package com.urban.mvvmshowcase.android.util

import android.databinding.BindingAdapter
import android.view.View

@BindingAdapter("android:onClick")
fun setOnClickListener(view: View, listener: View.OnClickListener)
        = view.setOnClickListener(listener)