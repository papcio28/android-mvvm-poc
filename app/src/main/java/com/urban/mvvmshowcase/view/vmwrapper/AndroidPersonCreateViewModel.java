package com.urban.mvvmshowcase.view.vmwrapper;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.text.TextWatcher;
import android.view.View;

import com.urban.mvvmshowcase.BR;
import com.urban.mvvmshowcase.view.utility.DefaultTextWatcher;
import com.urban.mvvmshowcase.viewmodel.PersonCreateViewModel;
import com.urban.mvvmshowcase.viewmodel.ViewModel;

public class AndroidPersonCreateViewModel extends BaseObservable implements ViewModel {
    private final PersonCreateViewModel mPersonCreateViewModel;

    @BindingAdapter("android:onClick")
    public static void setOnClickListener(View view, View.OnClickListener listener) {
        view.setOnClickListener(listener);
    }

    public AndroidPersonCreateViewModel(PersonCreateViewModel personCreateViewModel) {
        mPersonCreateViewModel = personCreateViewModel;
    }

    @Bindable
    public String getPersonName() {
        return mPersonCreateViewModel.getPersonName();
    }

    public void setPersonName(String personName) {
        mPersonCreateViewModel.setPersonName(personName);
        notifyPropertyChanged(BR.personName);
    }

    @Bindable
    public int getPersonAge() {
        return mPersonCreateViewModel.getPersonAge();
    }

    public void setPersonAge(int personAge) {
        mPersonCreateViewModel.setPersonAge(personAge);
        notifyPropertyChanged(BR.personAge);
    }

    public TextWatcher getPersonNameWatcher() {
        return new DefaultTextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mPersonCreateViewModel.setPersonName(s.toString());
            }
        };
    }

    public TextWatcher getPersonAgeWatcher() {
        return new DefaultTextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mPersonCreateViewModel.setPersonAge(s.length() == 0 ? 0 : Integer.parseInt(s.toString()));
            }
        };
    }

    public void onPersonSave() {
        mPersonCreateViewModel.onSavePerson();
    }

    @Override
    public void onShow() {
        mPersonCreateViewModel.onShow();
    }

    @Override
    public void onHide() {
        mPersonCreateViewModel.onHide();
    }
}
