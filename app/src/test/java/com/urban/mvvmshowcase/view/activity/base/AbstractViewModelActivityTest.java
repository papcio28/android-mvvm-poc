package com.urban.mvvmshowcase.view.activity.base;

import android.support.annotation.NonNull;

import com.urban.mvvmshowcase.BuildConfig;
import com.urban.mvvmshowcase.utility.ActivityUtil;
import com.urban.mvvmshowcase.viewmodel.ViewModel;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.android.controller.ActivityController;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 22)
public class AbstractViewModelActivityTest {
    @Mock
    private ViewModel viewModel;
    private ActivityController<TestViewModelActivity> activityController;
    private TestViewModelActivity activity;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        activityController = Robolectric.buildActivity(TestViewModelActivity.class);
        activity = activityController.get();
    }

    @Test
    public void viewModelIsAvailableAfterOnCreate() {
        // arrange
        activity.setTestViewModel(viewModel);

        // act
        activityController.create();

        // assert
        assertNotNull(activity.vm());
    }

    @Test
    public void viewModelGetsOnShowCallAfterActivityResume() {
        // arrange
        activity.setTestViewModel(viewModel);

        // act
        ActivityUtil.freshActivityStart(activityController);

        // assert
        verify(viewModel, times(1)).onShow();
    }

    @Test
    public void viewModelGetsOnHideCallAfterActivityPause() {
        // arrange
        activity.setTestViewModel(viewModel);

        // act
        ActivityUtil.freshActivityStart(activityController)
                .pause().stop().destroy();

        // assert
        verify(viewModel, times(1)).onHide();
    }

    @Test
    public void viewModelGetsPersistedAsNonConfigurationInstance() {
        // arrange
        activity.setTestViewModel(viewModel);

        // act
        ActivityUtil.freshActivityStart(activityController);

        // assert
        assertEquals(viewModel, activity.onRetainCustomNonConfigurationInstance());
    }

    public static class TestViewModelActivity extends AbstractViewModelActivity<ViewModel> {
        private ViewModel testViewModel;

        void setTestViewModel(ViewModel testViewModel) {
            this.testViewModel = testViewModel;
        }

        @NonNull
        @Override
        protected ViewModel createViewModel() {
            return testViewModel;
        }
    }
}