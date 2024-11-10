package com.example.tms.presentation.view.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.tms.presentation.view.fragments.MainFragment
import com.example.tms.R
import com.example.tms.databinding.ActivitySingleMainBinding

class MainActivity : AppCompatActivity() {

//    private val viewModel = ViewModel()
    private var _binding: ActivitySingleMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.mainFragmentView, MainFragment())
                .commit()
        }

//        viewModel.save()
    }
}
