package com.namma.travelcompanion.core.ui.components

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast

/** Hand-offs to other apps (browser, maps, dialer, SMS, share). UI layer only; ViewModels never start activities. */

fun openUrl(context: Context, url: String) =
    context.startOrToast(Intent(Intent.ACTION_VIEW, Uri.parse(url)), "No browser found")

fun openMapsSearch(context: Context, query: String) =
    openUrl(context, "https://www.google.com/maps/search/?api=1&query=" + Uri.encode(query))

fun openGeoCoordinates(context: Context, lat: Double, lon: Double, label: String) {
    val geo = Intent(Intent.ACTION_VIEW, Uri.parse("geo:$lat,$lon?q=$lat,$lon(" + Uri.encode(label) + ")"))
    try {
        context.startActivity(geo)
    } catch (_: ActivityNotFoundException) {
        openMapsSearch(context, label)
    }
}

/** Opens the dialer pre-filled; never places the call itself (no CALL_PHONE permission needed). */
fun dialNumber(context: Context, phoneNumber: String) =
    context.startOrToast(Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber.filter { it.isDigit() || it == '+' })), "No dialer found")

fun sendSms(context: Context, phoneNumber: String, body: String) =
    context.startOrToast(
        Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:$phoneNumber")).putExtra("sms_body", body),
        "No SMS app found"
    )

/** System share sheet: the user picks WhatsApp, SMS or any contact. */
fun shareText(context: Context, text: String, title: String = "Share via") =
    context.startOrToast(
        Intent.createChooser(Intent(Intent.ACTION_SEND).setType("text/plain").putExtra(Intent.EXTRA_TEXT, text), title),
        "Nothing to share with"
    )

/** WhatsApp chat with [phoneNumber] (country code, digits only), falling back to SMS. */
fun sendWhatsAppOrSms(context: Context, phoneNumber: String, text: String) {
    val whatsApp = Intent(Intent.ACTION_VIEW, Uri.parse("https://api.whatsapp.com/send?phone=$phoneNumber&text=" + Uri.encode(text)))
    try {
        context.startActivity(whatsApp)
    } catch (_: ActivityNotFoundException) {
        sendSms(context, phoneNumber, text)
    }
}

/** Opens an installed app, else its Play Store page. */
fun openAppOrStore(context: Context, packageName: String) {
    val launch = context.packageManager.getLaunchIntentForPackage(packageName)
    if (launch != null) {
        context.startActivity(launch)
        return
    }
    try {
        context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$packageName")))
    } catch (_: ActivityNotFoundException) {
        openUrl(context, "https://play.google.com/store/apps/details?id=$packageName")
    }
}

private fun Context.startOrToast(intent: Intent, failureMessage: String) {
    try {
        startActivity(intent)
    } catch (_: ActivityNotFoundException) {
        Toast.makeText(this, failureMessage, Toast.LENGTH_SHORT).show()
    }
}
