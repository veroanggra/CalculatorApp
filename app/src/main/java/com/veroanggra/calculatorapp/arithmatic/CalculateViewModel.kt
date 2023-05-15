package com.veroanggra.calculatorapp.arithmatic

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.veroanggra.calculatorapp.arithmatic.Arithmatic.peek
import com.veroanggra.calculatorapp.arithmatic.Arithmatic.pop
import com.veroanggra.calculatorapp.arithmatic.Arithmatic.push
import kotlin.math.pow

class CalculateViewModel : ViewModel() {
    var isDarkEnabled = mutableStateOf(false)

    fun setTheme(isDarkTheme: Boolean) {
        isDarkEnabled.value = isDarkTheme
    }

    var uiCalculateState by mutableStateOf(UICalculateState())

    fun onAction(action: CalculateAction) {
        when (action) {
            is CalculateAction.InputChange -> onInputChange(action.input)
            is CalculateAction.ClearInput -> clearInputChange()
            is CalculateAction.Evaluate -> evaluateExpression()
            is CalculateAction.Delete -> onDelete()
        }
    }

    private fun onInputChange(input: String) {
        uiCalculateState.apply { uiCalculateState = copy(input = this.input + input) }
    }

    private fun onDelete() {
        uiCalculateState.apply { uiCalculateState = copy(input = this.input.dropLast(1)) }
    }

    private fun clearInputChange() {
        uiCalculateState = uiCalculateState.copy(input = "")
    }

    private fun onResultChange(result: String) {
        uiCalculateState = uiCalculateState.copy(result = result)
    }

    private fun evaluateExpression() {
        if (uiCalculateState.input.isNotBlank()) {
            onResultChange(result = result(uiCalculateState.input))
        }
    }

    private fun notNumeric(char: Char): Boolean = when (char) {
        '+', '-', '*', '/', '(', ')', '^' -> true
        else -> false
    }

    private fun operatorPrecedence(char: Char?): Int = when (char) {
        '+', '-' -> 1
        '*', '/' -> 2
        '^' -> 3
        else -> -1
    }

    private fun outputConversion(input: String): String {
        var result = ""
        val inputString = ArrayDeque<Char>()

        for (s in input) {
            if (!notNumeric(s)) {
                result += s
            } else if (s == '(') {
                inputString.push(s)
            } else if (s == ')') {
                while (!inputString.isEmpty() && inputString.peek() != '(') {
                    result += " " + inputString.pop()
                }
                inputString.pop()
            } else {
                while (!inputString.isEmpty() && operatorPrecedence(s) <= operatorPrecedence(
                        inputString.peek()
                    )
                ) {
                    result += " ${inputString.pop()} "
                }
                inputString.push(s)
                result += " "
            }
        }

        result += " "
        while (!inputString.isEmpty()) {
            if (inputString.peek() == '(') return "Invalid"
            result += inputString.pop()!! + " "

        }
        return result.trim()
    }

    private fun replaceNumber(input: String): String {
        val arrayMember = StringBuffer(input)

        if (arrayMember[0] == '-') {
            arrayMember.setCharAt(0, 'n')
        }
        var i = 0
        while (i < arrayMember.length) {
            if (arrayMember[i] == '-') {
                if (arrayMember[i - 1] == '+' ||
                    arrayMember[i - 1] == '-' ||
                    arrayMember[i - 1] == '/' ||
                    arrayMember[i - 1] == '*' ||
                    arrayMember[i - 1] == '('
                ) {
                    arrayMember.setCharAt(i, 'n')
                }
            }
            i++
        }
        return arrayMember.toString()
    }

    private fun notOperator(char: Char): Boolean = when (char) {
        '+', '-', '*', '/', '(', ')', '^' -> false
        else -> true
    }

    private fun evaluation(input: String): Double {
        var inputString = ""
        val stack = ArrayDeque<Double>()
        for (i in input) {
            if (notOperator(i) && i != ' ') {
                inputString += i
            } else if (i == ' ' && inputString != "") {
                stack.push(inputString.replace('n', '-').toDouble())
                inputString = ""
            } else if (!notOperator(i)) {
                val num1 = stack.pop()
                val num2 = stack.pop()

                when (i) {
                    '+' -> stack.push(num2!! + num1!!)
                    '-' -> stack.push(num2!! - num1!!)
                    '/' -> stack.push(num2!! / num1!!)
                    '*' -> stack.push(num2!! * num1!!)
                    '^' -> stack.push(num2!!.pow(num1!!))
                }
            }
        }
        return stack.pop()!!
    }

    private fun result(input: String): String {
        val stringInput = replaceNumber(input)
        val postFix = outputConversion(stringInput)

        if (postFix == "Invalid") {
            return postFix
        }
        return try {
            val evaluation = evaluation(postFix)
            evaluation.toString()
        } catch (e: Exception) {
            e.printStackTrace()
            "Invalid"
        }

    }

}
