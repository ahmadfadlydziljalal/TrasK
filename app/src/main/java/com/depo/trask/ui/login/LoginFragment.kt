package com.depo.trask.ui.login

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import com.depo.trask.R
import com.depo.trask.databinding.FragmentLoginBinding


class LoginFragment : Fragment(), LoginListener {

    private lateinit var viewModel: LoginViewModel
    private lateinit var binding : FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_login,
            container,
            false
        )

        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        binding.viewmodel = viewModel
        viewModel.loginListener = this

        return binding.root

    }

    companion object {
        fun newInstance() = LoginFragment()
    }

    override fun onStarted() {
        Log.i("Login", "Started")
    }

    override fun onSuccess(loginResponse: LiveData<String>) {
        Log.i("Login", "Success " + loginResponse.toString())
    }

    override fun onFailure(message: String) {
        Log.i("Login", "Failed " + message)
    }


}