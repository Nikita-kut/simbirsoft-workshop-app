package com.nikita.kut.android.simbirsoft_workshop.kotlin.part1

fun main() {
    // 2 task
    var book1: Book? = Book(price = 75.0, wordCount = 10000)
    println("Book1 $book1")

    val book2 = Book(price = 50.0, wordCount = 6000)
    println("Book2 $book2")

    val book3: Book? = null

    val magazine = Magazine(price = 15.0, wordCount = 900)
    println("Magazine $magazine")

    println(book1?.equals(book2))
    println(book1 === book2)
    // 3 task
    buy(book1)
    buy(book3)

    // 4 task
    val sum = { x: Int, y: Int -> println("Sum is ${x + y}") }

    sum(5, 10)


}

fun buy(publication: Publication?) {
    println("The purchase is complete. The purchase amount was ${publication?.price}")
}

