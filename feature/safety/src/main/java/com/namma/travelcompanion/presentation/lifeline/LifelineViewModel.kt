package com.namma.travelcompanion.presentation.lifeline

import androidx.lifecycle.ViewModel
import com.namma.travelcompanion.domain.model.AirportBusRoute
import com.namma.travelcompanion.domain.model.BangaloreLifelineDirectory
import com.namma.travelcompanion.domain.model.EmergencyContact
import com.namma.travelcompanion.domain.model.HelplineCategory
import com.namma.travelcompanion.domain.model.HospitalInfo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class LifelineViewModel : ViewModel() {

    val emergencyContacts: List<EmergencyContact> = BangaloreLifelineDirectory.emergencyContacts
    val traumaHospitals: List<HospitalInfo> = BangaloreLifelineDirectory.topTraumaHospitals
    val busRoutes: List<AirportBusRoute> = BangaloreLifelineDirectory.airportBusRoutes
    val slangGuides = BangaloreLifelineDirectory.citySlangGuides

    private val _selectedTab = MutableStateFlow(HelplineCategory.POLICE_SAFETY)
    val selectedTab: StateFlow<HelplineCategory> = _selectedTab.asStateFlow()

    // BTP Grievance State
    private val _autoNumber = MutableStateFlow("")
    val autoNumber: StateFlow<String> = _autoNumber.asStateFlow()

    private val _violationType = MutableStateFlow("Demanded excess fare / refused meter")
    val violationType: StateFlow<String> = _violationType.asStateFlow()

    private val _incidentLocation = MutableStateFlow("")
    val incidentLocation: StateFlow<String> = _incidentLocation.asStateFlow()

    fun selectTab(tab: HelplineCategory) {
        _selectedTab.value = tab
    }

    fun setAutoNumber(number: String) {
        _autoNumber.value = number.uppercase()
    }

    fun setViolationType(type: String) {
        _violationType.value = type
    }

    fun setIncidentLocation(loc: String) {
        _incidentLocation.value = loc
    }

    /** WhatsApp/SMS body for a BTP complaint, or null until the auto number is filled in. */
    fun btpComplaintMessage(): String? {
        val auto = _autoNumber.value.trim()
        if (auto.isEmpty()) return null

        val dateStr = SimpleDateFormat("dd-MMM-yyyy hh:mm a", Locale.getDefault()).format(Date())
        val loc = if (_incidentLocation.value.isNotBlank()) _incidentLocation.value else "Bengaluru"

        return """
            *BENGALURU TRAFFIC POLICE (BTP) AUTO GRIEVANCE*
            ------------------------------------------------
            *Auto Reg Number:* $auto
            *Violation:* ${_violationType.value}
            *Location:* $loc
            *Date & Time:* $dateStr

            Please register this complaint under Karnataka Motor Vehicles Rules.
        """.trimIndent()
    }

    companion object {
        const val BTP_WHATSAPP_NUMBER = "919480801000"
    }
}
