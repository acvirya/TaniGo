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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tanigo.app.ui.theme.Dimens
import com.tanigo.app.ui.theme.TaniGoTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.tanigo.app.ui.theme.Shapes

import com.tanigo.app.ui.components.BrandHeader
import com.tanigo.app.ui.components.SuccessDialog

@Composable
fun RegisterScreen(navController: NavController){
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = Dimens.screenHorizontal)
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Spacer(modifier = Modifier.height(60.dp))
            Spacer(modifier = Modifier.weight(0.4f))

            BrandHeader()

            Spacer(modifier = Modifier.height(60.dp))

            RegisterForm(navController)

            Spacer(modifier = Modifier.weight(1f))
            Spacer(modifier = Modifier.height(60.dp))
        }

    }
}

@Composable
fun RegisterForm(navController: NavController){
    var username by remember { mutableStateOf("") }
    var email by remember  { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var visible by remember { mutableStateOf(false) }
    var showSuccessDialog by remember { mutableStateOf(false) }


    Box {
        // 2. Buat lapisan bayangan (digambar pertama kali, jadi di belakang)
        Box(
            modifier = Modifier
                // Membuat ukuran bayangan sama dengan ukuran konten
                .matchParentSize()
                // Geser bayangan ke kanan bawah
                .offset(y = 4.dp)
                // Beri warna hitam transparan dan bentuk yang sama
                .background(
                    color = Color.Black.copy(alpha = 0.08f),
                    shape = Shapes.large
                )
        )

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .background(Color.White, shape = Shapes.large)
                .padding(vertical = Dimens.spacingExtraLarge, horizontal = Dimens.spacingLarge),
        ) {
            Text(
                text = "Register",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.tertiary,
            )

            OutlinedTextField(
                value = username,
                onValueChange = { username = it },
                label = { Text(text = "Username") },
                placeholder = { Text("Enter your username") },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = Dimens.spacingMedium),
                shape = Shapes.medium,
                colors = OutlinedTextFieldDefaults.colors(
                    cursorColor = MaterialTheme.colorScheme.onSurface, // Color of the cursor
                    unfocusedPlaceholderColor = Color.Gray,
                    focusedPlaceholderColor = Color.Gray,
                )
            )

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text(text = "Email") },
                placeholder = { Text("Enter your email") },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = Dimens.spacingExtraSmall),
                shape = Shapes.medium,
                colors = OutlinedTextFieldDefaults.colors(
                    cursorColor = MaterialTheme.colorScheme.onSurface, // Color of the cursor
                    unfocusedPlaceholderColor = Color.Gray,
                    focusedPlaceholderColor = Color.Gray,
                )
            )

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text(text = "Password",) },
                placeholder = { Text("Enter your password") },
                singleLine = true,
                visualTransformation = if (visible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    val image =
                        if (visible) Icons.Default.Visibility else Icons.Default.VisibilityOff
                    IconButton(
                        onClick = { visible = !visible },
                        Modifier.padding(end = Dimens.spacingExtraSmall)
                    ) {
                        Icon(
                            imageVector = image,
                            contentDescription = if (visible) "Hide" else "Show"
                        )
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = Dimens.spacingExtraSmall),
                shape = Shapes.medium,
                colors = OutlinedTextFieldDefaults.colors(
                    cursorColor = MaterialTheme.colorScheme.onSurface, // Color of the cursor
                    unfocusedPlaceholderColor = Color.Gray,
                    focusedPlaceholderColor = Color.Gray,
                )
            )

            OutlinedTextField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                label = { Text(text = "Confirm Password",) },
                placeholder = { Text("Enter your password") },
                singleLine = true,
                visualTransformation = if (visible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    val image =
                        if (visible) Icons.Default.Visibility else Icons.Default.VisibilityOff
                    IconButton(
                        onClick = { visible = !visible },
                        Modifier.padding(end = Dimens.spacingExtraSmall)
                    ) {
                        Icon(
                            imageVector = image,
                            contentDescription = if (visible) "Hide" else "Show"
                        )
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = Dimens.spacingExtraSmall),
                shape = Shapes.medium,
                colors = OutlinedTextFieldDefaults.colors(
                    cursorColor = MaterialTheme.colorScheme.onSurface, // Color of the cursor
                    unfocusedPlaceholderColor = Color.Gray,
                    focusedPlaceholderColor = Color.Gray,
                )
            )

            Spacer(modifier = Modifier.height(Dimens.spacingLarge))

            Button(
                onClick = { showSuccessDialog = true },
                modifier = Modifier.fillMaxWidth()
                    .height(Dimens.heightMedium),
                shape = Shapes.medium
            ) {
                Text(
                    text = "Register",
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Medium
                )
            }


            Spacer(modifier = Modifier.height(Dimens.spacingSmall))

            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Already have an account? ",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = "Login",
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.clickable {
                        navController.navigate("login")
                    }
                )
            }
        }
    }

    if (showSuccessDialog) {
        SuccessDialog(
            message = "Register success, please login with your new account",
            buttonLabel = "Continue",
            onDismiss = { showSuccessDialog = false; navController.navigate("login") }
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