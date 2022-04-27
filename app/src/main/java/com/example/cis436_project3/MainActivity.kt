package com.example.cis436_project3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.*
import com.example.cis436_project3.databinding.MainActivityBinding
import com.example.cis436_project3.ui.main.ScrollViewFragment
import com.example.cis436_project3.ui.main.SelectorFragment
import com.example.cis436_project3.ui.main.SpinnerFragment
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity(), SpinnerFragment.ControlListener, SelectorFragment.ControlListener
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
    }

    override fun ItemSelected(id : String)
    {
        val scrollViewFragment : ScrollViewFragment = supportFragmentManager.findFragmentById(R.id.ScrollViewFragmentContainer) as ScrollViewFragment
        scrollViewFragment.RequestCat(id)
    }

    override fun ChangeSpinner(spinnerType : String)
    {
        val spinnerFragment : SpinnerFragment = supportFragmentManager.findFragmentById(R.id.SpinnerFragmentContainer) as SpinnerFragment
        spinnerFragment.PopulateSpinner(spinnerType)
    }
}

