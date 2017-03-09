package com.urban.mvvmshowcase.view.vmwrapper;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.os.Bundle;
import android.text.TextWatcher;
import android.view.View;

import com.urban.mvvmshowcase.model.entity.Person;
import com.urban.mvvmshowcase.view.utility.DefaultTextWatcher;
import com.urban.mvvmshowcase.viewmodel.PersonCreateViewModel;

public class AndroidPersonCreateViewModel extends BaseObservable implements SavingStateViewModel {
    private static final String STATE_KEY_NAME = "name";
    private static final String STATE_KEY_AGE = "age";

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

    @Override
    public void onSaveState(Bundle state) {
        state.putString(STATE_KEY_NAME, getPersonName());
        state.putInt(STATE_KEY_AGE, getPersonAge());
    }

    @Override
    public void onRestoreState(Bundle state) {
        createViewModel.setPersonName(state.getString(STATE_KEY_NAME, ""));
        createViewModel.setPersonAge(state.getInt(STATE_KEY_AGE, Person.DEFAULT_AGE));
    }
}
