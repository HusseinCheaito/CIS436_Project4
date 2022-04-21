package com.example.cis436_project3.ui.main

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.cis436_project3.databinding.SpinnerFragmentBinding
import java.lang.ClassCastException


class SpinnerFragment : Fragment()
{
    private var _binding : SpinnerFragmentBinding? = null
    private val binding get() = _binding!!

    var activityCallback : SpinnerFragment.ControlListener? = null

    interface ControlListener
    {
        fun ItemSelected(id : String)
        {

        }
    }

    companion object
    {
        fun newInstance() = SpinnerFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)

        try
        {
            activityCallback = context as ControlListener
        }
        catch(e : ClassCastException)
        {
            throw ClassCastException(context.toString() + " must implement ControlListener.")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View
    {
        _binding = SpinnerFragmentBinding.inflate(inflater, container, false)
        binding.breedDropDown.onItemSelectedListener =
            object: AdapterView.OnItemSelectedListener
            {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long)
                {
                   var catNum = binding.breedDropDown.selectedItemPosition

                    val requestQueue = Volley.newRequestQueue(getActivity()?.getApplicationContext())

                    val url = "https://api.thecatapi.com/v1/breeds"

                    val request = object : JsonArrayRequest(
                        Request.Method.GET, url, null,
                        Response.Listener { response ->
                            val jObject = response.getJSONObject(catNum)
                            val breedID = jObject.getString("id")
                            viewModel.setID(breedID)
                            Log.d("breed id", breedID.toString())
                            Log.d("TEST GET", viewModel.getID())
                            itemSelected(viewModel.getID())
                        },
                        Response.ErrorListener { error ->

                            Log.e("error", "%s".format(error.toString()))
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

                override fun onNothingSelected(p0: AdapterView<*>?)
                {
                    TODO("Not yet implemented")
                }
            }

        return binding.root

    }

    private fun itemSelected(id : String)
    {
        activityCallback?.ItemSelected(id)
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

    override fun onDestroyView()
    {
        super.onDestroyView()
        _binding = null
    }

}

