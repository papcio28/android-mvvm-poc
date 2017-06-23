package com.urban.mvvmshowcase.view.activity

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import com.urban.mvvmshowcase.R
import com.urban.mvvmshowcase.android.MvvmApplication
import com.urban.mvvmshowcase.databinding.ActivityCreatePersonBinding
import com.urban.mvvmshowcase.model.entity.Person
import com.urban.mvvmshowcase.model.repository.PersonRepository
import com.urban.mvvmshowcase.view.activity.base.AbstractSavingStateViewModelActivity
import com.urban.mvvmshowcase.view.vmwrapper.AndroidPersonCreateViewModel
import com.urban.mvvmshowcase.viewmodel.Navigator
import com.urban.mvvmshowcase.viewmodel.PersonCreateViewModel
import java.util.*

class CreatePersonActivity : AbstractSavingStateViewModelActivity<AndroidPersonCreateViewModel>(),
        Navigator {
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
        val viewBinding = DataBindingUtil.setContentView<ActivityCreatePersonBinding>(this, R.layout.activity_create_person)
        viewBinding.vm = vm()

        if (wasLaunchedWithPersonId()) {
            vm().person = personRepository.get(getEditPersonId())
        }
    }

    private fun getEditPersonId(): UUID {
        return intent.getSerializableExtra(EXTRA_ID) as UUID
    }

    private fun wasLaunchedWithPersonId(): Boolean {
        return intent.hasExtra(EXTRA_ID)
    }

    override fun hide() = finish()

    override fun createViewModel(): AndroidPersonCreateViewModel = AndroidPersonCreateViewModel(
            PersonCreateViewModel(this, personRepository))
}