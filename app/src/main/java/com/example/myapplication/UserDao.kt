package com.example.myapplication

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserDao {

    @Query("select * from user_table order by id asc")
    fun getAllUser():LiveData<List<UserData>>

    @Insert
    suspend fun insertUser(user: UserData)

    @Update
    suspend fun updateUser(user: UserData)

    @Delete
    suspend fun deleteUser(user: UserData)
}