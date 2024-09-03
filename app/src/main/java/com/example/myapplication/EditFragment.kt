package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.myapplication.databinding.FragmentEditBinding

class EditFragment : Fragment() {
    private lateinit var binding: FragmentEditBinding
    private val viewModel:ViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEditBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var position = -1
        arguments?.let {
            binding.title.setText(it.getString("title"))
            binding.description.setText(it.getString("description"))
            position = it.getInt("position", -1)
        }
        binding.btnUpdate.setOnClickListener {
            val updatedTitle = binding.title.text.toString()
            val updatedDescription = binding.description.text.toString()

            findNavController().navigate(R.id.listFragment, bundleOf(
                "title" to updatedTitle,
                "description" to updatedDescription,
                "position" to position
            ))
        }
        binding.closeImg.setOnClickListener{
            findNavController().navigateUp()
        }
    }
}
