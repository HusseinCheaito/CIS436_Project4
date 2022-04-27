package com.example.cis436_project3.ui.main

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.cis436_project3.R
import com.example.cis436_project3.databinding.FragmentSelectorBinding
import java.lang.ClassCastException

class SelectorFragment : Fragment()
{
    private var _binding : FragmentSelectorBinding? = null
    private val binding get() = _binding!!

    var activityCallback : SelectorFragment.ControlListener? = null

    interface ControlListener
    {
        fun ChangeSpinner(spinnerType : String)
    }

    companion object
    {
        fun newInstance() = SelectorFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onAttach(context: Context)
    {
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
    ): View? {

        _binding = FragmentSelectorBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun ChangeSpinner(spinnerType: String)
    {
        activityCallback?.ChangeSpinner(spinnerType)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?)
    {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        binding.characterButton.setOnClickListener{
            viewModel.setSpinnerType("people")
            ChangeSpinner(viewModel.getSpinnerType())
        }

        binding.movieButton.setOnClickListener{
            viewModel.setSpinnerType("films")
            ChangeSpinner(viewModel.getSpinnerType())
        }
    }

    override fun onDestroyView()
    {
        super.onDestroyView()
        _binding = null
    }
}