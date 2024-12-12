package com.example.wish_android_ex2

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.wish_android_ex2.ui.theme.DecimalPeriodTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DecimalPeriodTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen() {
    var numerator by remember { mutableStateOf("") }
    var denominator by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }

    val context = LocalContext.current


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = numerator,
            onValueChange = { numerator = it },
            label = { Text(stringResource(id = R.string.enter_numerator)) },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = denominator,
            onValueChange = { denominator = it },
            label = { Text(stringResource(id = R.string.enter_denominator)) },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                val num = numerator.toIntOrNull()
                val den = denominator.toIntOrNull()

                if (num != null && den != null && den != 0) {
                    val calculator = DecimalPeriodCalculator()
                    result = calculator.findDecimalPeriod(num, den)
                } else {
                    Toast.makeText(context, context.getString(R.string.error_message), Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(id = R.string.calculate))
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = if (result.isNotEmpty()) stringResource(id = R.string.result, result) else "",
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

fun findDecimalPeriod(numerator: Int, denominator: Int): String {
    val remainders = mutableMapOf<Int, Int>()
    var remainder = numerator % denominator
    val decimals = StringBuilder()
    var position = 0

    while (remainder != 0 && !remainders.contains(remainder)) {
        remainders[remainder] = position
        remainder *= 10
        decimals.append(remainder / denominator)
        remainder %= denominator
        position++
    }

    return if (remainder == 0) {
        "0" // Дробь конечна
    } else {
        val start = remainders[remainder] ?: 0
        val nonRepeating = decimals.substring(0, start)
        val repeating = decimals.substring(start)
        "$nonRepeating($repeating)"
    }
}
