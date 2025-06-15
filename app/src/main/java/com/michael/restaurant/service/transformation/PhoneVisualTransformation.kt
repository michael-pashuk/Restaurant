package com.michael.restaurant.service.transformation

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class PhoneNumberVisualTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val trimmed = if (text.length > 10) text.text.substring(0..9) else text.text

        var out = if (trimmed.isNotEmpty()) "+7-(" else ""

        for (i in trimmed.indices) {
            if (i == 3) out += ")-"
            if (i == 6) out += "-"
            if (i == 8) out += "-"
            out += trimmed[i]
        }

        return TransformedText(AnnotatedString(out), phoneNumberOffsetMapping)
    }

    private val phoneNumberOffsetMapping = object : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            return when (offset) {
                0 -> 0
                in 1..3 -> offset + 4
                in 4..6 -> offset + 6
                in 7..8 -> offset + 7
                else -> offset + 8
            }
        }

        override fun transformedToOriginal(offset: Int): Int {
            return when (offset) {
                in 0..4 -> 0
                in 5..8 -> offset - 4
                in 9..12 -> offset - 6
                in 13..15 -> offset - 7
                else -> offset - 8
            }
        }
    }
}
