package com.example.cryptosep.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.cryptosep.R

val font = FontFamily(
    Font(R.font.aclonica, FontWeight.Thin),
    Font(R.font.agbalumo, FontWeight.ExtraLight),
)


// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = font,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
)

private val textStyle = TextStyle(fontFamily = font)
private val Medium = textStyle.copy(fontWeight = FontWeight.W500)
private val Regular = textStyle.copy(fontWeight = FontWeight.W400)
private val SemiBold = textStyle.copy(fontWeight = FontWeight.W600)
private val Bold = textStyle.copy(fontWeight = FontWeight.W700)


val Regular_7 = Regular.copy(fontSize = 7.sp)
val Regular_10 = Regular.copy(fontSize = 10.sp)
val Regular_12 = Regular.copy( fontSize = 12.sp)
val Regular_14 = Regular.copy( fontSize = 14.sp)
val Regular_16 = Regular.copy(fontSize = 16.sp)
val Medium_8 = Medium.copy(fontSize = 8.sp)
val Medium_10 = Medium.copy( fontSize = 10.sp)
val Medium_12 = Medium.copy( fontSize = 12.sp)
val Medium_13 = Medium.copy(fontSize = 13.sp)
val Medium_14 = Medium.copy( fontSize = 14.sp)
val Medium_18 = Medium.copy(fontSize = 18.sp)
val SemiBold_12 = SemiBold.copy(fontSize = 12.sp)
val SemiBold_14 = SemiBold.copy(fontSize = 14.sp)
val SemiBold_16 = SemiBold.copy( fontSize = 16.sp)
val SemiBold_20 = SemiBold.copy( fontSize = 20.sp)
val SemiBold_24 = SemiBold.copy( fontSize = 24.sp)
val Bold_12 = Bold.copy(fontSize = 12.sp)
val Bold_14 = Bold.copy(fontSize = 14.sp)
val Bold_18 = Bold.copy(fontSize = 18.sp)
val Bold_20 = Bold.copy(fontSize = 20.sp)
val Bold_28 = Bold.copy( fontSize = 28.sp)
val Bold_30 = Bold.copy( fontSize = 30.sp)
val Bold_36 = Bold.copy( fontSize = 36.sp)