package com.example.cis436_project3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.*
import com.example.cis436_project3.databinding.MainActivityBinding
import com.example.cis436_project3.ui.main.SpinnerFragment
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity()
{
    private lateinit var binding : MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }



}

