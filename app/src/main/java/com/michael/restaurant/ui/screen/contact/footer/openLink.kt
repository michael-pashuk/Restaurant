package com.michael.restaurant.ui.screen.contact.footer

import android.content.Context
import android.content.Intent
import androidx.core.net.toUri

fun openLink(
    context: Context,
    link: String
) {
    val intent = Intent(Intent.ACTION_VIEW, link.toUri())
    context.startActivity(intent)
}