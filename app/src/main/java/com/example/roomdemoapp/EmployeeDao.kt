package com.example.roomdemoapp

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface EmployeeDao{
    @Insert
    suspend fun insert(emp:Employee)
    @Delete
    suspend fun delete(emp:Employee)
    @Query("Select*from employee_table order by id ASC")
    fun getAllEmp():LiveData<List<Employee>>

    @Update
    suspend fun update(emp: Employee)
}