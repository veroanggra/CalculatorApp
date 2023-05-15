package com.veroanggra.calculatorapp.arithmatic

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.veroanggra.calculatorapp.DataStoreUtil
import com.veroanggra.calculatorapp.R
import kotlinx.coroutines.launch

@Composable
fun SwitchThemeButton(
    modifier: Modifier = Modifier,
    dataStoreUtil: DataStoreUtil, calculateViewModel: CalculateViewModel
) {
    val coroutineScope = rememberCoroutineScope()
    var switchState by remember {
        calculateViewModel.isDarkEnabled
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp)
    ) {
        Switch(checked = switchState, onCheckedChange = {
            switchState = it
            coroutineScope.launch {
                dataStoreUtil.saveTheme(it)
            }
        }, thumbContent = {
            Icon(
                modifier = modifier.size(SwitchDefaults.IconSize),
                contentDescription = null,
                painter = if (switchState) painterResource(
                    R.drawable.dark
                ) else painterResource(id = R.drawable.light)
            )
        }, colors = SwitchDefaults.colors(
            checkedTrackColor = MaterialTheme.colorScheme.onSecondary,
            checkedThumbColor = MaterialTheme.colorScheme.background
        )
        )
    }
}