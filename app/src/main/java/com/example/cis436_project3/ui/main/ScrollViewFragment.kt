package com.example.cis436_project3.ui.main

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
        var genderText = binding.genderText
        var homeworldText = binding.homeworldText
        var speciesText = binding.speciesText
        var heightText = binding.heightText
        var weightText = binding.weightText
        var hairText = binding.hairText
        var skinText = binding.skinText
        var eyeText = binding.eyeText
        var birthYearText = binding.birthYearText


        val requestQueue = Volley.newRequestQueue(getActivity()?.getApplicationContext())

        val request = JsonObjectRequest(
            Request.Method.GET, url, null,
            Response.Listener { response ->

                //name
                val charName = response.getString("name")
                nameText.text = "Name: %s".format(charName)

                //gender
                val gender = response.getString("gender")
                genderText.text = "Gender: %s".format(gender)

                //species
                val speciesArray = response.getJSONArray("species")

                if (speciesArray.length() < 1)
                {
                    speciesText.text = "Species: Human"
                }
                else
                {
                    val species = speciesArray.getString(0)
                    RequestSpecies(species, speciesText)
                }

                //homeworld
                val homeworld = response.getString("homeworld")
                RequestHomeworld(homeworld, homeworldText)

                //height
                val height = response.getString("height")
                heightText.text = "Height: %s".format(height) + "cm" //convert to ft/in?

                //weight
                val weight = response.getString("mass")
                weightText.text = "Weight: %s".format(weight) + "kg" //convert to lbs?

                //hair color
                val hair = response.getString("hair_color")
                hairText.text = "Hair Color: %s".format(hair)

                //skin color
                val skin = response.getString("skin_color")
                skinText.text = "Skin Color: %s".format(skin)

                //eye color
                val eye = response.getString("eye_color")
                eyeText.text = "Eye Color: %s".format(eye)

                //birthyear
                val birthyear = response.getString("birth_year")
                birthYearText.text = "Birth Year: %s".format(birthyear)


            },
            Response.ErrorListener { error ->
                Log.e("ERROR", "%s".format(error.toString()))
            })

        requestQueue.add(request)
    }

    fun RequestHomeworld(homeworldURL : String, homeworldText : TextView)
    {
        val requestQueue = Volley.newRequestQueue(getActivity()?.getApplicationContext())

        val homeworldRequest = JsonObjectRequest(
            Request.Method.GET, homeworldURL, null,
            Response.Listener { response ->
                val homeworld = response.getString("name")
                homeworldText.text = "Homeworld: %s".format(homeworld)
            },
            Response.ErrorListener { error ->
                Log.e("ERROR", "%s".format(error.toString()))
            })

        requestQueue.add(homeworldRequest)
    }

    fun RequestSpecies(speciesURL : String, speciesText : TextView)
    {
        val requestQueue = Volley.newRequestQueue(getActivity()?.getApplicationContext())

        val speciesRequest = JsonObjectRequest(
            Request.Method.GET, speciesURL, null,
            Response.Listener { response ->
                val species = response.getString("name")
                speciesText.text = "Species: %s".format(species)
            },
            Response.ErrorListener { error ->
                Log.e("ERROR", "%s".format(error.toString()))
            })

        requestQueue.add(speciesRequest)
    }

    fun requestFilm(filmID: String){
        val url = "https://swapi.dev/api/films/" + filmID + "/"
        var filmNameText = binding.nameText
        var episodeText = binding.genderText
        var releaseDateText = binding.speciesText
        var directorText = binding.homeworldText
        var producerText = binding.heightText
        var openingText = binding.weightText

        val requestQueue = Volley.newRequestQueue(getActivity()?.getApplicationContext())

        val request = JsonObjectRequest(
            Request.Method.GET, url, null,
            Response.Listener { response ->

                //title
                val title = response.getString("title")
                filmNameText.text = "Name: %s".format(title)

                //Episode
                val episodeNum = response.getString("episode_id")
                episodeText.text = "Episode: %s".format(episodeNum)

                //release date
                val release = response.getString("release_date")
                releaseDateText.text = "Release: %s".format(release)

                //director
                val director = response.getString("director")
                directorText.text = "Director: %s".format(director)

                //producer
                val producer = response.getString("producer")
                producerText.text = "Producer: %s".format(producer)

                //opening crawl
                val opening = response.getString(("opening_crawl"))
                openingText.text = "Opening Crawl: \n\n%s".format(opening)


                //empty other text fields
                val hairText = binding.hairText
                val skinText = binding.skinText
                val eyeText = binding.eyeText
                val birthYearText = binding.birthYearText

                hairText.text = ""
                skinText.text = ""
                eyeText.text = ""
                birthYearText.text = ""

            },
            Response.ErrorListener { error ->
                Log.e("ERROR", "%s".format(error.toString()))
            })

        requestQueue.add(request)
    }

    override fun onDestroyView()
    {
        super.onDestroyView()
        _binding = null
    }

}
