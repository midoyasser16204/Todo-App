package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.FragmentListBinding

class ListFragment : Fragment() {
    lateinit var binding: FragmentListBinding
    private val userViewModel: ViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = UserDataAdapter(
            (userViewModel.allUser.value ?: mutableListOf()).toMutableList(),
            { userData ->
                userViewModel.delete(userData)
            },
            { userData ->
                val position = userViewModel.allUser.value?.indexOf(userData) ?: -1
                findNavController().navigate(R.id.editFragment, bundleOf(
                    "title" to userData.title,
                    "description" to userData.description,
                    "position" to position
                ))
            },
            { userData ->
                findNavController().navigate(R.id.detailedFragment, bundleOf(
                    "user" to userData
                ))
            })

        binding.list.apply {
            layoutManager = LinearLayoutManager(requireContext())
            this.adapter = adapter
        }
        userViewModel.allUser.observe(viewLifecycleOwner) { list ->
            adapter.updateData(list)
        }
        binding.fabAdd.setOnClickListener {
            findNavController().navigate(R.id.addFragment)
        }

        arguments?.let {
            val title = it.getString("title")
            val description = it.getString("description")
            val position = it.getInt("position", -1)
            if (position != -1 && position < (userViewModel.allUser.value?.size ?: 0)) {
                val existingUser = userViewModel.allUser.value?.get(position)
                existingUser?.let {
                    userViewModel.update(it.copy(title = title, description = description))
                }
            }
            else {
                userViewModel.insert(UserData(title = title, description = description))
            }
            requireArguments().clear()
        }
    }
}
