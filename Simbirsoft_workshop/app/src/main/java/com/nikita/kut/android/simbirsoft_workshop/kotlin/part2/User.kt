package com.nikita.kut.android.simbirsoft_workshop.kotlin.part2

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import kotlin.random.Random

//2 task
@RequiresApi(Build.VERSION_CODES.O)
data class User(
    private val id: Long = Random.nextLong(),
    val name: String,
    val age: Int,
    val type: Type,
) {
    private val dtf: DateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss")

    val startTime: String by lazy { dtf.format(LocalTime.now()) }
}

