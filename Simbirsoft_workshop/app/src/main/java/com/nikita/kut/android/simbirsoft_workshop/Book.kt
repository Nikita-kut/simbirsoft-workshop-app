package com.nikita.kut.android.simbirsoft_workshop

class Book(override val price: Double, override val wordCount: Int) : Publication {

    override fun getType(): String {
        return when {
            wordCount <= 1000 -> "Flash Fiction"
            wordCount <= 7500 -> "Short Story"
            else -> "Novel"
        }
    }

    override fun toString(): String {
        return "(price = $price euro, wordCount = $wordCount)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Book

        if (price != other.price) return false
        if (wordCount != other.wordCount) return false

        return true
    }

    override fun hashCode(): Int {
        var result = price.hashCode()
        result = 31 * result + wordCount
        return result
    }


}