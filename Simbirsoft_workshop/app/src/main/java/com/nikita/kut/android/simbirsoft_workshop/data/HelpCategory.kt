package com.nikita.kut.android.simbirsoft_workshop.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class HelpCategory(
    val id: Long,
    @Json(name = "name") val category: String,
    @Json(name = "image") val drawableRes: String
)

enum class CategoriesOfHelp {
    CHILDREN,
    ADULT,
    ELDERLY,
    ANIMALS,
    EVENTS
}


