package com.nikita.kut.android.simbirsoft_workshop.kotlin.part2

//11 task
sealed class Action {

    class Registration : Action()
    class Login(user: User) : Action()
    class Logout() : Action()

}
