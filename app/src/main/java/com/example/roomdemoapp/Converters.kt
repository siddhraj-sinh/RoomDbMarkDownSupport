package com.example.roomdemoapp

import android.text.Editable
import androidx.room.TypeConverter

class Converters{
    fun fromEditableToString(editText:Editable):String{
        return editText.toString()
    }
  fun fromStringToEditable(str:String):Editable{
      val editable = Editable.Factory.getInstance().newEditable(str)
      return editable
  }
}