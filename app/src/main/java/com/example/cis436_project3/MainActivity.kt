package com.example.cis436_project3

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cis436_project3.ui.main.ScrollViewFragment
import com.example.cis436_project3.ui.main.SelectorFragment
import com.example.cis436_project3.ui.main.SpinnerFragment

class MainActivity : AppCompatActivity(), SpinnerFragment.ControlListener, SelectorFragment.ControlListener {
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
    }

    override fun ItemSelected(id : String)
    {
        val scrollViewFragment : ScrollViewFragment = supportFragmentManager.findFragmentById(R.id.ScrollViewFragmentContainer) as ScrollViewFragment
        scrollViewFragment.RequestCharacter(id)
    }

    override fun ChangeSpinner(spinnerType : String)
    {
        val spinnerFragment : SpinnerFragment = supportFragmentManager.findFragmentById(R.id.SpinnerFragmentContainer) as SpinnerFragment
        spinnerFragment.PopulateSpinner(spinnerType)
    }
}

