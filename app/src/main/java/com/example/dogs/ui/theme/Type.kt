package com.example.dogs.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.example.dogs.extensions.BodyLarge
import com.example.dogs.extensions.BodyMedium
import com.example.dogs.extensions.LabelMedium
import com.example.dogs.extensions.LineHeight
import com.example.dogs.extensions.TitleLarge

val Typography = Typography(
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold,
        color = TextColorBlack,
        fontSize = TitleLarge,
        lineHeight = LineHeight
    ),
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        color = TextColorGray,
        fontSize = BodyLarge,
        lineHeight = LineHeight
    ),
    bodyMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        color = TextColorWhite,
        fontSize = BodyMedium,
        lineHeight = LineHeight
    ),
    labelMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold,
        color = TextColorGray,
        fontSize = LabelMedium,
        lineHeight =LineHeight
    )
)