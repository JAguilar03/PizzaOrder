// Jacob Aguilar
// CST-416
// Hamburger App

package com.jacobaguilar.helloworld

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.jacobaguilar.helloworld.ui.theme.HelloWorldTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HelloWorldTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ButtonVarietyApp()
                }
            }
        }
    }
}

@Composable
fun ButtonVarietyApp() {

    // Create values and variables
    val context = LocalContext.current
    var textState by remember { mutableStateOf(TextFieldValue()) }
    var pepperoni by remember { mutableStateOf(false) }
    var sausage by remember { mutableStateOf(false) }
    var mushrooms by remember { mutableStateOf(false) }
    var size by remember { mutableStateOf("Small") }
    var sauceAmount by remember { mutableStateOf(0f) }
    var peppers by remember { mutableStateOf(false) }
    var side by remember { mutableStateOf("Breadsticks") }
    var orderSummary by remember { mutableStateOf("") }

    Surface {
        Column(modifier = Modifier.padding(16.dp)) {
            // Order a pizza
            Text("Order a pizza!", style = MaterialTheme.typography.bodySmall)

            // Divider with default styling
            Divider(modifier = Modifier.padding(vertical = 8.dp))

            // Create a text input box for user to input name
            TextField(
                value = textState,
                onValueChange = { textState = it },
                label = { Text("Your Name") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )

            // Create 3 checkboxes horizontally (options are pepperoni, sausage, and mushrooms)
            Text("Toppings:")
            Row(modifier = Modifier.padding(vertical = 8.dp)) {
                Checkbox(checked = pepperoni, onCheckedChange = { pepperoni = it })
                Text("Pepperoni", modifier = Modifier.padding(end = 8.dp))

                Checkbox(checked = sausage, onCheckedChange = { sausage = it })
                Text("Sausage", modifier = Modifier.padding(end = 8.dp))

                Checkbox(checked = mushrooms, onCheckedChange = { mushrooms = it })
                Text("Mushrooms")
            }

            // Create 3 radio buttons horizontally (options are small medium or large)
            Text("Pick a size:")
            Row(modifier = Modifier.padding(vertical = 8.dp)) {
                RadioButton(selected = size == "Small", onClick = { size = "Small" })
                Text("Small", modifier = Modifier.padding(end = 8.dp))

                RadioButton(selected = size == "Medium", onClick = { size = "Medium" })
                Text("Medium", modifier = Modifier.padding(end = 8.dp))

                RadioButton(selected = size == "Large", onClick = { size = "Large" })
                Text("Large")
            }

            // Create a Slider to show how much sauce should be on the pizza
            Text("Amount of Sauce:")
            Slider(
                value = sauceAmount,
                onValueChange = { sauceAmount = it },
                valueRange = 0f..100f,
                modifier = Modifier.padding(vertical = 8.dp)
            )

            // Display the percentage of the slider (amount of sauce 0-100%)
            Text("Amount of Sauce: ${sauceAmount.toInt()}%")

            // Create Toggle Switch for if they would like peppers
            Text("Add Peppers:")
            Row(modifier = Modifier.padding(vertical = 8.dp)) {
                Switch(checked = peppers, onCheckedChange = { peppers = it })
                Text(if (peppers) "Yes" else "No", modifier = Modifier.padding(start = 8.dp))
            }

            // Create drop down menu to show sides (breadsticks, salad, wings)
            Text("Choose a Side:")
            var expanded by remember { mutableStateOf(false) }
            Box {
                OutlinedButton(onClick = { expanded = true }) {
                    Text(side)
                }
                DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                    DropdownMenuItem(text = { Text("Breadsticks") }, onClick = {
                        side = "Breadsticks"
                        expanded = false
                    })
                    DropdownMenuItem(text = { Text("Salad") }, onClick = {
                        side = "Salad"
                        expanded = false
                    })
                    DropdownMenuItem(text = { Text("Wings") }, onClick = {
                        side = "Wings"
                        expanded = false
                    })
                }
            }

            // Order button that will update the order list
            Button(
                onClick = {
                    orderSummary = "Name: ${textState.text}\n" +
                            "Toppings: ${if (pepperoni) "Pepperoni " else ""}${if (sausage) "Sausage " else ""}${if (mushrooms) "Mushrooms" else ""}\n" +
                            "Size: $size\n" +
                            "Sauce: ${sauceAmount.toInt()}%\n" +
                            "Peppers: ${if (peppers) "Yes" else "No"}\n" +
                            "Side: $side"

                    Toast.makeText(context, "Order placed!", Toast.LENGTH_LONG).show()
                },
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(top = 8.dp)
            ) {
                Text("Order")
            }

            // Display results of order
            if (orderSummary.isNotEmpty()) {
                Text("Order Summary:", style = MaterialTheme.typography.bodyMedium, modifier = Modifier.padding(top = 16.dp))
                Text(orderSummary, style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}
