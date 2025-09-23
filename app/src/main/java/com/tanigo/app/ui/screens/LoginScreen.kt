package com.tanigo.app.ui.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.compose.material3.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.*
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.tanigo.app.R
import androidx.compose.ui.unit.dp
import com.tanigo.app.ui.theme.Dimens
import com.tanigo.app.ui.theme.TaniGoTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import com.tanigo.app.ui.theme.Shapes

@Composable
fun LoginScreen(navController: NavController){
    Surface(
        modifier = Modifier.fillMaxSize().padding(horizontal = Dimens.screenHorizontal)
    ){
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            BrandHeader()
            Spacer(modifier = Modifier.height(Dimens.spacingMedium))
            LoginForm()
        }

    }
}

@Composable
fun BrandHeader(){
    Row(){
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo TaniGo",
            modifier = Modifier.size(80.dp), // atur ukuran
            contentScale = ContentScale.Fit,   // atur scaling (Crop, Fit, FillBounds)
            alignment = Alignment.Center

        )
        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.tertiary)) {
                    append("Tani")
                }
                withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.secondary)) {
                    append("Go")
                }
            },
            modifier = Modifier.align(Alignment.Bottom),
            style = MaterialTheme.typography.titleLarge,
            fontSize = 40.sp,
            textAlign = TextAlign.Center
        )

    }
}

@Composable
fun LoginForm(){
    var text by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var visible by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = text,
        onValueChange = { text = it },
        label = { Text("Username") },
        placeholder = { Text("Enter your username") },
        singleLine = true,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = Dimens.spacingExtraSmall),
        shape = Shapes.medium
    )
    OutlinedTextField(
        value = password,
        onValueChange = { password = it },
        label = { Text(text="Password",) },
        singleLine = true,
        visualTransformation = if (visible) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            val image = if (visible) Icons.Default.Visibility else Icons.Default.VisibilityOff
            IconButton(onClick = { visible = !visible }, Modifier.padding(end=Dimens.spacingExtraSmall)) {
                Icon(image, contentDescription = if (visible) "Hide" else "Show")
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = Dimens.spacingExtraSmall),
        shape = Shapes.medium
    )

    Spacer(modifier = Modifier.height(Dimens.spacingMedium))

    Button(
        onClick = { /* TODO: handle login */ },
        modifier = Modifier.fillMaxWidth()
            .height(Dimens.buttonHeightMedium),
        shape = Shapes.medium
    ) {
        Text("Login")
    }


    Spacer(modifier = Modifier.height(Dimens.spacingExtraSmall))

    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "Don't have an account? ",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.tertiary
        )

        Text(
            text = "Register",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.secondary,
            modifier = Modifier.clickable {
//                navController.navigate("register")
            }
        )
    }

}

@Preview (showBackground = true, showSystemUi = true)
@Composable
fun LoginScreenPreview(){
    TaniGoTheme {
        LoginScreen(navController = NavController(LocalContext.current))
    }
}