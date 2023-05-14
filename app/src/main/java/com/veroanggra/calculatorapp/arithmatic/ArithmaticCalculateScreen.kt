package com.veroanggra.calculatorapp.arithmatic


import android.content.res.Resources.Theme
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import com.veroanggra.calculatorapp.common.NumberButton
import com.veroanggra.calculatorapp.ui.theme.lightFontYellow
import com.veroanggra.calculatorapp.ui.theme.lightPurple
import com.veroanggra.calculatorapp.ui.theme.lightTransparentPurple
import com.veroanggra.calculatorapp.ui.theme.lightTransparentYellow
import com.veroanggra.calculatorapp.ui.theme.lightYellow

@Composable
fun ArithmaticCalculateScreen(modifier: Modifier, calculateViewModel: CalculateViewModel?) {
    val uiCalculateState = calculateViewModel?.uiCalculateState
    val contentWidthCommon = 70.dp
    ConstraintLayout(
        modifier = modifier
            .fillMaxSize()
            .padding(start = 30.dp, end = 30.dp)
    ) {
        val (firstSpacer, secondSpacer, inputText, outputText, buttonC, buttonOpenBracket, buttonClosedBracket, buttonX, buttonDivide, button7, button8, button9, button4, button5, button6, buttonMin) = createRefs()
        val (thirdSpacer, button1, button2, button3, buttonSum, button0, buttonSquare, buttonDel, buttonEqual, buttonDot, fourthSpacer, fifthSpacer, sixSpacer) = createRefs()

        val guidelineTop = createGuidelineFromTop(0.1f)
        createHorizontalChain(
            buttonC,
            buttonOpenBracket,
            buttonClosedBracket,
            buttonDivide,
            chainStyle = ChainStyle.SpreadInside
        )
        createHorizontalChain(
            button7,
            button8,
            button9,
            buttonX,
            chainStyle = ChainStyle.SpreadInside
        )
        createHorizontalChain(
            button4,
            button5,
            button6,
            buttonMin,
            chainStyle = ChainStyle.SpreadInside
        )

        createHorizontalChain(
            button1,
            button2,
            button3,
            buttonSum,
            chainStyle = ChainStyle.SpreadInside
        )

        createHorizontalChain(
            button0,
            buttonDot,
            buttonSquare,
            chainStyle = ChainStyle.SpreadInside
        )

        createHorizontalChain(
            buttonDel,
            buttonEqual,
            chainStyle = ChainStyle.SpreadInside
        )

        Text(
            modifier = modifier
                .padding(end = 20.dp)
                .fillMaxWidth()
                .constrainAs(inputText) {
                    top.linkTo(guidelineTop)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            text = uiCalculateState?.input ?: "0", textAlign = TextAlign.End,
            fontWeight = FontWeight.Black, fontSize = 20.sp
        )

        Text(
            text = uiCalculateState?.result ?: "0",
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(end = 20.dp)
                .fillMaxWidth()
                .padding(top = 16.dp)
                .constrainAs(outputText) {
                    start.linkTo(parent.start)
                    top.linkTo(inputText.bottom)
                    end.linkTo(parent.end)
                }, textAlign = TextAlign.End, fontSize = 25.sp
        )
        Spacer(modifier = Modifier
            .height(20.dp)
            .fillMaxWidth()
            .constrainAs(sixSpacer) {
                start.linkTo(parent.start)
                top.linkTo(outputText.bottom)
                end.linkTo(parent.end)
            })
        NumberButton(
            symbol = "C",
            colorBackground = MaterialTheme.colorScheme.secondary,
            colorFont = Color.White,
            modifier = Modifier
                .width(70.dp)
                .constrainAs(buttonC) {
                    start.linkTo(parent.start)
                    top.linkTo(sixSpacer.bottom)
                }
        ) {
            calculateViewModel?.onAction(CalculateAction.ClearInput)
        }
        NumberButton(
            symbol = "(",
            colorBackground = MaterialTheme.colorScheme.tertiary,
            colorFont = lightFontYellow,
            modifier = Modifier
                .width(70.dp)
                .constrainAs(buttonOpenBracket) {
                    top.linkTo(buttonC.top)
                    bottom.linkTo(buttonC.bottom)
                }
        ) {
            calculateViewModel?.onAction(CalculateAction.InputChange("("))
        }
        NumberButton(
            symbol = ")",
            colorBackground = MaterialTheme.colorScheme.tertiary,
            colorFont = lightFontYellow,
            modifier = Modifier
                .width(70.dp)
                .constrainAs(buttonClosedBracket) {
                    top.linkTo(buttonC.top)
                    bottom.linkTo(buttonC.bottom)
                }
        ) {
            calculateViewModel?.onAction(CalculateAction.InputChange(")"))
        }
        NumberButton(
            symbol = "÷",
            colorBackground = MaterialTheme.colorScheme.surface,
            colorFont = MaterialTheme.colorScheme.onSecondary,
            modifier = Modifier
                .width(70.dp)
                .constrainAs(buttonDivide) {
                    top.linkTo(buttonC.top)
                    bottom.linkTo(buttonC.bottom)
                    end.linkTo(parent.end)
                }) {
            calculateViewModel?.onAction(CalculateAction.InputChange("/"))
        }

        Spacer(modifier = Modifier
            .height(20.dp)
            .fillMaxWidth()
            .constrainAs(firstSpacer) {
                start.linkTo(parent.start)
                top.linkTo(buttonC.bottom)
                end.linkTo(parent.end)
            })

        NumberButton(
            symbol = "7",
            colorBackground = MaterialTheme.colorScheme.tertiary,
            colorFont = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier
                .width(70.dp)
                .constrainAs(button7) {
                    start.linkTo(parent.start)
                    top.linkTo(firstSpacer.bottom)
                }
        ) {
            calculateViewModel?.onAction(CalculateAction.InputChange("7"))
        }
        NumberButton(
            symbol = "8",
            colorBackground = MaterialTheme.colorScheme.tertiary,
            colorFont = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier
                .width(70.dp)
                .constrainAs(button8) {
                    top.linkTo(button7.top)
                    bottom.linkTo(button7.bottom)
                }
        ) {
            calculateViewModel?.onAction(CalculateAction.InputChange("8"))
        }
        NumberButton(
            symbol = "9",
            colorBackground = MaterialTheme.colorScheme.tertiary,
            colorFont = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier
                .width(70.dp)
                .constrainAs(button9) {
                    bottom.linkTo(button7.bottom)
                    top.linkTo(button7.top)
                }
        ) {
            calculateViewModel?.onAction(CalculateAction.InputChange("9"))
        }
        NumberButton(
            symbol = "x",
            colorBackground = MaterialTheme.colorScheme.surface,
            colorFont = MaterialTheme.colorScheme.onSecondary,
            modifier = Modifier
                .constrainAs(buttonX) {
                    top.linkTo(button7.top)
                    bottom.linkTo(button7.bottom)
                    end.linkTo(firstSpacer.end)
                }
                .width(70.dp)
        ) {
            calculateViewModel?.onAction(CalculateAction.InputChange("*"))
        }
        Spacer(modifier = Modifier
            .height(20.dp)
            .fillMaxWidth()
            .constrainAs(secondSpacer) {
                start.linkTo(parent.start)
                top.linkTo(button7.bottom)
                end.linkTo(parent.end)
            })
        NumberButton(
            symbol = "4",
            colorBackground = MaterialTheme.colorScheme.tertiary,
            colorFont = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier
                .width(70.dp)
                .constrainAs(button4) {
                    start.linkTo(parent.start)
                    top.linkTo(secondSpacer.bottom)
                }
        ) {
            calculateViewModel?.onAction(CalculateAction.InputChange("4"))
        }
        NumberButton(
            symbol = "5",
            colorBackground = MaterialTheme.colorScheme.tertiary,
            colorFont = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier
                .width(contentWidthCommon)
                .constrainAs(button5) {
                    top.linkTo(button4.top)
                    bottom.linkTo(button4.bottom)
                }
        ) {
            calculateViewModel?.onAction(CalculateAction.InputChange("5"))
        }
        NumberButton(
            symbol = "6",
            colorBackground = MaterialTheme.colorScheme.tertiary,
            colorFont = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier
                .width(contentWidthCommon)
                .constrainAs(button6) {
                    top.linkTo(button4.top)
                    bottom.linkTo(button4.bottom)
                }
        ) {
            calculateViewModel?.onAction(CalculateAction.InputChange("6"))
        }
        NumberButton(
            symbol = "-",
            colorBackground = MaterialTheme.colorScheme.surface,
            colorFont = MaterialTheme.colorScheme.onSecondary,
            modifier = Modifier
                .width(contentWidthCommon)
                .constrainAs(buttonMin) {
                    top.linkTo(button4.top)
                    bottom.linkTo(button4.bottom)
                    end.linkTo(parent.end)
                }
        ) {
            calculateViewModel?.onAction(CalculateAction.InputChange("-"))
        }
        Spacer(modifier = Modifier
            .height(20.dp)
            .fillMaxWidth()
            .constrainAs(thirdSpacer) {
                start.linkTo(parent.start)
                top.linkTo(button4.bottom)
                end.linkTo(parent.end)
            })
        NumberButton(
            symbol = "1",
            colorBackground = MaterialTheme.colorScheme.tertiary,
            colorFont = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier
                .width(contentWidthCommon)
                .constrainAs(button1) {
                    start.linkTo(parent.start)
                    top.linkTo(thirdSpacer.bottom)
                }
        ) {
            calculateViewModel?.onAction(CalculateAction.InputChange("1"))
        }
        NumberButton(
            symbol = "2",
            colorBackground = MaterialTheme.colorScheme.tertiary,
            colorFont = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier
                .width(contentWidthCommon)
                .constrainAs(button2) {
                    top.linkTo(button1.top)
                    bottom.linkTo(button1.bottom)
                }
        ) {
            calculateViewModel?.onAction(CalculateAction.InputChange("2"))
        }
        NumberButton(
            symbol = "3",
            colorBackground = MaterialTheme.colorScheme.tertiary,
            colorFont = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier
                .width(contentWidthCommon)
                .constrainAs(button3) {
                    top.linkTo(button1.top)
                    bottom.linkTo(button1.bottom)
                }
        ) {
            calculateViewModel?.onAction(CalculateAction.InputChange("3"))
        }
        NumberButton(
            symbol = "+",
            colorBackground = MaterialTheme.colorScheme.surface,
            colorFont = MaterialTheme.colorScheme.onSecondary,
            modifier = Modifier
                .width(contentWidthCommon)
                .constrainAs(buttonSum) {
                    top.linkTo(button1.top)
                    bottom.linkTo(button1.bottom)
                    end.linkTo(parent.end)
                }
        ) {
            calculateViewModel?.onAction(CalculateAction.InputChange("+"))
        }
        Spacer(modifier = Modifier
            .height(20.dp)
            .fillMaxWidth()
            .constrainAs(fourthSpacer) {
                start.linkTo(parent.start)
                top.linkTo(button1.bottom)
                end.linkTo(parent.end)
            })
        NumberButton(
            symbol = "0",
            colorBackground = MaterialTheme.colorScheme.tertiary,
            colorFont = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier
                .width(140.dp)
                .constrainAs(button0) {
                    start.linkTo(parent.start)
                    top.linkTo(fourthSpacer.bottom)
                }
        ) {
            calculateViewModel?.onAction(CalculateAction.InputChange("0"))
        }
        NumberButton(
            symbol = ".",
            colorBackground = MaterialTheme.colorScheme.tertiary,
            colorFont = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier
                .width(contentWidthCommon)
                .constrainAs(buttonDot) {
                    top.linkTo(button0.top)
                    bottom.linkTo(button0.bottom)
                }
        ) {
            calculateViewModel?.onAction(CalculateAction.InputChange("."))
        }
        NumberButton(
            symbol = "^",
            colorBackground = MaterialTheme.colorScheme.surface,
            colorFont = MaterialTheme.colorScheme.onSecondary,
            modifier = Modifier
                .width(contentWidthCommon)
                .constrainAs(buttonSquare) {
                    top.linkTo(button0.top)
                    bottom.linkTo(button0.bottom)
                }
        ) {
            calculateViewModel?.onAction(CalculateAction.InputChange("^"))
        }
        NumberButton(
            symbol = "←",
            colorBackground = MaterialTheme.colorScheme.surface,
            colorFont = MaterialTheme.colorScheme.onSecondary,
            modifier = Modifier
                .width(contentWidthCommon)
                .constrainAs(buttonDel) {
                    top.linkTo(fifthSpacer.bottom)
                    start.linkTo(parent.start)
                }
        ) {
            calculateViewModel?.onAction(CalculateAction.Delete)
        }
        Spacer(modifier = Modifier
            .height(20.dp)
            .fillMaxWidth()
            .constrainAs(fifthSpacer) {
                start.linkTo(parent.start)
                top.linkTo(button0.bottom)
                end.linkTo(parent.end)
            })
        NumberButton(
            symbol = "=",
            colorBackground = MaterialTheme.colorScheme.background,
            colorFont = Color.White,
            modifier = Modifier
                .width(210.dp)
                .constrainAs(buttonEqual) {
                    end.linkTo(parent.end)
                    top.linkTo(buttonDel.top)
                    bottom.linkTo(buttonDel.bottom)
                }
        ) {
            calculateViewModel?.onAction(CalculateAction.Evaluate)
        }
    }
}