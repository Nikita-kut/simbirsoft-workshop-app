package com.nikita.kut.android.simbirsoft_workshop

class Magazine(override val price: Double, override val wordCount: Int) : Publication {

    override fun getType(): String {
        return when  {
            wordCount <= 1000 -> "Flash Fiction"
            wordCount <= 7500 -> "Short Story"
            else -> "Magazine"
        }
    }

    override fun toString(): String {
        return "(price = $price euro, wordCount = $wordCount)"
    }


}
