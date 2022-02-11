package com.example.roomdemoapp

import android.app.Application
import android.provider.ContactsContract
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EmployeeViewModel(application: Application) :AndroidViewModel(application) {
     val allEmp:LiveData<List<Employee>>

    private val repository:Repository
    init {
        val dao = EmployeeRoomDatabase.getDatabase(application).getEmployeeDao()
        repository = Repository(dao)
        allEmp = repository.allEmp
    }
    fun deleteEmp(emp: Employee) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(emp)
    }
    fun insertEmp(emp: Employee) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(emp)
    }
    fun updateEmp(emp: Employee) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(emp)
    }
}