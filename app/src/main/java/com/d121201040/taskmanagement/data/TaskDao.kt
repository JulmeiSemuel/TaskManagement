package com.d121201040.taskmanagement.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.d121201040.taskmanagement.model.Task

@Dao
interface TaskDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addTask(task:Task)

    @Query("SELECT * FROM task WHERE status = 'Tugas Masih Berjalan'")
    fun readAllData():LiveData<List<Task>>

    @Query("SELECT * FROM task WHERE status = 'Tugas Telah Selesai'")
    fun readAllDataHistory():LiveData<List<Task>>

    @Update
    suspend fun updateTask(task: Task)
}