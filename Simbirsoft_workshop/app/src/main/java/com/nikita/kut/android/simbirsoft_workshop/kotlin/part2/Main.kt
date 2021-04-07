package com.nikita.kut.android.simbirsoft_workshop.kotlin.part2

import android.os.Build
import androidx.annotation.RequiresApi
import com.nikita.kut.android.simbirsoft_workshop.kotlin.part2.Action.*

@RequiresApi(Build.VERSION_CODES.O)
fun main() {
    val user = User(name = "Test", age = 5, type = Type.FULL)

    // 3 task
    println(user.getStartTime())
    Thread.sleep(1000)
    println(user.getStartTime())

    //4 task
    val users = mutableListOf(User(name = "User1", age = 1, type = Type.FULL)).apply {
        add(User(name = "User2", age = 2, type = Type.DEMO))
        add(User(name = "User3", age = 3, type = Type.FULL))
        add(User(name = "User4", age = 4, type = Type.DEMO))
    }
    // 5 task
    val usersTypeFull = users.filter { it.type == Type.FULL }

    //6 task
    val usersName: MutableList<String> = mutableListOf()
    for (element in users) {
        usersName.add(element.name)
    }
    println(
        """
        First element ${usersName.first()}
        Last element ${usersName.last()}
    """.trimIndent()
    )

    //7 task
    val userAdult = User(name = "Adult", age = 20, type = Type.FULL)
    val userTeenage = User(name = "Teenage", age = 12, type = Type.FULL)
    userAdult.checkAdultUser()
    userTeenage.checkAdultUser()


    //8 task
    authObj.authSuccess()
    authObj.authFailed()

    //9 task
    auth({ updateCache() }, userAdult)

    auth({ updateCache() }, userTeenage)

    //12 task
    val registration = Registration()
    val login = Login(userAdult)
    val logout = Logout()
    doAction(registration, userAdult)
    doAction(login, userAdult)
    doAction(logout, userAdult)
}

//8 task
val authObj = object : AuthCallBack {
    override fun authSuccess() {
        println("Auth is success")
    }

    override fun authFailed() {
        println("Auth if failed")
    }
}

//9 task
fun updateCache() {
    println("Cache is update")
}

//10 task
inline fun auth(update: () -> Unit, user: User) {
    if (user.checkAdultUser()) {
        update()
        authObj.authSuccess()
    } else {
        authObj.authFailed()
    }
}

//12 task
@RequiresApi(Build.VERSION_CODES.O)
fun doAction(action: Action, user: User) {
    when (action) {
        is Registration -> println("Registration completed successfully")
        is Login -> auth({ updateCache() }, user)
        is Logout -> println("Logout successfully")
        else -> {
        }
    }
}