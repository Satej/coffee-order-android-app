package com.example.coffeemasters

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.coffeemasters.ui.theme.Alternative2
import com.example.coffeemasters.ui.theme.CoffeeMastersTheme
import com.example.coffeemasters.ui.theme.Primary
import com.example.coffeemasters.ui.theme.Purple40

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App() {
    val selectedRoute = remember {
        mutableStateOf(Routes.MenuPage.route)
    }
    Scaffold(
        topBar = {
            TopAppBar(title = { AppTitle() })
        },
        bottomBar = {
            NavBar(
                selectedRoute = selectedRoute.value,
                onChange = { selectedRoute.value = it }
            )
        }
    ) { innerPadding ->
        OffersPage(
            Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun AppTitle() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)
            .background(Primary)
    ) {
        Image(painter = painterResource(id = R.drawable.logo), contentDescription = "Coffee Masters Logo")
    }
}

@Preview(showBackground = true, widthDp = 400)
@Composable
private fun App_Preview() {
    CoffeeMastersTheme {
        App()
    }
}