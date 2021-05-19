package com.nikita.kut.android.simbirsoft_workshop.model

import androidx.room.TypeConverter

class CategoriesOfHelpConverter {

    @TypeConverter
    fun fromCategoriesOfHelp(value: List<CategoriesOfHelp>): String =
        value.joinToString(separator = ",") { it.name }

    @TypeConverter
    fun toCategoriesOfHelp(value: String): List<CategoriesOfHelp> =
        value.split(",").map { CategoriesOfHelp.valueOf(it) }.toList()

}