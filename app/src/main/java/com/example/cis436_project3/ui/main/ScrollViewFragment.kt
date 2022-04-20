package com.example.cis436_project3.ui.main

import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.*
import com.example.cis436_project3.R
import com.example.cis436_project3.databinding.FragmentScrollViewBinding
import com.google.android.material.internal.ContextUtils.getActivity
import org.json.JSONArray
import org.json.JSONObject

class ScrollViewFragment : Fragment() {
    private var _binding: FragmentScrollViewBinding? = null
    private val binding get() = _binding!!



    companion object {
        fun newInstance() = ScrollViewFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentScrollViewBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        val textView = binding.testText
        var breedText = binding.breedText
        var temperText = binding.temperText
        var originText = binding.originText
        var lifeText = binding.lifeText
        var image: ImageView = binding.imageView


        val requestQueue = Volley.newRequestQueue(getActivity()?.getApplicationContext())

        val url = "https://api.thecatapi.com/v1/images/search?breed_ids=beng"

        val request = object : JsonArrayRequest(
            Request.Method.GET, url, null,
            Response.Listener { response ->
                //Image
                val firstObj = response.getJSONObject(0)
                val imageURL = firstObj.getString("url")
                textView.text = "%s".format(imageURL)

                //Breed info
                val breedArray = firstObj.getJSONArray("breeds")
                val breed = breedArray.getJSONObject(0)

                //name
                val breedName = breed.getString("name")
                breedText.text = "Name: %s".format(breedName)

                //temperament
                val temperament = breed.getString("temperament")
                temperText.text = "%s".format(temperament)

                //origin
                val origin = breed.getString("origin")
                originText.text = "Origin: %s".format(origin)

                //lifespan
                val lifespan = breed.getString("life_span")
                lifeText.text = "Lifespan: %s".format(lifespan) + " years"
            },
            Response.ErrorListener { error ->
                textView.text = "Error: %s".format(error.toString())
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
