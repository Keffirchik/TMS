package com.example.tms.presentation.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.lifecycle.ViewModelProvider
import com.example.tms.R
import com.example.tms.presentation.view.MainFragmentAction
import com.example.tms.presentation.view.activities.MainActivity
import com.example.tms.presentation.view_model.MainFragmentModel

class MainFragment : Fragment() {

    private var viewModel: MainFragmentModel? = null

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

        return currentView
    }

}