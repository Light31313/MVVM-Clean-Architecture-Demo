package com.giangnh44.base_lib.extension.extension

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

fun AppCompatActivity.toastShort(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun AppCompatActivity.toastLong(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}