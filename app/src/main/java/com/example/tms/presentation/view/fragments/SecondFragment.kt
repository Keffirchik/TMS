package com.example.tms.presentation.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tms.R
import com.example.tms.databinding.FragmentSecondBinding

class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    private val binding: FragmentSecondBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSecondBinding.inflate(inflater)

//        val currentView = inflater.inflate(R.layout.fragment_second, container, false)

//        val buttonGoMainctivity =
//            currentView.findViewById<AppCompatButton>(R.id.cl_go_to_main_button_fs)
//        buttonGoMainctivity.setOnClickListener {
        binding.clGoToMainButtonFs.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.mainFragmentView, MainFragment())
                .addToBackStack(null)
                .commit()

        }

//        return currentView
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}