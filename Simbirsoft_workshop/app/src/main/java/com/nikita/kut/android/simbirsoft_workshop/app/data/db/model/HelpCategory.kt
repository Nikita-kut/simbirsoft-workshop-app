package com.nikita.kut.android.simbirsoft_workshop.app.data.db.model

import androidx.room.*
import com.nikita.kut.android.simbirsoft_workshop.app.data.db.model.HelpCategory.Companion.TABLE_NAME
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
@Entity(tableName = TABLE_NAME)
data class HelpCategory(
    @ColumnInfo(name = "id") @PrimaryKey val id: Long,
    @ColumnInfo(name = "name") @Json(name = "name") val category: String,
    @ColumnInfo(name = "image") @Json(name = "image") val drawableRes: String
) {
    companion object {
        const val TABLE_NAME = "categories"
    }
}


