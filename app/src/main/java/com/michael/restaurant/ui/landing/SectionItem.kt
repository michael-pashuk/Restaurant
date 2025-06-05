package com.michael.restaurant.ui.landing

import com.michael.restaurant.R

sealed class SectionItem(
    val id: String,
    val titleResId: Int,
    val iconResId: Int
) {
    object Home : SectionItem(
        id = "home",
        titleResId = R.string.home_title,
        iconResId = R.drawable.home
    )

    object About : SectionItem(
        id = "about",
        titleResId = R.string.about_title,
        iconResId = R.drawable.info
    )

    object Menu : SectionItem(
        id = "menu",
        titleResId = R.string.menu_title,
        iconResId = R.drawable.restaurant
    )

    object Events : SectionItem(
        id = "events",
        titleResId = R.string.events_title,
        iconResId = R.drawable.celebration
    )

    object Contact : SectionItem(
        id = "contact",
        titleResId = R.string.contact_title,
        iconResId = R.drawable.alternate_email
    )

    companion object {
        val listOfSections = listOf<SectionItem>(Home, About, Menu, Events, Contact)
    }
}