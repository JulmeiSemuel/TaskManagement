package com.d121201040.taskmanagement.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.d121201040.taskmanagement.R
import com.d121201040.taskmanagement.adapter.TaskAdapter
import com.d121201040.taskmanagement.databinding.FragmentHomeBinding
import com.d121201040.taskmanagement.viewmodel.TaskViewModel

class HomeFragment : Fragment() {
    private var _binding:FragmentHomeBinding ?=null
    private val binding get() = _binding!!
    private lateinit var taskViewModel: TaskViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentHomeBinding.inflate(inflater,container, false)
        val view = binding.root

        val adapter_task = TaskAdapter()
        binding.recyleTugas.adapter = adapter_task
        binding.recyleTugas.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
        taskViewModel = ViewModelProvider(this)[TaskViewModel::class.java]
        taskViewModel.readAllData.observe(viewLifecycleOwner){task->
            adapter_task.setData(task)
            emptyData(task.size)
        }

        return view
    }
    private fun emptyData(sizeData:Int){
        if(sizeData==0){
            binding.empty.visibility = View.VISIBLE
            binding.recyleTugas.visibility = View.GONE
        }else{
            binding.empty.visibility = View.GONE
            binding.recyleTugas.visibility = View.VISIBLE
        }
    }
}