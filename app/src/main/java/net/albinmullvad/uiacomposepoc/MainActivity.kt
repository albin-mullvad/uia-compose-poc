package net.albinmullvad.uiacomposepoc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import net.albinmullvad.uiacomposepoc.ui.theme.UiacomposepocTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UiacomposepocTheme {
                MyApp()
            }
        }
    }
}

@Composable
fun MyApp() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "main") {
        composable("main") {
            MainScreen(onOpenSecondary = {
                navController.navigate("secondary")
            })
        }
        composable("secondary") { SecondaryScreen(onBack = { navController.popBackStack() }) }
    }
}

@Composable
fun MainScreen(onOpenSecondary: () -> Unit) {
    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
        Column {
            Text("Main screen says hello!")
            Button(onClick = onOpenSecondary) {
                Text(text = "Open secondary")
            }
        }
    }
}

@Composable
fun SecondaryScreen(onBack: () -> Unit) {
    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
        Column {
            Text("Secondary screen also says hello!")
            Button(onClick = onBack) {
                Text(text = "Back")
            }
        }
    }
}
