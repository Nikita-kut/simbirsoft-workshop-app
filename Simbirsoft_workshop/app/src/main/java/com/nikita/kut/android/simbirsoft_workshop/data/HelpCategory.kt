package com.nikita.kut.android.simbirsoft_workshop.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class HelpCategory(
    val id: Long,
    @DrawableRes val drawableRes: Int,
    val category: String
)

enum class CategoriesOfHelp {
    CHILDREN,
    ADULT,
    ELDERLY,
    ANIMALS,
    EVENTS
}


