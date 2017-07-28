package com.urban.mvvmshowcase.view.activity

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import com.urban.mvvmshowcase.R
import com.urban.mvvmshowcase.android.MvvmApplication
import com.urban.mvvmshowcase.databinding.ActivityMainBinding
import com.urban.mvvmshowcase.model.entity.Person
import com.urban.mvvmshowcase.view.activity.base.AbstractViewModelActivity
import com.urban.mvvmshowcase.view.adapter.ClickListener
import com.urban.mvvmshowcase.view.adapter.PeopleAdapter
import com.urban.mvvmshowcase.view.vmwrapper.AndroidPersonListViewModel
import com.urban.mvvmshowcase.viewmodel.PeopleListObserver
import com.urban.mvvmshowcase.viewmodel.PersonListNavigator

class MainActivity : AbstractViewModelActivity<AndroidPersonListViewModel>(),
        PeopleListObserver, PersonListNavigator, ClickListener {
    private var peopleAdapter = PeopleAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewBinding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        configureViewModel(viewBinding)
        configureListAdapter(viewBinding)
    }

    private fun configureListAdapter(viewBinding: ActivityMainBinding) {
        viewBinding.peopleList.layoutManager = LinearLayoutManager(this)
        viewBinding.peopleList.adapter = peopleAdapter
    }

    private fun configureViewModel(viewBinding: ActivityMainBinding) {
        vm().setListObserver(this)
        viewBinding.vm = vm()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_list, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_save -> vm().openCreatePersonScreen()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun openCreatePersonScreen() = CreatePersonActivity.start(this)

    override fun openEditPersonScreen(person: Person) = CreatePersonActivity.startEdit(this, person)

    override fun onPersonClick(person: Person) = vm().openEditPersonScreen(person)

    override fun createViewModel(): AndroidPersonListViewModel =
            AndroidPersonListViewModel(MvvmApplication.get(this).personRepository, this)

    override fun onPeopleListChanged(people: List<Person>) {
        peopleAdapter.people = people
    }
}