package com.urban.mvvmshowcase.view.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuItem;

import com.urban.mvvmshowcase.R;
import com.urban.mvvmshowcase.databinding.ActivityMainBinding;
import com.urban.mvvmshowcase.model.repository.FakePersonRepository;
import com.urban.mvvmshowcase.view.adapter.PeopleAdapter;
import com.urban.mvvmshowcase.view.vmwrapper.AndroidPersonListViewModel;
import com.urban.mvvmshowcase.viewmodel.PersonListViewModel;

public class MainActivity extends AbstractViewModelActivity<AndroidPersonListViewModel>
        implements PersonListViewModel.PeopleListObserver {
    private PeopleAdapter mPeopleAdapter;

    @Override
    protected AndroidPersonListViewModel createViewModel() {
        return new AndroidPersonListViewModel(FakePersonRepository.create());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding viewBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        configureViewModel(viewBinding);
        configureListAdapter(viewBinding);
    }

    private void configureListAdapter(ActivityMainBinding viewBinding) {
        mPeopleAdapter = new PeopleAdapter();
        viewBinding.peopleList.setLayoutManager(new LinearLayoutManager(this));
        viewBinding.peopleList.setAdapter(mPeopleAdapter);
    }

    private void configureViewModel(ActivityMainBinding viewBinding) {
        vm().setListObserver(this);
        viewBinding.setVm(vm());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_list, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.item_save) {
            onSaveMenuItemSelected();
        }
        return super.onOptionsItemSelected(item);
    }

    private boolean onSaveMenuItemSelected() {
        CreatePersonActivity.start(this);
        return true;
    }

    @Override
    public void onPeopleListChanged() {
        mPeopleAdapter.setPeople(vm().getPeople());
    }
}
