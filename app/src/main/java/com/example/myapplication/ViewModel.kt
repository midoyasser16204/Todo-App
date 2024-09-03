package com.example.myapplication

import android.icu.text.Transliterator.Position
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ViewModel: ViewModel() {
    private var _userList = MutableLiveData<MutableList<UserData>>()
    val userList:MutableLiveData<MutableList<UserData>> get() = _userList

    init {
            _userList.value = mutableListOf(
                UserData("ahmed", "ahmed@gmail.com"),
                UserData("s", "sssw@gmail.com")
            )
    }

    fun addtolist(userData: UserData){
        val list = userList.value?: mutableListOf()
        list.add(userData)
        _userList.value=list
    }

    fun removefromlist(userData: UserData){
        val list = userList.value?: mutableListOf()
        list.remove(userData)
        _userList.value=list
    }

    fun updatelist(userData: UserData,position: Int){
        val list = userList.value?: mutableListOf()
        if (list != null) {
            if (position in list.indices){
                list[position] = userData
                _userList.value=list
            }
        }
    }

}