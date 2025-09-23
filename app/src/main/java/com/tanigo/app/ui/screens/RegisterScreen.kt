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

import com.tanigo.app.ui.components.BrandHeader

@Composable
fun RegisterScreen(navController: NavController){
    Surface(
        modifier = Modifier.fillMaxSize().padding(horizontal = Dimens.screenHorizontal)
    ){
        Column(
            modifier = Modifier.fillMaxSize()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Spacer(modifier = Modifier.height(60.dp))
            Spacer(modifier = Modifier.weight(0.4f))

            BrandHeader()

            Spacer(modifier = Modifier.height(60.dp))

            Text(
                text = "Register",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.tertiary,
            )

            RegisterForm(navController)
            Spacer(modifier = Modifier.weight(1f))
            Spacer(modifier = Modifier.height(60.dp))
        }

    }
}

@Composable
fun RegisterForm(navController: NavController){
    var text by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var visible by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = text,
        onValueChange = { text = it },
        label = { Text("Username") },
        placeholder = { Text("Enter your username") },
        singleLine = true,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = Dimens.spacingMedium),
        shape = Shapes.medium
    )
    OutlinedTextField(
        value = text,
        onValueChange = { text = it },
        label = { Text("Email") },
        placeholder = { Text("Enter your email") },
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

    OutlinedTextField(
        value = confirmPassword,
        onValueChange = { confirmPassword = it },
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
        Text("Register")
    }


    Spacer(modifier = Modifier.height(Dimens.spacingExtraSmall))

    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "Already have an account? ",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.tertiary
        )

        Text(
            text = "Login",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.secondary,
            modifier = Modifier.clickable {
                navController.navigate("login")
            }
        )
    }

}

@Preview (showBackground = true, showSystemUi = true)
@Composable
fun RegisterScreenPreview(){
    TaniGoTheme {
        RegisterScreen(navController = NavController(LocalContext.current))
    }
}