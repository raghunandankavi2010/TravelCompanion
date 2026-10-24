package com.nammaauto.ai.presentation.phrasebook

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.nammaauto.ai.NammaAutoApplication
import com.nammaauto.ai.data.speech.KannadaTtsEngine
import com.nammaauto.ai.data.speech.SpeechToTextManager
import com.nammaauto.ai.data.translator.CommuteTranslator
import com.nammaauto.ai.data.translator.TranslationResult
import com.nammaauto.ai.domain.model.KannadaPhrase
import com.nammaauto.ai.domain.model.PhraseCategory
import com.nammaauto.ai.domain.repository.PhrasebookRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class KannadaSathiViewModel(application: Application) : AndroidViewModel(application) {

    private val ttsEngine = KannadaTtsEngine(application)
    val sttManager = SpeechToTextManager(application)
    private val billingManager = (application as NammaAutoApplication).billingManager

    val isSpeaking = ttsEngine.isSpeaking
    val isTtsReady = ttsEngine.isReady
    val subscriptionState = billingManager.subscriptionState
    val isListeningToMic = sttManager.isListening

    private val _selectedCategory = MutableStateFlow<PhraseCategory?>(null)
    val selectedCategory: StateFlow<PhraseCategory?> = _selectedCategory.asStateFlow()

    private val _customEnglishInput = MutableStateFlow("")
    val customEnglishInput: StateFlow<String> = _customEnglishInput.asStateFlow()

    private val _activeTranslation = MutableStateFlow<TranslationResult?>(null)
    val activeTranslation: StateFlow<TranslationResult?> = _activeTranslation.asStateFlow()

    private val _isTranslating = MutableStateFlow(false)
    val isTranslating: StateFlow<Boolean> = _isTranslating.asStateFlow()

    private val _lastSpokenPhraseId = MutableStateFlow<String?>(null)
    val lastSpokenPhraseId: StateFlow<String?> = _lastSpokenPhraseId.asStateFlow()

    fun selectCategory(category: PhraseCategory?) {
        _selectedCategory.value = category
    }

    fun setCustomEnglishInput(input: String) {
        _customEnglishInput.value = input
    }

    /**
     * Translates custom English text/speech into Kannada and stores the active result.
     */
    fun translateCustomPhrase(englishText: String, andSpeakImmediately: Boolean = false) {
        val query = englishText.trim()
        if (query.isBlank()) return

        _customEnglishInput.value = query
        viewModelScope.launch {
            _isTranslating.value = true
            val result = CommuteTranslator.translateToKannada(query)
            _activeTranslation.value = result
            _isTranslating.value = false

            if (andSpeakImmediately && result.kannadaLipi.isNotBlank()) {
                playActiveTranslation()
            }
        }
    }

    fun playActiveTranslation(): Boolean {
        val translation = _activeTranslation.value ?: return false
        val canPlay = billingManager.consumePhraseQuota()
        if (!canPlay) return false

        _lastSpokenPhraseId.value = "custom_translation"
        ttsEngine.speak(translation.kannadaLipi, "custom_translation")
        return true
    }

    fun playPhrase(phrase: KannadaPhrase): Boolean {
        val canPlay = billingManager.consumePhraseQuota()
        if (!canPlay) return false

        _lastSpokenPhraseId.value = phrase.id
        ttsEngine.speak(phrase.kannadaLipi, phrase.id)
        return true
    }

    fun startVoiceMicInput() {
        sttManager.startListening { transcribedEnglish ->
            translateCustomPhrase(transcribedEnglish, andSpeakImmediately = false)
        }
    }

    fun stopVoiceMicInput() {
        sttManager.stopListening()
    }

    fun getFilteredPhrases(): List<KannadaPhrase> {
        val cat = _selectedCategory.value
        return if (cat == null) PhrasebookRepository.phrases
        else PhrasebookRepository.getPhrasesByCategory(cat)
    }

    override fun onCleared() {
        super.onCleared()
        ttsEngine.release()
        sttManager.release()
    }
}
