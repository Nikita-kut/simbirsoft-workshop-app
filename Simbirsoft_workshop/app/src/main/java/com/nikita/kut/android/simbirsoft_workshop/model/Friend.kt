package com.nikita.kut.android.simbirsoft_workshop.model

import android.annotation.SuppressLint
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

@SuppressLint("SupportAnnotationUsage")
data class Friend(
    val id: Long,
    @DrawableRes val drawableRes: Int,
    @StringRes val name: Int
)
