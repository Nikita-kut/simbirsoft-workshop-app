package com.nikita.kut.android.simbirsoft_workshop.`kotlin-part-1`

interface Publication {
    val price: Double
    val wordCount: Int

    fun getType(): String
}