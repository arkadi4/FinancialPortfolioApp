package com.example.financialportfolioapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.compose.AppTheme
import com.example.financialportfolioapp.navigation.AppNavWithLocalNavController
import dagger.hilt.android.AndroidEntryPoint

inline fun example1(a: Int, b: Int): Int {
    return a + b
}

inline fun inlineEx(body: () -> String) {
    Log.e("YYY","inlineEx: " + body.invoke())
}

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                Surface {
                    val x1 = example1(5, 6)
                    Log.e("YYY", "x= $x1")
                    inlineEx { "XXX" }
                    AppNavWithLocalNavController()
//                    BadScreen()
                }
            }
        }
    }
}

@Composable
fun BadScreen() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            "123"
        )
        Text("456")

        FloatingActionButton(
            onClick = { /*TODO*/ },
            modifier = Modifier.align(Alignment.CenterEnd)
        ) {
            Text("button")
        }

    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewBadScreen() {
    BadScreen()
}