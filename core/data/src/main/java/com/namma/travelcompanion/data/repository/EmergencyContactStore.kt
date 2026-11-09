package com.namma.travelcompanion.data.repository

import android.content.Context
import androidx.core.content.edit
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/** The user's own SOS contact. Blank means "let the user pick who to send it to" via the share sheet. */
class EmergencyContactStore(context: Context) {

    private val prefs = context.getSharedPreferences("namma_travel_safety", Context.MODE_PRIVATE)

    private val _phone = MutableStateFlow(prefs.getString(KEY_PHONE, "").orEmpty())
    val phone: StateFlow<String> = _phone.asStateFlow()

    fun save(phone: String) {
        val clean = phone.filter { it.isDigit() || it == '+' }
        prefs.edit { putString(KEY_PHONE, clean) }
        _phone.value = clean
    }

    private companion object {
        const val KEY_PHONE = "sos_phone"
    }
}
