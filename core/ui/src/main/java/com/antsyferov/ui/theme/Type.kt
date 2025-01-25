package com.antsyferov.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import com.antsyferov.ui.R

val LocalPidkovaTypography = staticCompositionLocalOf { PidkovaTypography() }

data class PidkovaTypography(
    val title: TextStyle = TextStyle(
        fontFamily = FontFamily(Font(R.font.manrope_bold)),
        fontSize = 30.sp,
        lineHeight = 36.sp,
    ),
    val subtitle: TextStyle = TextStyle(
        fontFamily = FontFamily(Font(R.font.manrope_semibold)),
        fontSize = 24.sp,
        lineHeight = 30.sp,
    ),
    val body: TextStyle = TextStyle(
        fontFamily = FontFamily(Font(R.font.manrope_regular)),
        fontSize = 18.sp,
        lineHeight = 24.sp,
    ),
    val caption: TextStyle = TextStyle(
        fontFamily = FontFamily(Font(R.font.manrope_light)),
        fontSize = 14.sp,
        lineHeight = 20.sp,
    ),
    val overline: TextStyle = TextStyle(
        fontFamily = FontFamily(Font(R.font.manrope_extralight)),
        fontSize = 12.sp,
        lineHeight = 16.sp,
    ),
    val button: TextStyle = TextStyle(
        fontFamily = FontFamily(Font(R.font.manrope_medium)),
        fontSize = 16.sp,
        lineHeight = 20.sp,
    )
)