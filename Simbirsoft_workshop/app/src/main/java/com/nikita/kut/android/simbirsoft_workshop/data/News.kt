package com.nikita.kut.android.simbirsoft_workshop.data

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@kotlinx.parcelize.Parcelize
@JsonClass(generateAdapter = true)
data class News(
    val id: Long,
    @Json(name = "image") val newsPicture: String,
    @Json(name = "title") val newsTitle: String,
    @Json(name = "body") val newsBody: String,
    @Json(name = "date") val newsDate: String,
    @Json(name = "category") val categoriesOfHelp: List<CategoriesOfHelp>
) : Parcelable
