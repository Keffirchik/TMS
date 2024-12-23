package com.example.tms.presentation.view.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.tms.App
import com.example.tms.AppComponent
import com.example.tms.presentation.view.fragments.MainFragment
import com.example.tms.R
import com.example.tms.data.storage.RoomDB

import com.example.tms.presentation.view_model.MyViewModel
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private var viewModel: MyViewModel? = null
    @Inject
    lateinit var roomDB: RoomDB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        (application as App).appComponent?.inject(this)

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
