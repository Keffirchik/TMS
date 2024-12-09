package com.example.tms.presentation.view.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.tms.presentation.view.fragments.MainFragment
import com.example.tms.R

import com.example.tms.presentation.view_model.MyViewModel

class MainActivity : AppCompatActivity() {

    private var viewModel: MyViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

//        viewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(application).create(MyViewModel::class.java)
//        _binding = ActivityMainBinding.inflate(LayoutInflater)

        if (savedInstanceState == null) {
            val fragment = MainFragment()
//            openFragment(fragment)
        }

    }


//    fun openFragment(fragment: Fragment) {
//        supportFragmentManager.beginTransaction()
//            .replace(R.id.nav_graph, fragment)
//            .addToBackStack(null)
//            .commit()
//    }
}
