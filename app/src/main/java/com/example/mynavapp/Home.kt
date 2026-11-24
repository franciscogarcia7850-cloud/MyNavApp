package com.example.mynavapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mynavapp.ui.theme.MyNavAppTheme

@Composable
fun Home(
    onNavigationProfile: (id: String, name: String) -> Unit,
    modifier: Modifier = Modifier
) {
    // rememberSaveable conserva lo escrito al rotar la pantalla
    var userId by rememberSaveable { mutableStateOf("") }
    var userName by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(all = 24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text (
            text = "App Home",
            fontSize = 24.sp
        )

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = userId,
            onValueChange = {userId = it},
            label = {Text("ID Usuario")},
            singleLine = true
        )

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = userName,
            onValueChange = {userName = it},
            label = {Text("Nombre")},
            singleLine = true
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button (
            onClick = {
                if(userId.isNotBlank() && userName.isNotBlank()) {
                    onNavigationProfile(userId, userName)
                }
            },
            enabled = userId.isNotBlank() && userName.isNotBlank()
        ) {
           Text("Ir al Perfil")
        }

    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomePreview() {
    MyNavAppTheme {
        Home(onNavigationProfile = { _, _ -> })
    }
}
