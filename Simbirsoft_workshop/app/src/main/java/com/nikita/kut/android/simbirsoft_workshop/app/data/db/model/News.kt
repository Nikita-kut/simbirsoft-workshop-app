package com.nikita.kut.android.simbirsoft_workshop.app.data.db.model

import android.os.Parcelable
import androidx.room.*
import com.nikita.kut.android.simbirsoft_workshop.app.data.db.model.News.Companion.TABLE_NAME
import com.nikita.kut.android.simbirsoft_workshop.feature.news.model.CategoriesOfHelp
import com.nikita.kut.android.simbirsoft_workshop.app.data.converters.CategoriesOfHelpConverter
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@kotlinx.parcelize.Parcelize
@JsonClass(generateAdapter = true)
@Entity(tableName = TABLE_NAME)
@TypeConverters(CategoriesOfHelpConverter::class)
data class News(
    @ColumnInfo(name = "id") @PrimaryKey val id: Long,
    @ColumnInfo(name = "image") @Json(name = "image") val newsPicture: String,
    @ColumnInfo(name = "title") @Json(name = "title") val newsTitle: String,
    @ColumnInfo(name = "body") @Json(name = "body") val newsBody: String,
    @ColumnInfo(name = "date") @Json(name = "date") val newsDate: String,
    @ColumnInfo(name = "category") @Json(name = "category") val categoriesOfHelp: List<CategoriesOfHelp>
) : Parcelable {
    companion object {
        const val TABLE_NAME = "users"
    }
}
