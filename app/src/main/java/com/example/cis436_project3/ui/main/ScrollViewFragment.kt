package com.example.cis436_project3.ui.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.ViewModelProvider
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.*
import com.example.cis436_project3.databinding.FragmentScrollViewBinding
import com.squareup.picasso.Picasso

class ScrollViewFragment : Fragment()
{
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
    }

    fun RequestCharacter(charID : String)
    {
        val url = "https://swapi.dev/api/people/" + charID + "/"

        var nameText = binding.nameText
        var heightText = binding.heightText
        var weightText = binding.weightText
        var birthYearText = binding.birthYearText
        var image: ImageView = binding.imageView

        val requestQueue = Volley.newRequestQueue(getActivity()?.getApplicationContext())

        val request = JsonObjectRequest(
            Request.Method.GET, url, null,
            Response.Listener { response ->
                //Image
                val imageURL = response.getString("url")


                Picasso.with(context).load(imageURL).into(image)

                //name
                val charName = response.getString("name")
                nameText.text = "Name: %s".format(charName)

                //height
                val height = response.getString("height")
                heightText.text = "Height: %s".format(height) + "cm" //convert to ft/in?

                //weight
                val weight = response.getString("mass")
                weightText.text = "Weight: %s".format(weight) + "kg" //convert to lbs?

                //birthyear
                val birthyear = response.getString("birth_year")
                birthYearText.text = "Birth Year: %s".format(birthyear)


            },
            Response.ErrorListener { error ->
                Log.e("ERROR", "%s".format(error.toString()))
            })

        requestQueue.add(request)
    }

    fun requestFilm(charID: String){
        val url = "https://swapi.dev/api/films/" + charID + "/"
        var filmNameText = binding.nameText
        var releaseDateText = binding.heightText
        var directorText = binding.weightText
        var producerText = binding.birthYearText

        val requestQueue = Volley.newRequestQueue(getActivity()?.getApplicationContext())

        val request = JsonObjectRequest(
            Request.Method.GET, url, null,
            Response.Listener { response ->
                //Image
                val imageURL = response.getString("url")

                //name
                val charName = response.getString("title")
                filmNameText.text = "Name: %s".format(charName)

                //height
                val release = response.getString("release_date")
                releaseDateText.text = "Release: %s".format(release)

                //weight
                val director = response.getString("director")
                directorText.text = "Director: %s".format(director)

                //birthyear
                val producer = response.getString("producer")
                producerText.text = "Producer: %s".format(producer)


            },
            Response.ErrorListener { error ->
                Log.e("ERROR", "%s".format(error.toString()))
            })

        requestQueue.add(request)


    }



    //Gets name, but returns as initial value
    private fun getHomeworld(homeworld: String): String {

        val requestQueue2 = Volley.newRequestQueue(getActivity()?.getApplicationContext())

        var homeName = "TEST"

        val request2 = JsonObjectRequest(
            Request.Method.GET, homeworld, null,
            Response.Listener { response ->

                homeName = response.getString("name")

            },
            Response.ErrorListener { error ->
                Log.e("ERROR", "%s".format(error.toString()))
            })

        requestQueue2.add(request2)

        return homeName

    }

    override fun onDestroyView()
    {
        super.onDestroyView()
        _binding = null
    }

}
