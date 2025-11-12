package com.example.globalfut.modules.auth.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.globalfut.R
import com.example.globalfut.core.network.RetrofitInstance
import com.example.globalfut.core.ui.theme.GFPrimary
import com.example.globalfut.modules.auth.data.repository.UserRepository
import com.example.globalfut.modules.auth.viewmodel.LoginState
import com.example.globalfut.modules.auth.viewmodel.LoginViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit
) {
    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setStatusBarColor(GFPrimary, darkIcons = false)
    }

    val viewModel = remember {
        LoginViewModel(
            UserRepository(RetrofitInstance.userService)
        )
    }

    val state by viewModel.state.collectAsState()

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(380.dp)
                .clip(
                    GenericShape { size, _ ->
                        moveTo(0f, 0f)
                        lineTo(0f, size.height * 0.85f)
                        quadraticBezierTo(
                            size.width / 2, size.height * 1.15f,
                            size.width, size.height * 0.85f
                        )
                        lineTo(size.width, 0f)
                        close()
                    }
                )
                .background(GFPrimary),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo_sem_fundo),
                contentDescription = "Logo Global Fut",
                modifier = Modifier
                    .width(306.dp)
                    .height(204.dp)
                    .clip(RoundedCornerShape(12.dp))
            )
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
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

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = { viewModel.login(email, password) },
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .height(52.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = GFPrimary,
                        contentColor = Color.White
                    )
                ) {
                    if (state is LoginState.Loading)
                        CircularProgressIndicator(color = Color.White, strokeWidth = 2.dp)
                    else
                        Text("ENTRAR", fontSize = 16.sp)
                }

                when (state) {
                    is LoginState.Success -> onLoginSuccess()
                    is LoginState.Error -> {
                        Text(
                            text = (state as LoginState.Error).message,
                            color = Color.Red,
                            modifier = Modifier.padding(top = 8.dp)
                        )
                    }
                    else -> {}
                }
            }
        }
    }
}
