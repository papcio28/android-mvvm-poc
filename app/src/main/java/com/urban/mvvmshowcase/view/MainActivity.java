package com.urban.mvvmshowcase.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import com.urban.mvvmshowcase.R;
import com.urban.mvvmshowcase.databinding.ActivityMainBinding;
import com.urban.mvvmshowcase.model.repository.FakePersonRepository;
import com.urban.mvvmshowcase.view.adapter.PeopleAdapter;
import com.urban.mvvmshowcase.view.vmwrapper.AndroidPersonListViewModel;
import com.urban.mvvmshowcase.viewmodel.PersonListViewModel;

public class MainActivity extends AppCompatActivity implements PersonListViewModel.PeopleListObserver {
    private AndroidPersonListViewModel mPersonViewModel;
    private PeopleAdapter mPeopleAdapter;

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
        mPersonViewModel = (AndroidPersonListViewModel) getLastCustomNonConfigurationInstance();
        if (mPersonViewModel == null) {
            mPersonViewModel = new AndroidPersonListViewModel(FakePersonRepository.create());
        }
        mPersonViewModel.setListObserver(this);
        viewBinding.setVm(mPersonViewModel);
    }

    @Override
    public Object onRetainCustomNonConfigurationInstance() {
        return mPersonViewModel;
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPersonViewModel.onShow();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mPersonViewModel.onHide();
    }

    @Override
    public void onPeopleListChanged() {
        mPeopleAdapter.setPeople(mPersonViewModel.getPeople());
    }
}
