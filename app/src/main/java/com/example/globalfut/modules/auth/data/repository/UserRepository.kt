package com.example.globalfut.modules.auth.data.repository

import com.example.globalfut.modules.auth.data.remote.UserService

class UserRepository(private val api: UserService) {

    suspend fun login(username: String, password: String): Boolean {
        val users = api.getUsers()
        val user = users.find { it.username == username && it.password == password }
        return user != null
    }
}
