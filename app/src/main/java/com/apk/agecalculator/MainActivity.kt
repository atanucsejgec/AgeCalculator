package com.apk.agecalculator



import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                AgeCalculatorScreen()
            }
        }
    }
}

@Composable
fun AgeCalculatorScreen() {
    // LESSON: var = mutable, val = immutable
    // "by remember" keeps state across recompositions
    var birthYear by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("🎂 Age Calculator", fontSize = 28.sp)

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = birthYear,
            onValueChange = { birthYear = it },  // LESSON: var can be reassigned
            label = { Text("Enter birth year") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            // LESSON: Nullable type - toIntOrNull() returns Int?
            val year: Int? = birthYear.toIntOrNull()

            // LESSON: if/else expression
            result = if (year == null) {
                "Please enter a valid number"
            } else {
                val currentYear = 2026     // LESSON: val = cannot change
                val age = currentYear - year

                // LESSON: when expression (like switch)
                val category: String = when {
                    age < 0    -> "Not born yet! 🤔"
                    age < 13   -> "Child 👶"
                    age < 18   -> "Teenager 🧑"
                    age < 60   -> "Adult 🧑‍💼"
                    else       -> "Senior 👴"
                }

                // LESSON: String templates with $variable and ${expression}
                "You are $age years old.\nCategory: $category"
            }
        }) {
            Text("Calculate Age")
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(text = result, fontSize = 18.sp)
    }
}