package com.gunhansancar.mars

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gunhansancar.mars.game.Simulator
import com.gunhansancar.mars.game.input.InputParser
import com.gunhansancar.mars.ui.theme.MarsGameTheme

class MainActivity : ComponentActivity() {
    private val inputParser = InputParser()
    private val simulator = Simulator()

    private val padding = 16.dp
    private val half = 8.dp
    private val zero = 0.dp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MarsGameTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(Modifier.padding(padding)) {
                        Text(
                            text = "Martian Robots!",
                        )
                        UserInput()

                    }
                }
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun UserInput() {
        val focusManager = LocalFocusManager.current

        var outputText by remember {
            mutableStateOf("")
        }
        var userInput by remember {
            mutableStateOf(
                """
            5 3
            1 1 E 
            RFRFRFRF
            
            3 2 N 
            FRRFLLFFRRFLL
            
            0 3 W 
            LLFFFLFLFL
            
    """.trimIndent()
            )
        }

        OutlinedTextField(
            value = userInput,
            onValueChange = { userInput = it },
            label = { Text("Input") }
        )
        Button(
            modifier = Modifier.padding(zero, half, zero, half),
            onClick = {
                focusManager.clearFocus()
                outputText = simulate(userInput)
            }) {
            Text(text = "Simulate")
        }
        Text(
            text = outputText,
        )
    }

    private fun simulate(input: String): String {
        val result = "Invalid data"
        val data = inputParser.parse(input) ?: return result
        return simulator.simulate(data).ifEmpty { "No valid data" }
    }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MarsGameTheme {
        Greeting("Android")
    }
}