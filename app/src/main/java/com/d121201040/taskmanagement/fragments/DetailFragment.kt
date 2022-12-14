package com.d121201040.taskmanagement.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.d121201040.taskmanagement.R
import com.d121201040.taskmanagement.databinding.FragmentDetailBinding
import com.d121201040.taskmanagement.databinding.FragmentHistoryBinding
import com.d121201040.taskmanagement.model.Task
import com.d121201040.taskmanagement.viewmodel.TaskViewModel

class DetailFragment : Fragment() {
    private var _binding: FragmentDetailBinding?=null
    private val binding get() = _binding!!
    private lateinit var taskViewModel: TaskViewModel
    private val args by navArgs<DetailFragmentArgs>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentDetailBinding.inflate(inflater,container, false)
        val view = binding.root

        taskViewModel = ViewModelProvider(this)[TaskViewModel::class.java]

        binding.judulTugas.text = args.currentTask.judul
        binding.deskripsiTugas.text = args.currentTask.deskripsi
        binding.kategoriTugasTugas.text = args.currentTask.kategori

        if (args.currentTask.status == "Tugas Telah Selesai"){
            binding.btnSave.visibility = View.GONE
            binding.btnBack.setOnClickListener{
                findNavController().navigate(R.id.action_detailFragment_to_historyFragment)
            }
        }else{
            binding.btnBack.setOnClickListener{
                findNavController().navigate(R.id.action_detailFragment_to_homeFragment)
            }
            binding.btnSave.setOnClickListener {
                val selesai = Task(args.currentTask.id,
                    args.currentTask.judul,
                    args.currentTask.deskripsi,
                    args.currentTask.kategori,
                    "Tugas Telah Selesai")
                taskViewModel.updateTask(selesai)
                findNavController().navigate(R.id.action_detailFragment_to_homeFragment)
            }
        }

        return view
    }
}