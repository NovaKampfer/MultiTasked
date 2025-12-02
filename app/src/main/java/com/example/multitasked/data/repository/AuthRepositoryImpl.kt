package com.example.multitasked.data.repository

import com.example.multitasked.data.model.User
import com.example.multitasked.data.remote.FirebaseDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepositoryImpl @Inject constructor(
    private val remote: FirebaseDataSource
) : AuthRepository {

    override fun currentUserId(): String? = remote.currentUserId()

    override fun logout() {
        remote.logout()
    }

    override suspend fun login(email: String, password: String): String {
        return remote.login(email, password)
    }

    override suspend fun register(email: String, password: String): String {
        return remote.register(email, password)
    }

    override suspend fun getCurrentUser(): User? {
        return remote.getCurrentUser()
    }

    override suspend fun updateUserName(newName: String) {
        remote.updateUserName(newName)
    }
}
