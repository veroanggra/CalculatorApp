package com.veroanggra.calculatorapp.arithmatic

sealed class CalculateAction {
    data class InputChange(val input: String) : CalculateAction()
    object ClearInput: CalculateAction()
    object Evaluate: CalculateAction()
    object Delete: CalculateAction()
}