package com.urban.mvvmshowcase.android

import android.app.Application
import android.content.Context
import com.urban.mvvmshowcase.model.repository.FakePersonRepository

class MvvmApplication : Application() {
    val personRepository = FakePersonRepository

    companion object {
        @JvmStatic
        fun get(context: Context): MvvmApplication = context.applicationContext as MvvmApplication
    }
}