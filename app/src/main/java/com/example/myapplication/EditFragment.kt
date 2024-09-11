package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.myapplication.databinding.FragmentEditBinding

class EditFragment : Fragment() {
    private lateinit var binding: FragmentEditBinding
    private val viewModel: ViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEditBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            val userId = it.getInt("userId")
            viewModel.getUserById(userId)?.let { user ->
                binding.title.setText(user.title)
                binding.description.setText(user.description)
            }
        }

        binding.btnUpdate.setOnClickListener {
            val updatedTitle = binding.title.text.toString()
            val updatedDescription = binding.description.text.toString()
            val userId = arguments?.getInt("userId") ?: return@setOnClickListener
            viewModel.update(UserData(id = userId, title = updatedTitle, description = updatedDescription))
            findNavController().navigateUp()
        }

        binding.closeImg.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}
