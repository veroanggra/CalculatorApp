package com.veroanggra.calculatorapp.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.veroanggra.calculatorapp.ui.theme.lightFontYellow
import com.veroanggra.calculatorapp.ui.theme.lightTransparentYellow

@Composable
fun NumberButton(
    modifier: Modifier = Modifier,
    symbol: String,
    colorBackground: Color,
    colorFont: Color,
    onClick: () -> Unit
) {
    Box(contentAlignment = Alignment.Center,
        modifier = Modifier
            .clip(RoundedCornerShape(50.dp))
            .background(color = colorBackground)
            .clickable { onClick() }
            .padding(start = 6.dp, end = 6.dp, top = 9.dp, bottom = 9.dp)
            .then(modifier)
    ) {
        Text(
            text = symbol,
            fontWeight = FontWeight.SemiBold,
            fontSize = 30.sp,
            color = colorFont
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ButtonPreview() {
    NumberButton(
        symbol = "(",
        colorBackground = lightTransparentYellow,
        colorFont = lightFontYellow,
        modifier = Modifier
            .width(70.dp)
    ){}
}