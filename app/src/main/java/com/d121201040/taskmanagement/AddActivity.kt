package com.d121201040.taskmanagement

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.d121201040.taskmanagement.databinding.ActivityAddBinding
import com.d121201040.taskmanagement.model.Task
import com.d121201040.taskmanagement.viewmodel.TaskViewModel
import kotlinx.coroutines.launch

class AddActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddBinding
    private lateinit var kategoriTugas:ArrayAdapter<CharSequence>
    private lateinit var taskViewModel: TaskViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        taskViewModel = ViewModelProvider(this)[TaskViewModel::class.java]
        kategoriTugas = ArrayAdapter.createFromResource(this,R.array.kategori_tugas,android.R.layout.simple_list_item_1)
        binding.dropdownKategori.setAdapter(kategoriTugas)

        binding.btnAdd.setOnClickListener {
            val judul = binding.judulTugas.text.toString()
            val deskripsi = binding.deskripsiTugas.text.toString()
            val kategori = binding.dropdownKategori.text.toString()

            if (kategori.isEmpty()){
                Toast.makeText(this, "Kategori Belum Dipilih",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (judul.isEmpty()){
                binding.judulTugas.error = "Judul Harus Diisi"
                binding.judulTugas.requestFocus()
                return@setOnClickListener
            }
            if (deskripsi.isEmpty()){
                binding.deskripsiTugas.error = "Deskripsi Harus Diisi"
                binding.deskripsiTugas.requestFocus()
                return@setOnClickListener
            }

            lifecycleScope.launch {
                val task = Task(0, judul, deskripsi, kategori, "Tugas Masih Berjalan")
                taskViewModel.addTask(task)
                finish()
            }
        }

        binding.btnBack.setOnClickListener {
            finish()
        }
    }
}