package com.urban.mvvmshowcase.view.activity.base;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.urban.mvvmshowcase.BuildConfig;
import com.urban.mvvmshowcase.utility.ActivityUtil;
import com.urban.mvvmshowcase.view.vmwrapper.SavingStateViewModel;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.android.controller.ActivityController;
import org.robolectric.annotation.Config;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 22)
public class AbstractSavingStateViewModelActivityTest {
    @Mock
    private SavingStateViewModel viewModel;
    private ActivityController<TestSavingStateViewModelActivity> activityController;
    private TestSavingStateViewModelActivity activity;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        activityController = Robolectric.buildActivity(TestSavingStateViewModelActivity.class);
        activity = activityController.get();
    }

    @Test
    public void viewModelOnSaveShouldBeCalled() {
        // arrange
        activity.setTestViewModel(viewModel);

        // act
        ActivityUtil.freshActivityStart(activityController)
                .pause().saveInstanceState(new Bundle()).stop().destroy();

        // assert
        verify(viewModel, times(1)).onSaveState(any(Bundle.class));
    }

    @Test
    public void viewModelOnRestoreShouldBeCalled() {
        // arrange
        activity.setTestViewModel(viewModel);

        // act
        ActivityUtil.restoredActivityStart(activityController);

        // assert
        verify(viewModel, times(1)).onRestoreState(any(Bundle.class));
    }

    private static class TestSavingStateViewModelActivity
            extends AbstractSavingStateViewModelActivity<SavingStateViewModel> {
        private SavingStateViewModel viewModel;

        void setTestViewModel(SavingStateViewModel viewModel) {
            this.viewModel = viewModel;
        }

        @NonNull
        @Override
        protected SavingStateViewModel createViewModel() {
            return viewModel;
        }
    }
}