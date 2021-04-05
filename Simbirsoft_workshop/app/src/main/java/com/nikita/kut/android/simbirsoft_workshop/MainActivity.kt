package com.nikita.kut.android.simbirsoft_workshop

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}

@RequiresApi(Build.VERSION_CODES.N)
fun main() {

    val student = CollectionsBlock.Student()

    val listStudents = student.listStudent

    listStudents.forEach { println(it) }

    student.averageGrade(listStudents, CollectionsBlock.Student::getEngGrade).forEach { t, u ->
        println("Course = $t, Eng average grade = $u")
    }

    student.averageGrade(listStudents, CollectionsBlock.Student::getMathGrade).forEach { t, u ->
        println("Course = $t, Math average grade = $u")
    }


    fun replaceAllNumbers(text: String): String? {
        var text = text
        val digitsText =
            arrayOf("Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine")
        var c = '0'
        while (c <= '9') {
            text = text.replace(c.toString().toRegex(), digitsText[c - '0'] + ' ')
            ++c
        }
        return text
    }

    println(replaceAllNumbers("Text132"))
}