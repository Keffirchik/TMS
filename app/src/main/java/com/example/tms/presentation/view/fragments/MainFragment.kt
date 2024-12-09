package com.example.tms.presentation.view.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.tms.R
import com.example.tms.data.api.DisneyAPI
import com.example.tms.databinding.FragmentMainBinding
import com.example.tms.presentation.view.MainFragmentAction
import com.example.tms.presentation.view_model.MainFragmentModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainFragment : Fragment() {

    private var viewModel: MainFragmentModel? = null
    private var _binding: FragmentMainBinding? = null
    private val binding: FragmentMainBinding get() = _binding!!

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

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater)

        //login button
        binding.fragmentMainButton.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_mainLoginFragment)
            viewModel?.toNextScreen(MainFragmentAction.OpenLoginFragment)
        }

        //button to second screen
        binding.buttonToSecondActivity.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_secondFragment)
        }

        //button to notes
        binding.acbGoToNotesAm.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_notesFragment)
        }

        // api text button
        val api = retrofit.create(DisneyAPI::class.java)

        binding.acbApiNotesAm.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                val responseBody = api.getOneCharacter(308)
                Log.d("Response", "$responseBody")
            }
        }

        return binding.root
    }

}