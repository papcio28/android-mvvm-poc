package com.urban.mvvmshowcase.view.activity

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.urban.mvvmshowcase.R
import com.urban.mvvmshowcase.android.MvvmApplication
import com.urban.mvvmshowcase.databinding.ActivityMainBinding
import com.urban.mvvmshowcase.model.entity.Person
import com.urban.mvvmshowcase.view.activity.base.AbstractViewModelActivity
import com.urban.mvvmshowcase.view.adapter.ClickListener
import com.urban.mvvmshowcase.view.adapter.PeopleAdapter
import com.urban.mvvmshowcase.viewmodel.PersonListViewAccess
import com.urban.mvvmshowcase.viewmodel.PersonListViewModel

class MainActivity : AbstractViewModelActivity<PersonListViewModel>(),
        ClickListener, PersonListViewAccess {
    lateinit var listAdapter: PeopleAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewBinding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        listAdapter = PeopleAdapter(this, vm().people)

        viewBinding.vm = vm()
        viewBinding.view = this
    }

    override fun onResume() {
        super.onResume()
        vm().viewAccess = this
        vm().loadPeople()
    }

    override fun onPause() {
        super.onPause()
        vm().viewAccess = null
        vm().cancelLoading()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_list, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.item_save -> CreatePersonActivity.start(this).let { true }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun notifyDataChanged() = listAdapter.notifyDataSetChanged()

    override fun onPersonClick(person: Person) = CreatePersonActivity.startEdit(this, person)

    override fun createViewModel(): PersonListViewModel =
            PersonListViewModel(MvvmApplication.get(this).personRepository, this)
}