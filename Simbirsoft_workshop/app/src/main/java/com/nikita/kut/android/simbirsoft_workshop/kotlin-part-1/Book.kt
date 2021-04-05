package com.nikita.kut.android.simbirsoft_workshop.`kotlin-part-1`

class Book(override val price: Double, override val wordCount: Int): Publication {

    override fun getType(): String {
        return when  {
            wordCount <= 1000 -> "Flash Fiction"
            wordCount <= 7500 -> "Short Story"
            else -> "Novel"
        }
    }
}