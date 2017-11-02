package com.urban.mvvmshowcase.view.utility

import android.databinding.BindingAdapter
import android.view.View

@BindingAdapter("android:onClick")
fun setOnClickListener(view: View, listener: View.OnClickListener)
        = view.setOnClickListener(listener)