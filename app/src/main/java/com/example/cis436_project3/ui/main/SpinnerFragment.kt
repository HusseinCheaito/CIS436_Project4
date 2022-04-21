package com.example.cis436_project3.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.cis436_project3.databinding.SpinnerFragmentBinding

class SpinnerFragment : Fragment()
{
    private var _binding : SpinnerFragmentBinding? = null
    private val binding get() = _binding!!

    companion object
    {
        fun newInstance() = SpinnerFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View
    {
        _binding = SpinnerFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?)
    {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

       var spinnerText = binding.breedDropDown

        val requestQueue = Volley.newRequestQueue(getActivity()?.getApplicationContext())

        val url = "https://api.thecatapi.com/v1/breeds"

        val breedList = mutableListOf<String>()

        val request = object : JsonArrayRequest(
            Request.Method.GET, url, null,
            Response.Listener { response ->

                for(i in 0 until response.length()){

                    val jObject = response.getJSONObject(i)
                    breedList.add(jObject.getString("name"))

                    }

                val adapter = ArrayAdapter(requireContext(),
                    android.R.layout.simple_spinner_item, breedList)
                spinnerText.adapter = adapter

            },
            Response.ErrorListener { error ->

                val errorMsg = arrayOf(error)

                val adapter = ArrayAdapter(requireContext(),
                    android.R.layout.simple_spinner_item, errorMsg)
                spinnerText.adapter = adapter
            })
        {
            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers["x-api-key"] = "cff029ba-ea55-493d-b527-06b4a6813175"
                return headers
            }

        }

        requestQueue.add(request)
    }

}