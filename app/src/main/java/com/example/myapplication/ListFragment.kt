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
    private lateinit var binding: FragmentListBinding
    private val viewModel: ViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = UserDataAdapter(
            (viewModel.allUser.value ?: emptyList()).toMutableList(),
            { userData ->
                viewModel.delete(userData)
            },
            { userData ->
                findNavController().navigate(R.id.editFragment, bundleOf(
                    "userId" to userData.id
                ))
            },
            { userData ->
                findNavController().navigate(R.id.detailedFragment, bundleOf(
                    "user" to userData
                ))
            }
        )

        binding.list.apply {
            layoutManager = LinearLayoutManager(requireContext())
            this.adapter = adapter
        }

        viewModel.allUser.observe(viewLifecycleOwner) { list ->
            adapter.updateData(list)
        }

        binding.fabAdd.setOnClickListener {
            findNavController().navigate(R.id.addFragment)
        }

        arguments?.let {
            val title = it.getString("title")
            val description = it.getString("description")
            val userId = it.getInt("userId", -1)

            if (userId != -1) {
                val existingUser = viewModel.allUser.value?.find { it.id == userId }
                existingUser?.let {
                    viewModel.update(it.copy(title = title, description = description))
                }
            }
            else {
                viewModel.insert(UserData(title = title, description = description))
            }
            requireArguments().clear()
        }
    }
}
