package com.example.roomdemoapp

import androidx.lifecycle.LiveData

class Repository(val empDao:EmployeeDao) {
    val allEmp: LiveData<List<Employee>> = empDao.getAllEmp()

    suspend fun insert(emp:Employee){
        empDao.insert(emp)
    }
    suspend fun delete(emp:Employee){
        empDao.delete(emp)
    }
   suspend fun update(emp: Employee){
       empDao.update(emp)
   }
}