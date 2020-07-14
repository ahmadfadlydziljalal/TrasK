package com.depo.trask.util

import android.content.Context
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_login.view.*

fun Context.toast(message : String){
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun ProgressBar.show(){
    progress_bar.visibility = View.VISIBLE
}

fun ProgressBar.hide(){
    progress_bar.visibility = View.GONE
}

fun View.snackbar(message: String){
    Snackbar.make(this, message, Snackbar.LENGTH_LONG).also {snackbar ->
        snackbar.setAction("OK"){
            snackbar.dismiss()
        }
    }.show()
}