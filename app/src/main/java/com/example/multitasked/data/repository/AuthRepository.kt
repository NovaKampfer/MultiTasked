package com.example.multitasked.data.repository

import com.example.multitasked.data.model.User

interface AuthRepository {
    fun currentUserId(): String?
    fun logout()
    suspend fun login(email: String, password: String): String
    suspend fun register(email: String, password: String): String
    suspend fun getCurrentUser(): User?
    suspend fun updateUserName(newName: String)
}
