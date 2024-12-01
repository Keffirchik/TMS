package com.example.tms.presentation.view.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.tms.R
import com.example.tms.data.api.DisneyAPI
import com.example.tms.presentation.view.MainFragmentAction
import com.example.tms.presentation.view.activities.MainActivity
import com.example.tms.presentation.view_model.MainFragmentModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainFragment : Fragment() {

    private var viewModel: MainFragmentModel? = null

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.disneyapi.dev")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel =
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
                .create(MainFragmentModel::class.java)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel?.publicLiveData?.observe(this.viewLifecycleOwner) { event ->
            if (event == null) return@observe
            val fragment = when (event) {
                MainFragmentAction.OpenLoginFragment -> MainLoginFragment()
                MainFragmentAction.OpenNoteFragment -> NotesFragment()
                MainFragmentAction.OpenSecondFragment -> SecondFragment()
            }

            (activity as MainActivity).openFragment(fragment)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val currentView = inflater.inflate(R.layout.fragment_main, container, false)

        //login button
        val buttonLogin = currentView.findViewById<AppCompatButton>(R.id.fragment_main_button)

        buttonLogin.setOnClickListener {
//            val fragment = MainLoginFragment()
//            (activity as MainActivity).openFragment(fragment)
            viewModel?.toNextScreen(MainFragmentAction.OpenLoginFragment)
        }

        //button to second screen
        val buttonGoToSecondActivity =
            currentView.findViewById<AppCompatButton>(R.id.button_to_second_activity)

        buttonGoToSecondActivity.setOnClickListener {
//            val fragment = SecondFragment()
//            (activity as MainActivity).openFragment(fragment)
            viewModel?.toNextScreen(MainFragmentAction.OpenSecondFragment)
        }

        //button to notes
        val noteButton = currentView.findViewById<AppCompatButton>(R.id.acb_go_to_notes_am)

        noteButton.setOnClickListener {
//            val fragment = NotesFragment()
//            (activity as MainActivity).openFragment(fragment)
            viewModel?.toNextScreen(MainFragmentAction.OpenNoteFragment)
        }

        // api text button
        val apiButton = currentView.findViewById<AppCompatButton>(R.id.acb_api_notes_am)

        val api = retrofit.create(DisneyAPI::class.java)

        apiButton.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                val responseBody = api.getOneCharacter(308)
                Log.d("Response", "$responseBody")
            }
        }

        return currentView
    }

}