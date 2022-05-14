package com.giangnh44.base_lib.extension.extension

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

fun Fragment.toastShort(message: String) {
    Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
}

fun Fragment.toastLong(message: String) {
    Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
}