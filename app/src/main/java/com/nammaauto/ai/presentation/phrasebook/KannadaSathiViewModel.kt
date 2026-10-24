package com.nammaauto.ai.presentation.phrasebook

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.nammaauto.ai.NammaAutoApplication
import com.nammaauto.ai.data.speech.KannadaTtsEngine
import com.nammaauto.ai.domain.model.KannadaPhrase
import com.nammaauto.ai.domain.model.PhraseCategory
import com.nammaauto.ai.domain.repository.PhrasebookRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class KannadaSathiViewModel(application: Application) : AndroidViewModel(application) {

    private val ttsEngine = KannadaTtsEngine(application)
    private val billingManager = (application as NammaAutoApplication).billingManager

    val isSpeaking = ttsEngine.isSpeaking
    val isTtsReady = ttsEngine.isReady
    val subscriptionState = billingManager.subscriptionState

    private val _selectedCategory = MutableStateFlow<PhraseCategory?>(null)
    val selectedCategory: StateFlow<PhraseCategory?> = _selectedCategory.asStateFlow()

    private val _hagglingAsk = MutableStateFlow("150")
    val hagglingAsk: StateFlow<String> = _hagglingAsk.asStateFlow()

    private val _lastSpokenPhraseId = MutableStateFlow<String?>(null)
    val lastSpokenPhraseId: StateFlow<String?> = _lastSpokenPhraseId.asStateFlow()

    fun selectCategory(category: PhraseCategory?) {
        _selectedCategory.value = category
    }

    fun setHagglingAsk(value: String) {
        _hagglingAsk.value = value.filter { it.isDigit() }.take(4)
    }

    fun playPhrase(phrase: KannadaPhrase): Boolean {
        val canPlay = billingManager.consumePhraseQuota()
        if (!canPlay) return false

        _lastSpokenPhraseId.value = phrase.id
        ttsEngine.speak(phrase.kannadaLipi, phrase.id)
        return true
    }

    fun playCustomHagglingPhrase(askAmount: Int): Boolean {
        val canPlay = billingManager.consumePhraseQuota()
        if (!canPlay) return false

        // Win-win counter offer: ~65-70% of the inflated demand, minimum ₹30
        val counterOffer = kotlin.math.max(30, (askAmount * 0.65).toInt())
        val phraseText = "ಭೈಯಾ, ₹$askAmount ತುಂಬಾ ಜಾಸ್ತಿ ಆಯ್ತು, ₹$counterOffer ಕೊಡ್ತೀನಿ, ಬರ್ತೀರಾ?"
        ttsEngine.speak(phraseText, "custom_haggling")
        return true
    }

    fun getFilteredPhrases(): List<KannadaPhrase> {
        val cat = _selectedCategory.value
        return if (cat == null) PhrasebookRepository.phrases
        else PhrasebookRepository.getPhrasesByCategory(cat)
    }

    override fun onCleared() {
        super.onCleared()
        ttsEngine.release()
    }
}
