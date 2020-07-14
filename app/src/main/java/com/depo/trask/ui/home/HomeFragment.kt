package com.depo.trask.ui.home

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.depo.trask.R



class HomeFragment : Fragment() {

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
        val navController = findNavController()
        navController.navigate(R.id.action_global_loginFragment)


//        Log.i("Login", viewModel.username)

//        viewModel.getLoggedInUser().observe(viewLifecycleOwner, Observer { user ->
//            if(user == null){
//                navController.navigate(R.id.action_global_loginFragment)
//            }
//        })
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

        return NavigationUI.onNavDestinationSelected(
            item!!,
            requireView().findNavController()
        )
                || super.onOptionsItemSelected(item)

    }
}