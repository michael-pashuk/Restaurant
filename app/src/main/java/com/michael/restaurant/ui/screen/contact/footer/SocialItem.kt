package com.michael.restaurant.ui.screen.contact.footer

import com.michael.restaurant.R

sealed class SocialItem(val icon: Int, val link: String) {

    object Telegram: SocialItem(R.drawable.telegram, "https://telegram.org")
    object VK: SocialItem(R.drawable.vk, "https://vk.com")
    object GitHub: SocialItem(R.drawable.github, "https://github.com/michael-pashuk/Restaurant")

    companion object {
        val socialList = listOf<SocialItem>(Telegram, VK, GitHub)
    }
}