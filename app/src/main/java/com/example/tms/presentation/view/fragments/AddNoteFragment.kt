package com.example.tms.presentation.view.fragments

import android.icu.util.Calendar
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import com.example.tms.R
import com.example.tms.data.storage.MySharedPreferences
import com.example.tms.presentation.view.AddNoteFragmentAction
import com.example.tms.presentation.view.MainFragmentAction
import com.example.tms.presentation.view.activities.MainActivity
import com.example.tms.presentation.view_model.AddFragmentModel
import com.example.tms.presentation.view_model.MainFragmentModel

class AddNoteFragment : Fragment() {

    private var sharedPreferences: MySharedPreferences? = null

    private var viewModel: AddFragmentModel? = null

    private lateinit var titleEditText: EditText
    private lateinit var contentEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel =
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
                .create(AddFragmentModel::class.java)
        initSharedPreferences()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel?.publicLiveData?.observe(this.viewLifecycleOwner) { event ->
            if (event == null) return@observe
            val fragment = when (event) {
                AddNoteFragmentAction.OpenNoteFragmentAction -> NotesFragment()
            }

            (activity as MainActivity).openFragment(fragment)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val currentView = inflater.inflate(R.layout.fragment_add_note, container, false)

        // save button
        val saveButton = currentView.findViewById<Button>(R.id.ll_add_new_note_fan)
        saveButton.setOnClickListener {
            saveNoteToPreferences(currentView)

//            val fragment = NotesFragment()
//            (activity as MainActivity).openFragment(fragment)
            viewModel?.toNextScreen(AddNoteFragmentAction.OpenNoteFragmentAction)
        }

        // cancel button
        val cancelButton = currentView.findViewById<Button>(R.id.ll_cancel_buttonLfan)
        cancelButton.setOnClickListener {
//            parentFragmentManager.beginTransaction()
//                .replace(R.id.mainFragmentView, NotesFragment())
//                .addToBackStack(null)
//                .commit()
            viewModel?.toNextScreen(AddNoteFragmentAction.OpenNoteFragmentAction)
        }

        return currentView
    }

    private fun initSharedPreferences() {
        sharedPreferences = MySharedPreferences(context)
    }

    private fun saveNoteToPreferences(currentView: View?) {
        titleEditText = currentView?.findViewById(R.id.titleEditText)!!
        contentEditText = currentView.findViewById(R.id.contentEditText)!!

        val title = titleEditText.text.toString()
        val content = contentEditText.text.toString()
        val noteDate = Calendar.getInstance().time.toString()

        if (title.isNotEmpty() && content.isNotEmpty()) {
            sharedPreferences?.addElementsToPreferences(title, content, noteDate)

        }
    }


}