package com.example.myapplication

import android.icu.text.Transliterator.Position
import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.FileDescriptor
import java.io.Serializable

data class UserData(
    var title:String,
    var description:String
):Serializable