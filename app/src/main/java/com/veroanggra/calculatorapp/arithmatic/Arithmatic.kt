package com.veroanggra.calculatorapp.arithmatic

object Arithmatic {
    fun <T> ArrayDeque<T>.push(element: T) = addLast(element)
    fun <T> ArrayDeque<T>.pop() = removeLastOrNull()
    fun <T> ArrayDeque<T>.peek() = lastOrNull()
}