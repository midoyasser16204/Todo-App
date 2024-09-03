package com.example.myapplication

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.FragmentListBinding

class ListFragment : Fragment() {
    lateinit var binding: FragmentListBinding
    private val viewModel: ViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = UserDataAdapter(viewModel.userList.value?: mutableListOf(),
            {UserData->
                viewModel.removefromlist(UserData)
            },
            {UserData->
                val position = viewModel.userList.value?.indexOf(UserData)?:-1
                findNavController().navigate(R.id.editFragment,bundleOf(
                    "title" to UserData.title,
                    "description" to UserData.description,
                    "position" to position))
            },
            {UserData->
                findNavController().navigate(R.id.detailedFragment, bundleOf(
                    "user" to UserData
                ))
            })

        binding.list.apply {
            layoutManager = LinearLayoutManager(requireContext())
            this.adapter = adapter
        }

        viewModel.userList.observe(viewLifecycleOwner) { list ->
            adapter.updateData(list)
        }

        binding.fabAdd.setOnClickListener{
            findNavController().navigate(R.id.addFragment)
        }

        arguments?.let {
            val title = it.getString("title")
            val description = it.getString("description")
            val position = it.getInt("position", -1)
            if (!title.isNullOrEmpty() && !description.isNullOrEmpty()) {
                if (position != -1 && position < (viewModel.userList.value?.size ?: 0)) {
                    viewModel.updatelist(UserData(title,description),position)
                }
                else {
                    viewModel.addtolist(UserData(title,description))
                }
            }
            requireArguments().clear()
        }
    }
}