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

        if (savedInstanceState == null)
        {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, SpinnerFragment.newInstance())
                .commitNow()
        }

        val textView = binding.testText

        val cache = DiskBasedCache(cacheDir, 1024 * 1024)
        val network = BasicNetwork(HurlStack())

        val requestQueue = RequestQueue(cache, network).apply{
            start()
        }

        val url = "https://api.thecatapi.com/v1/images/search"

        val request = object: JsonArrayRequest(Request.Method.GET, url, null,
            Response.Listener{response -> textView.text = "%s".format(response.toString())},
            Response.ErrorListener {error -> textView.text = "Error: %s".format(error.toString())})
        {
            override fun getHeaders() : MutableMap<String, String>
            {
                val headers = HashMap<String, String>()
                headers["x-api-key"] = "cff029ba-ea55-493d-b527-06b4a6813175"
                return headers
            }
        }

        requestQueue.add(request)

    }



}

