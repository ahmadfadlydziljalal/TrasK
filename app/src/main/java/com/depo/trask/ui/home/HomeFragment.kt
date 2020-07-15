package com.depo.trask.ui.home

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
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
import com.depo.trask.util.ApiException
import com.depo.trask.util.NoInternetException
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeAuthenticationState()
    }

    private fun observeAuthenticationState() {
        val networkConnectionInterceptor = NetworkConnectionInterceptor(requireContext())
        val api = MyApi(networkConnectionInterceptor)
        val db = AppDatabase(context = requireActivity().applicationContext)
        val repository = UserRepository(api, db)
        val factory = LoginViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory).get(LoginViewModel::class.java)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.top_home_menu, menu)
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
            item!!,
            requireView().findNavController()
        )
                || super.onOptionsItemSelected(item)
    }

    private fun showLogoutConfirmation() {

        val builder = AlertDialog.Builder(context, R.style.Theme_MyTheme_Dialog_alert)

        builder.setMessage(
            """
                Data lokal yang belum tersinkron dengan server akan hilang.
                
                Pastikan dengan menghubungi admin sistem di office
            """.trimIndent()
        )
            .setPositiveButton("Proceed", DialogInterface.OnClickListener { _, _ ->


                GlobalScope.launch {
                    Log.w("Dzil", "inside launch with lifecycleScope")
                    viewModel.deleteLoggedInUser()
                }

                findNavController().navigate(R.id.loginFragment)

            }).show()
    }
}