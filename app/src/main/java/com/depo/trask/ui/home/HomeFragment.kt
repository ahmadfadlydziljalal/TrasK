package com.depo.trask.ui.home

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.depo.trask.R
import com.depo.trask.data.db.AppDatabase
import com.depo.trask.data.network.MyApi
import com.depo.trask.data.network.NetworkConnectionInterceptor
import com.depo.trask.data.repositories.UserRepository
import com.depo.trask.ui.login.LoginViewModel
import com.depo.trask.ui.login.LoginViewModelFactory
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class HomeFragment : Fragment() {

    private lateinit var loginViewModel: LoginViewModel
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        navController = findNavController()
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val preferences =
            this.requireActivity().getSharedPreferences("session", Context.MODE_PRIVATE)
        val token = preferences.getString("token", null);

        if (token == null) {
            navController.navigate(R.id.loginFragment)
        }


        observeAuthenticationState()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.top_home_menu, menu)
    }

    private fun observeAuthenticationState() {

        val networkConnectionInterceptor = NetworkConnectionInterceptor(requireContext())

        val api = MyApi(networkConnectionInterceptor)
        val db = AppDatabase(context = requireActivity().applicationContext)

        val repository = UserRepository(api, db)
        val factory = LoginViewModelFactory(repository)

        loginViewModel = ViewModelProvider(this, factory).get(LoginViewModel::class.java)
        loginViewModel.getLoggedInUser().observe(viewLifecycleOwner, Observer { user ->
            val navController = findNavController()
            if (user == null) {
                navController.navigate(R.id.loginFragment)
            }
        })
    }

    private fun showLogoutConfirmation() {
        val builder = AlertDialog.Builder(context, R.style.Theme_MyTheme_Dialog_alert)
        builder.setMessage(
            """
                Are You Sure To Logout ?
            """.trimIndent()
        )
            .setPositiveButton("Proceed") { _, _ ->
                GlobalScope.launch {
                    deleteToken()
                    loginViewModel.deleteLoggedInUser()
                }
            }.show()
    }

    private fun deleteToken() {
        val sharedPreferences =
            this.requireActivity().getSharedPreferences("session", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("token", null)
        editor.apply()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val id = item.itemId
        val navController = findNavController()

        if (id == R.id.action_settings) {
            navController.navigate(R.id.action_homeFragment_to_settingsFragment)
            return true
        }

        if (id == R.id.action_renew_home_information) {
            Toast.makeText(context, "//TODO: Mengambil data dari Server", Toast.LENGTH_SHORT).show()
            return true
        }

        if (id == R.id.action_logout) {
            showLogoutConfirmation()
            return true
        }

        return NavigationUI.onNavDestinationSelected(
            item,
            requireView().findNavController()
        ) || super.onOptionsItemSelected(item)
    }
}