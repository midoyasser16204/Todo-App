package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.myapplication.databinding.FragmentAddBinding

class AddFragment : Fragment() {
    private lateinit var binding: FragmentAddBinding
    private val viewModel: ViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSave.setOnClickListener {
            val title = binding.title.text.toString()
            val description = binding.description.text.toString()
            if (title.isNotEmpty() && description.isNotEmpty()) {
                viewModel.insert(UserData(title = title, description = description))
                findNavController().navigateUp()
            }
            else {
                binding.title.error = "Please enter a title"
                binding.description.error = "Please enter a description"
            }
        }

        binding.closeImg.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}
