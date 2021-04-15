package com.nikita.kut.android.simbirsoft_workshop.kotlin.part2

//11 task
sealed class Action {

    object Registration : Action()
    data class Login(val user: User) : Action()
    object Logout : Action()

}
