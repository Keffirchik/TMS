package com.example.tms.presentation.view.activities

import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.tms.presentation.view.fragments.MainFragment
import com.example.tms.R
import com.example.tms.databinding.ActivityMainBinding
import com.example.tms.presentation.view_model.MyViewModel

class MainActivity : AppCompatActivity() {

//    private val viewModel = ViewModel()
    private var _binding: ActivityMainBinding? = null

    private var viewModel: MyViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(application).create(MyViewModel::class.java)
        _binding = ActivityMainBinding.inflate(LayoutInflater)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.mainFragmentView, MainFragment())
                .commit()
        }

    }
}
