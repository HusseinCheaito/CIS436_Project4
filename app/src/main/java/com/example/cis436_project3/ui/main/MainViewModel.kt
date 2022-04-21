package com.example.cis436_project3.ui.main

import android.util.Log
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel()
{
    private var catID : String = "abys"

    fun setID(id : String)
    {
        catID = id
        Log.d("setID", catID)
    }

    fun getID() : String
    {
        Log.d("getID", catID)
        return catID
    }
}