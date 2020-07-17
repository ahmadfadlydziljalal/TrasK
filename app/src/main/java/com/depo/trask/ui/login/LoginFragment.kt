package com.depo.trask.ui.login

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.depo.trask.R
import com.depo.trask.data.db.AppDatabase
import com.depo.trask.data.network.MyApi
import com.depo.trask.data.network.NetworkConnectionInterceptor
import com.depo.trask.data.repositories.UserRepository
import com.depo.trask.databinding.FragmentLoginBinding
import com.depo.trask.util.*
import kotlinx.coroutines.launch


class LoginFragment : Fragment() {

    private lateinit var loginViewModel: LoginViewModel
    private lateinit var binding: FragmentLoginBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(

        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {

        val networkConnectionInterceptor = NetworkConnectionInterceptor(requireContext())
        val api = MyApi(networkConnectionInterceptor)
        val db = AppDatabase(context = requireActivity().applicationContext)
        val repository = UserRepository(api, db)
        val factory = LoginViewModelFactory(repository)

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_login,
            container,
            false
        )

        loginViewModel = ViewModelProvider(this, factory).get(LoginViewModel::class.java)
        binding.buttonLogin.setOnClickListener { loginUser() }

        loginViewModel.getLoggedInUser().observe(viewLifecycleOwner, Observer { user ->
            val navController = findNavController()
            if (user != null) {
                navController.navigate(R.id.homeFragment)
            }
        })

        return binding.root
    }

    private fun loginUser() {
        val username = binding.editTextUsername.text.toString().trim()
        val password = binding.editTextPassword.text.toString().trim()

        binding.progressBar.show()
        lifecycleScope.launch {
            try {
                val loginResponse = loginViewModel.userLogin(username!!, password!!)

                if (loginResponse.user != null) {
                    loginViewModel.saveLoggedInUser(loginResponse.user)
                    loginResponse.user.token?.let { saveToken(it) }
                }

                binding.progressBar.hide()
                binding.root.context.toast(loginResponse.message!!)

            } catch (e: ApiException) {
                binding.progressBar.hide()
                binding.root.context.toast(e.toString())
            } catch (e: NoInternetException) {
                binding.progressBar.hide()
                binding.root.context.toast(e.toString())
            }
        }
    }

    private fun saveToken(token: String) {
        val sharedPreferences =
            this.requireActivity().getSharedPreferences("session", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("token", token)
        editor.apply()
    }
}