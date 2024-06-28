package com.example.coffeemasters.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.coffeemasters.DataManager
import com.example.coffeemasters.Product
import com.example.coffeemasters.ui.theme.Alternative1
import com.example.coffeemasters.ui.theme.CardBackground
import com.example.coffeemasters.ui.theme.Primary

fun Double.format(digits: Int) = "%.${digits}f".format(this)

@Composable
fun ProductItem(product: Product, onAdd: (Product)->Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
    ) {

        AsyncImage(
            model = product.imageUrl,
            contentDescription = "Image for ${product.name}",
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
        )
        Row (
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Column {
                Text(product.name, fontWeight = FontWeight.Bold)
                Text("$${product.price.format(2)} ea")
            }
            Button(

                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.White,
                    containerColor = Alternative1,
                ),
                onClick = {
                    onAdd(product)
                },
            ) {
                Text(text = "Add")
            }
        }
    }
}

@Composable
fun MenuPage(dataManager: DataManager, modifier: Modifier = Modifier) {
    LazyColumn (
        modifier = modifier
    ) {
        items(dataManager.menu) {
            Text(text = it.name,
                color = Primary,
                modifier = Modifier.padding(10.dp, 20.dp, 10.dp, 10.dp))
            it.products.forEach {
                Card(
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 2.dp
                    ),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .background(CardBackground)
                        .padding(12.dp)
                ) {
                    ProductItem(product = it, onAdd = {
                        dataManager.cartAdd(it)
                    })
                }
            }
        }
    }
}