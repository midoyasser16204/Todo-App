package com.example.myapplication

import androidx.recyclerview.widget.DiffUtil

class AlbumDiffUtil (
    val oldList: List<UserData>,
    val newList: List<UserData>): DiffUtil.Callback()
{
    override fun getOldListSize()=oldList.size
    override fun getNewListSize()=newList.size
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList [oldItemPosition].id == newList[newItemPosition].id
    }
    override fun areContentsTheSame (oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList [oldItemPosition] == newList[newItemPosition]
    }
}