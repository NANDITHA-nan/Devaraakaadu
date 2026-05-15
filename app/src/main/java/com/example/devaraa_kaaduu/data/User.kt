package com.example.devaraa_kaaduu.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val name: String,
    val email: String,
    val password: String,
    val accessLevel: String = "Standard User",
    val isLoggedIn: Boolean = false
)