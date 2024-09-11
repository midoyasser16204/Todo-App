package com.example.myapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ItemLayoutBinding

 class UserDataAdapter(
     var userlist:MutableList<UserData>,
     val onDeleteClick:(UserData) -> Unit,
     val onEditClick:(UserData)->Unit,
     val onDetailClick:(UserData)->Unit
    ):RecyclerView.Adapter<UserDataAdapter.UserDataHolder>() {

    inner class UserDataHolder(val binding: ItemLayoutBinding):RecyclerView.ViewHolder(binding.root){
        fun bindData(userData: UserData) {
            binding.user = userData
            binding.deleteImg.setOnClickListener {
                onDeleteClick(userData)
            }
            binding.editImg.setOnClickListener{
                onEditClick(userData)
            }
            binding.card.setOnClickListener{
                onDetailClick(userData)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserDataHolder {
        val binding = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return UserDataHolder(binding)
    }

     override fun onBindViewHolder(holder: UserDataHolder, position: Int) {
         holder.bindData(userlist[position])
     }

     override fun getItemCount()=userlist.size

     fun updateData(newList: List<UserData>) {
         val diffCallback = AlbumDiffUtil(userlist, newList)
         val diffResult = DiffUtil.calculateDiff(diffCallback)
         userlist.clear()
         userlist.addAll(newList)
         diffResult.dispatchUpdatesTo(this)
     }

    }