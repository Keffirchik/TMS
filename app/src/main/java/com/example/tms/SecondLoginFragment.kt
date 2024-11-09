package com.example.tms

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val LOGIN = "loginInput"

/**
 * A simple [Fragment] subclass.
 * Use the [SecondLoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SecondLoginFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var loginInput: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            loginInput = it.getString(LOGIN)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val currentView = inflater.inflate(R.layout.fragment_second_login, container, false)


        val textView = currentView.findViewById<TextView>(R.id.cl_second_text_view_slf)
        val login = arguments?.getString("loginInput")
        textView.text = "Hello $login we are happy to see you!"

        // Inflate the layout for this fragment
        return currentView
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @return A new instance of fragment SecondLoginFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String) =
            SecondLoginFragment().apply {
                arguments = Bundle().apply {
                    putString(LOGIN, param1)
                }
            }
    }
}