package com.nikita.kut.android.simbirsoft_workshop.kotlin.part1

interface Publication {
    val price: Double
    val wordCount: Int

    fun getType(): String
}