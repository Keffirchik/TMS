package com.example.tms.presentation.view.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.tms.presentation.view.fragments.MainFragment
import com.example.tms.R
import com.example.tms.data.storage.RoomObject
import com.example.tms.databinding.ActivityMainBinding
import com.example.tms.presentation.view_model.MyViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    //    private val viewModel = ViewModel()
    private var _binding: ActivityMainBinding? = null

    private var viewModel: MyViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

//        viewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(application).create(MyViewModel::class.java)
//        _binding = ActivityMainBinding.inflate(LayoutInflater)

        if (savedInstanceState == null) {
            val fragment = MainFragment()
            openFragment(fragment)
        }

        lifecycleScope.launch(Dispatchers.IO) {
            RoomObject.initDB(application)
        }
    }

    fun openFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.mainFragmentView, fragment)
            .addToBackStack(null)
            .commit()
    }
}
