package com.urban.mvvmshowcase.view.vmwrapper;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.text.TextWatcher;
import android.view.View;

import com.urban.mvvmshowcase.view.utility.DefaultTextWatcher;
import com.urban.mvvmshowcase.viewmodel.PersonCreateViewModel;
import com.urban.mvvmshowcase.viewmodel.ViewModel;

public class AndroidPersonCreateViewModel extends BaseObservable implements ViewModel {
    private final PersonCreateViewModel createViewModel;

    @BindingAdapter("android:onClick")
    public static void setOnClickListener(View view, View.OnClickListener listener) {
        view.setOnClickListener(listener);
    }

    public AndroidPersonCreateViewModel(PersonCreateViewModel personCreateViewModel) {
        createViewModel = personCreateViewModel;
    }

    @Bindable
    public String getPersonName() {
        return createViewModel.getPersonName();
    }

    @Bindable
    public int getPersonAge() {
        return createViewModel.getPersonAge();
    }

    public TextWatcher getPersonNameWatcher() {
        return new DefaultTextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                createViewModel.setPersonName(s.toString());
            }
        };
    }

    public TextWatcher getPersonAgeWatcher() {
        return new DefaultTextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                createViewModel.setPersonAge(s.length() == 0 ? 0 : Integer.parseInt(s.toString()));
            }
        };
    }

    public void onPersonSave() {
        createViewModel.onSavePerson();
    }

    @Override
    public void onShow() {
        createViewModel.onShow();
    }

    @Override
    public void onHide() {
        createViewModel.onHide();
    }
}
