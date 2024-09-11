package com.example.myapplication

import android.app.Application
import android.icu.text.Transliterator.Position
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.ViewModelFactoryDsl
import kotlinx.coroutines.launch

class ViewModel (application: Application): AndroidViewModel(application) {

        private val userRepo:UserRepo

        val allUser: LiveData<List<UserData>>

        init {
            val userDao = AppDataBase.getDataBase(application).getData()
            userRepo = UserRepo(userDao)
            allUser = userRepo.allUser
        }

        fun insert(user: UserData)= viewModelScope.launch{
            userRepo.insertUser(user)
        }

        fun update(user: UserData)= viewModelScope.launch{
            userRepo.updateUser(user)
        }

        fun delete(user: UserData)= viewModelScope.launch{
            userRepo.deleteUser(user)
        }

        fun getUserById(id: Int): UserData? {
            return allUser.value?.find { it.id == id }
        }

    }