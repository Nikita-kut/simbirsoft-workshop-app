package com.nikita.kut.android.simbirsoft_workshop

interface Publication {
    val price: Double
    val wordCount: Int

    fun getType(): String
}