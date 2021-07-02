package com.nikita.kut.android.simbirsoft_workshop.kotlin.part2

import java.lang.IllegalStateException

fun User.checkAdultUser(): Boolean {
    return if (this.age >= 18) {
        println(this)
        true
    } else {
        try {
            throw IllegalStateException("Can't print: User age < 18")
        } catch (e: IllegalStateException) {
            println(e.message)
        }
        false
    }
}