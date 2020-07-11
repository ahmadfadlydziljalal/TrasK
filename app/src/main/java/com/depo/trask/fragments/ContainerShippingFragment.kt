package com.depo.trask.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.depo.trask.R

/**
 * A simple [Fragment] subclass.
 */
class ContainerShippingFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        setHasOptionsMenu(true)
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_container_shipping, container, false)
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.top_container_shipping_menu, menu)
    }

}