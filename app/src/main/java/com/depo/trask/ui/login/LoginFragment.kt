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
import com.depo.trask.data.db.entities.User
import com.depo.trask.databinding.FragmentLoginBinding
import com.depo.trask.util.hide
import com.depo.trask.util.show
import kotlinx.android.synthetic.main.fragment_login.*


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
        progress_bar.show()
    }

    override fun onSuccess(user : User) {
        progress_bar.hide()
        Toast.makeText(activity, "${user.token} is logged_in", Toast.LENGTH_SHORT).show()
    }

    override fun onFailure(message: String) {
        progress_bar.hide()
        Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
    }


}