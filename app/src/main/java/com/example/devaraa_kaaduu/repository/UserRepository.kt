package com.example.devaraa_kaaduu.repository

import com.example.devaraa_kaaduu.data.User
import com.example.devaraa_kaaduu.data.UserDao

class UserRepository(
    private val userDao: UserDao
) {

    suspend fun insertUser(user: User) {
        userDao.insertUser(user)
    }

    suspend fun login(email: String, password: String): User? {
        return userDao.login(email, password)
    }

    suspend fun getLoggedInUser(): User? {
        return userDao.getLoggedInUser()
    }

    suspend fun logout() {
        userDao.logoutAll()
    }

    suspend fun updateUser(user: User) {
        userDao.updateUser(user)
    }
}