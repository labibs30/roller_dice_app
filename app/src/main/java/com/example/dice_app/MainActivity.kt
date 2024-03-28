package com.example.dice_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.dice_app.ui.theme.Dice_appTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.sp
import java.text.DecimalFormat

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Dice_appTheme {
                DiceRollerApp()
            }

        }
    }
}
@Preview
@Composable
fun DiceRollerApp(){
    DiceWithButtonAndImage(modifier = Modifier
        .fillMaxSize()
        .wrapContentSize(Alignment.Center)
    )
}

@Composable
fun DiceWithButtonAndImage(modifier:Modifier = Modifier
    .fillMaxSize()
    .wrapContentSize(Alignment.Center)
){
    var result by remember { mutableStateOf(1) }
    var count: Int by remember {mutableStateOf(0)}
    var total: Int by remember {mutableStateOf(0)}
    var average: Double by remember { mutableStateOf(0.0)}
    var stringValue: String by remember {mutableStateOf("")}
    val imageResource = when (result) {
        1 -> R.drawable.dice_1
        2 -> R.drawable.dice_2
        3 -> R.drawable.dice_3
        4 -> R.drawable.dice_4
        5 -> R.drawable.dice_5
        else -> R.drawable.dice_6
    }
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 40.dp)
            .padding(top = 25.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Row(modifier = Modifier.fillMaxWidth(), Arrangement.SpaceBetween) {
            Text(text = "Counter: $count", fontSize = 18.sp)
            Text(text = "Total: $total", fontSize = 18.sp)
            Text(text = "Average: $average", fontSize = 18.sp)
        }

        Spacer(modifier = Modifier.height(14.dp))

        Button(onClick = {
            count = 0
            total = 0
            average= 0.0},
            modifier = Modifier
                .width(90.dp)
                .height(40.dp)
                .alpha(0.8F)
        ) {
            Text(text = "Reset")
        }
    }
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(stringValue, fontSize = 24.sp)
        Image(
            painter = painterResource(imageResource),
            contentDescription = result.toString()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text("Click Button to Roll Dice")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {   result = (1..6).random()
            count++
            result = (1..6).random()
            total += result
            val df = DecimalFormat("#.##")
            average = df.format((total.toDouble()/count.toDouble())).toDouble()
            stringValue = "You rolled a ${result}!"

        }) {
            Text(stringResource(R.string.roll))
        }
    }
}