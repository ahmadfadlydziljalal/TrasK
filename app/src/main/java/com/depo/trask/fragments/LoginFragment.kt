package com.depo.trask.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.depo.trask.R

/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : Fragment()  {

    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = findNavController()


        usernameEditText = view.findViewById(R.id.edit_text_username)
        passwordEditText = view.findViewById(R.id.edit_text_password)

        loginButton = view.findViewById(R.id.button_login)
        loginButton.setOnClickListener {

            // Keluarkan Login fragment dari graph
//            navController.popBackStack(R.id.homeFragment, false)

            val bundle = bundleOf("authentication" to "authenticated")
            navController.navigate(R.id.homeFragment)



        }



    }

}