package com.example.roomdemoapp

import android.text.Editable
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Blob

@Entity(tableName = "employee_table")
class Employee(@PrimaryKey(autoGenerate = true) val id: Int,val name:String,val email:String,val mdname:String?)