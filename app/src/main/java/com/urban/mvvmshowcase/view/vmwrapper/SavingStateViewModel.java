package com.urban.mvvmshowcase.view.vmwrapper;

import android.os.Bundle;

import com.urban.mvvmshowcase.viewmodel.ViewModel;

public interface SavingStateViewModel extends ViewModel {
    void onSaveState(Bundle state);

    void onRestoreState(Bundle state);
}
