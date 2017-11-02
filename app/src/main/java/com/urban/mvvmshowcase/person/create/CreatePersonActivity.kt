package com.urban.mvvmshowcase.person.create

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import com.urban.mvvmshowcase.R
import com.urban.mvvmshowcase.android.MvvmApplication
import com.urban.mvvmshowcase.databinding.ActivityCreatePersonBinding
import com.urban.mvvmshowcase.person.Person
import com.urban.mvvmshowcase.person.PersonRepository
import com.urban.mvvmshowcase.android.base.AbstractViewModelActivity
import java.util.*

class CreatePersonActivity : AbstractViewModelActivity<PersonCreateViewModel>(),
        PersonCreateViewAccess {
    private lateinit var personRepository: PersonRepository

    companion object {
        private val EXTRA_ID = "id"

        private fun openIntent(context: Context): Intent {
            return Intent(context, CreatePersonActivity::class.java)
        }

        fun start(context: Context) {
            context.startActivity(openIntent(context))
        }

        fun startEdit(context: Context, person: Person) {
            val editIntent = openIntent(context)
            editIntent.putExtra(EXTRA_ID, person.id)
            context.startActivity(editIntent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        personRepository = MvvmApplication.get(this).personRepository

        super.onCreate(savedInstanceState)
        val viewBinding: ActivityCreatePersonBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_create_person)
        viewBinding.vm = vm()

        if (wasLaunchedWithPersonId()) {
            personRepository.get(getEditPersonId())?.let {
                vm().person = it
            }
        }
    }

    private fun getEditPersonId() = intent.getSerializableExtra(EXTRA_ID) as UUID

    private fun wasLaunchedWithPersonId() = intent.hasExtra(EXTRA_ID)

    override fun hide() = finish()

    override fun createViewModel(): PersonCreateViewModel =
            PersonCreateViewModel(personRepository, this)
}