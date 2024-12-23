package com.example.tms.presentation.view.fragments

import android.icu.util.Calendar
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.tms.App
import com.example.tms.R
import com.example.tms.data.storage.RoomDB
import com.example.tms.data.storage.RoomObject
import com.example.tms.databinding.FragmentAddNoteBinding
import com.example.tms.domain.models.Note
import com.example.tms.presentation.view.AddNoteFragmentAction
import com.example.tms.presentation.view_model.AddFragmentModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class AddNoteFragment : Fragment() {

    private var viewModel: AddFragmentModel? = null

    private lateinit var titleEditText: EditText
    private lateinit var contentEditText: EditText

    private var _binding: FragmentAddNoteBinding? = null
    private val binding: FragmentAddNoteBinding get() = _binding!!

    @Inject
    lateinit var roomDB: RoomDB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (requireActivity().application as App).appComponent?.inject(this)

        viewModel =
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
                .create(AddFragmentModel::class.java)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel?.publicLiveData?.observe(this.viewLifecycleOwner) { event ->
            if (event == null) return@observe
            when (event) {
                AddNoteFragmentAction.OpenNoteFragmentAction -> NotesFragment()
            }
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddNoteBinding.inflate(inflater)

        // save button
        binding.llAddNewNoteFan.setOnClickListener {
            saveNoteToDB(binding.root)
            findNavController().navigate(R.id.notesFragment)
        }

        // cancel button
        binding.llCancelButtonLfan.setOnClickListener {
            findNavController().navigate(R.id.notesFragment)
        }
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun saveNoteToDB(currentView: View?) {
        titleEditText = currentView?.findViewById(R.id.titleEditText)!!
        contentEditText = currentView.findViewById(R.id.contentEditText)!!

        val title = titleEditText.text.toString()
        val content = contentEditText.text.toString()
        val noteDate = Calendar.getInstance().time.toString()

        val note = Note(title = title, content = content, noteDate = noteDate)

        if (title.isNotEmpty() && content.isNotEmpty()) {
            lifecycleScope.launch(Dispatchers.IO) {
//                RoomObject.putInDB(note)
                roomDB.noteDao().putNote(note)
            }
        }
    }
}