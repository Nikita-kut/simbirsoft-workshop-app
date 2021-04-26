package com.nikita.kut.android.simbirsoft_workshop.data

import android.os.Parcelable
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import kotlinx.android.parcel.Parcelize

@kotlinx.parcelize.Parcelize
data class News(
    val id: Long,
    @DrawableRes val newsPicture: Int,
    val newsTitle: String,
    val newsBody: String,
    val newsDate: String,
    val categoriesOfHelp: ArrayList<CategoriesOfHelp>
) : Parcelable
