package com.example.cis436_project3.ui.main

import android.util.Log
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel()
{
    private var spinnerItem : String = "abys"
    private var spinnerType : String = "people"

    fun setSpinnerItem(id : String)
    {
        spinnerItem = id
    }

    fun getSpinnerItem() : String
    {
        return spinnerItem
    }

    fun setSpinnerType(type : String)
    {
        spinnerType = type
    }

    fun getSpinnerType() : String
    {
        return spinnerType
    }
}