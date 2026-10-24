package com.nammaauto.ai.presentation.lifeline

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.lifecycle.ViewModel
import com.nammaauto.ai.domain.model.AirportBusRoute
import com.nammaauto.ai.domain.model.BangaloreLifelineDirectory
import com.nammaauto.ai.domain.model.EmergencyContact
import com.nammaauto.ai.domain.model.HelplineCategory
import com.nammaauto.ai.domain.model.HospitalInfo
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

    fun dialContact(context: Context, phoneNumber: String) {
        val cleanNumber = phoneNumber.replace("-", "").replace(" ", "")
        val intent = Intent(Intent.ACTION_DIAL).apply {
            data = Uri.parse("tel:$cleanNumber")
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
        }
        context.startActivity(intent)
    }

    fun openHospitalInMaps(context: Context, mapsQuery: String) {
        val uri = Uri.parse("geo:0,0?q=" + Uri.encode(mapsQuery))
        val intent = Intent(Intent.ACTION_VIEW, uri).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
        }
        try {
            context.startActivity(intent)
        } catch (e: Exception) {
            // Fallback to web browser
            val webIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/maps/search/?api=1&query=" + Uri.encode(mapsQuery))).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }
            context.startActivity(webIntent)
        }
    }

    fun sendBtpWhatsAppComplaint(context: Context): Boolean {
        val auto = _autoNumber.value.trim()
        if (auto.isEmpty()) return false

        val dateStr = SimpleDateFormat("dd-MMM-yyyy hh:mm a", Locale.getDefault()).format(Date())
        val loc = if (_incidentLocation.value.isNotBlank()) _incidentLocation.value else "Bengaluru"

        val message = """
            *BENGALURU TRAFFIC POLICE (BTP) AUTO GRIEVANCE*
            ------------------------------------------------
            *Auto Reg Number:* $auto
            *Violation:* ${_violationType.value}
            *Location:* $loc
            *Date & Time:* $dateStr
            
            _Generated via NammaAuto AI Citizen Support._
            Please register this complaint under Karnataka Motor Vehicles Rules.
        """.trimIndent()

        val whatsappUri = Uri.parse("https://api.whatsapp.com/send?phone=919480801000&text=" + Uri.encode(message))
        val intent = Intent(Intent.ACTION_VIEW, whatsappUri).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
        }
        return try {
            context.startActivity(intent)
            true
        } catch (e: Exception) {
            // Fallback to standard SMS
            val smsIntent = Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse("sms:9480801000")
                putExtra("sms_body", message)
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }
            context.startActivity(smsIntent)
            true
        }
    }
}
