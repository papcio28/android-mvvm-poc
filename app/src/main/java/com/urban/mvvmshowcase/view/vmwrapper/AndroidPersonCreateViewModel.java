package com.urban.mvvmshowcase.view.vmwrapper;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.text.TextWatcher;
import android.view.View;

import com.urban.mvvmshowcase.model.entity.Person;
import com.urban.mvvmshowcase.view.utility.DefaultTextWatcher;
import com.urban.mvvmshowcase.viewmodel.PersonCreateViewModel;

import static android.support.annotation.VisibleForTesting.PRIVATE;

public class AndroidPersonCreateViewModel extends BaseObservable implements SavingStateViewModel {
    @VisibleForTesting(otherwise = PRIVATE)
    static final String STATE_KEY_NAME = "name";
    @VisibleForTesting(otherwise = PRIVATE)
    static final String STATE_KEY_AGE = "age";

    private final PersonCreateViewModel createViewModel;

    public AndroidPersonCreateViewModel(PersonCreateViewModel personCreateViewModel) {
        createViewModel = personCreateViewModel;
    }

    @BindingAdapter("android:onClick")
    public static void setOnClickListener(View view, View.OnClickListener listener) {
        view.setOnClickListener(listener);
    }

    @Bindable
    public Person getPerson() {
        return createViewModel.getPerson();
    }

    public void setPerson(@Nullable Person person) {
        createViewModel.setPerson(person);
    }

    public TextWatcher getPersonNameWatcher() {
        return new DefaultTextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                createViewModel.setPerson(getPerson().withName(s.toString()));
            }
        };
    }

    public TextWatcher getPersonAgeWatcher() {
        return new DefaultTextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                createViewModel.setPerson(
                        getPerson().withAge(s.length() == 0 ? 0 : Integer.parseInt(s.toString())));
            }
        };
    }

    public void onPersonSave() {
        createViewModel.onSavePerson();
    }

    @Override
    public void onSaveState(Bundle state) {
        state.putString(STATE_KEY_NAME, getPerson().getName());
        state.putInt(STATE_KEY_AGE, getPerson().getAge());
    }

    @Override
    public void onRestoreState(Bundle state) {
        createViewModel.setPerson(new Person(state.getString(STATE_KEY_NAME, ""),
                state.getInt(STATE_KEY_AGE, Person.DEFAULT_AGE)));
    }
}
