package com.urban.mvvmshowcase.view.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuItem;

import com.urban.mvvmshowcase.R;
import com.urban.mvvmshowcase.android.MvvmApplication;
import com.urban.mvvmshowcase.databinding.ActivityMainBinding;
import com.urban.mvvmshowcase.model.entity.Person;
import com.urban.mvvmshowcase.view.activity.base.AbstractViewModelActivity;
import com.urban.mvvmshowcase.view.adapter.PeopleAdapter;
import com.urban.mvvmshowcase.view.vmwrapper.AndroidPersonListViewModel;
import com.urban.mvvmshowcase.viewmodel.PersonListViewModel;

import java.util.List;

public class MainActivity extends AbstractViewModelActivity<AndroidPersonListViewModel>
        implements PersonListViewModel.PeopleListObserver {
    private PeopleAdapter peopleAdapter;

    @NonNull
    @Override
    protected AndroidPersonListViewModel createViewModel() {
        return new AndroidPersonListViewModel(MvvmApplication.get(this).getPersonRepository());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding viewBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        configureViewModel(viewBinding);
        configureListAdapter(viewBinding);
    }

    private void configureListAdapter(ActivityMainBinding viewBinding) {
        peopleAdapter = new PeopleAdapter();
        viewBinding.peopleList.setLayoutManager(new LinearLayoutManager(this));
        viewBinding.peopleList.setAdapter(peopleAdapter);
    }

    private void configureViewModel(ActivityMainBinding viewBinding) {
        vm().setListObserver(this);
        viewBinding.setVm(vm());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.item_save) {
            CreatePersonActivity.start(this);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPeopleListChanged(List<Person> people) {
        peopleAdapter.setPeople(people);
    }
}
