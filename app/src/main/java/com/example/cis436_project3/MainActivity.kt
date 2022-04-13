package com.example.cis436_project3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cis436_project3.ui.main.SpinnerFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, SpinnerFragment.newInstance())
                .commitNow()
        }
    }
}