package com.namma.travelcompanion.core.util

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast

fun openBrowserUrl(context: Context, url: String) {
    if (url.isBlank()) return
    try {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        context.startActivity(intent)
    } catch (e: Exception) {
        Toast.makeText(context, "Unable to open link: ${e.message}", Toast.LENGTH_SHORT).show()
    }
}
