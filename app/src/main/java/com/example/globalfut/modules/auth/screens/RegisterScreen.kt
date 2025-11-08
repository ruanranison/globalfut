package com.example.globalfut.modules.auth.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.globalfut.R
import com.example.globalfut.core.ui.theme.GFPrimary

@Composable
fun RegisterScreen(
    onNavigateBack: () -> Unit = {},
    onRegisterClick: () -> Unit = {}
) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // 🔹 Topo verde com logo
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(280.dp)
                .background(
                    color = GFPrimary,
                    shape = RoundedCornerShape(bottomStart = 100.dp, bottomEnd = 100.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo_globalfut),
                contentDescription = "Logo GlobalFut",
                modifier = Modifier
                    .height(120.dp)
                    .width(160.dp)
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        // 🔹 Campos de formulário
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            placeholder = { Text("Digite seu nome completo") },
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .padding(vertical = 6.dp),
            singleLine = true
        )

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            placeholder = { Text("Digite seu Email") },
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .padding(vertical = 6.dp),
            singleLine = true
        )

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            placeholder = { Text("Digite sua senha") },
            visualTransformation = PasswordVisualTransformation(),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .padding(vertical = 6.dp),
            singleLine = true
        )

        OutlinedTextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            placeholder = { Text("Confirme sua senha") },
            visualTransformation = PasswordVisualTransformation(),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .padding(vertical = 6.dp),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(24.dp))

        // 🔹 Botão principal
        Button(
            onClick = onRegisterClick,
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .height(52.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = GFPrimary,
                contentColor = Color.White
            )
        ) {
            Text("CADASTRAR", fontSize = 16.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 🔹 Voltar para login
        TextButton(onClick = onNavigateBack) {
            Text("Já possui uma conta? Entrar", color = GFPrimary)
        }
    }
}
