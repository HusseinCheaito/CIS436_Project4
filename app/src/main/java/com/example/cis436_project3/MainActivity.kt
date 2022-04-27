package com.example.cis436_project3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils.replace
import androidx.fragment.app.Fragment
import com.android.volley.VolleyError
import com.android.volley.toolbox.*
import com.example.cis436_project3.databinding.MainActivityBinding
import com.example.cis436_project3.ui.main.MainViewModel
import com.example.cis436_project3.ui.main.ScrollViewFragment
import com.example.cis436_project3.ui.main.SpinnerFragment
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity(), SpinnerFragment.ControlListener
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        val moviesFragment = MainViewModel()

    }

    override fun ItemSelected(id : String)
    {
        val scrollViewFragment : ScrollViewFragment = supportFragmentManager.findFragmentById(R.id.ScrollViewFragmentContainer) as ScrollViewFragment
        scrollViewFragment.RequestCat(id)
    }


}





