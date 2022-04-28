package com.example.cis436_project3.ui.main

import android.R
import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
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
        fun ItemSelected(id : String,spinnerType: String)
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
        binding.DropDown.onItemSelectedListener =
            object: AdapterView.OnItemSelectedListener
            {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long)
                {
                   var charNum = binding.DropDown.selectedItemPosition+1

                            itemSelected(charNum.toString(),viewModel.getSpinnerType())

                    //Differentiate for films in scrollview frag

                }

                override fun onNothingSelected(p0: AdapterView<*>?)
                {
                    TODO("Not yet implemented")
                }
            }

        return binding.root

    }

    private fun itemSelected(id : String,spinnerType: String)
    {
        activityCallback?.ItemSelected(id,spinnerType)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?)
    {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        PopulateSpinner(viewModel.getSpinnerType())
    }

    fun PopulateSpinner(spinnerType : String)
    {
        viewModel.setSpinnerType(spinnerType)

        var spinnerText = binding.DropDown

        val requestQueue = Volley.newRequestQueue(getActivity()?.getApplicationContext())

        if (spinnerType == "people")
        {
            var url = "https://swapi.dev/api/people/"

            val nameList = mutableListOf<String>()

            var requestArray = mutableListOf<JsonObjectRequest>()

            var request = JsonObjectRequest(
                Request.Method.GET, url, null,
                Response.Listener { response ->

                    val resultArray = response.getJSONArray("results")

                    for (i in 0 until resultArray.length()) {

                        val jObject = resultArray.getJSONObject(i)
                        nameList.add(jObject.getString("name"))

                    }

                    val adapter = ArrayAdapter(
                        requireContext(),
                        R.layout.simple_spinner_item, nameList
                    )
                    spinnerText.adapter = adapter

                },
                Response.ErrorListener { error ->

                    val errorMsg = arrayOf(error)

                    val adapter = ArrayAdapter(
                        requireContext(),
                        R.layout.simple_spinner_item, errorMsg
                    )
                    spinnerText.adapter = adapter
                })

            requestQueue.add(request)

        }
        else if (spinnerType == "films")
        {
            var url = "https://swapi.dev/api/films"

            val nameList = mutableListOf<String>()

            var request = JsonObjectRequest(
                Request.Method.GET, url, null,
                Response.Listener { response ->

                    val resultArray = response.getJSONArray("results")

                    for (i in 0 until resultArray.length()) {

                        val jObject = resultArray.getJSONObject(i)
                        nameList.add(jObject.getString("title"))

                    }

                    val adapter = ArrayAdapter(
                        requireContext(),
                        R.layout.simple_spinner_item, nameList
                    )
                    spinnerText.adapter = adapter

                },
                Response.ErrorListener { error ->

                    val errorMsg = arrayOf(error)

                    val adapter = ArrayAdapter(
                        requireContext(),
                        R.layout.simple_spinner_item, errorMsg
                    )
                    spinnerText.adapter = adapter
                })

            requestQueue.add(request)
        }


    }

    override fun onDestroyView()
    {
        super.onDestroyView()
        _binding = null
    }

}

