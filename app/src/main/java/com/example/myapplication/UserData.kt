package com.example.myapplication

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "user_table")
data class UserData(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String?,
    val description: String?
):Serializable