package com.urban.mvvmshowcase.view.vmwrapper;

import android.os.Bundle;
import android.text.TextWatcher;
import android.view.View;

import com.urban.mvvmshowcase.BuildConfig;
import com.urban.mvvmshowcase.model.entity.Person;
import com.urban.mvvmshowcase.viewmodel.PersonCreateViewModel;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static com.urban.mvvmshowcase.view.vmwrapper.AndroidPersonCreateViewModel.STATE_KEY_AGE;
import static com.urban.mvvmshowcase.view.vmwrapper.AndroidPersonCreateViewModel.STATE_KEY_NAME;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 22)
public class AndroidPersonCreateViewModelTest {
    private static final Person TEST_PERSON = new Person("Test Person", 27);

    @Mock
    private PersonCreateViewModel basicViewModel;
    private AndroidPersonCreateViewModel androidViewModel;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        androidViewModel = new AndroidPersonCreateViewModel(basicViewModel);

        when(basicViewModel.getPerson()).thenReturn(TEST_PERSON);
    }

    @Test
    public void bindingAdapterShouldSetOnClickListenerOnView() {
        // given
        View mockedView = mock(View.class);
        View.OnClickListener testListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        };

        // when
        AndroidPersonCreateViewModel.setOnClickListener(mockedView, testListener);

        // then
        verify(mockedView).setOnClickListener(testListener);
    }

    @Test
    public void shouldReturnExactSamePersonAsBaseViewModel() {
        assertTrue(androidViewModel.getPerson() == TEST_PERSON);
    }

    @Test
    public void shouldSetNewPersonToBaseViewModel() {
        // when
        androidViewModel.setPerson(TEST_PERSON);

        // then
        verify(basicViewModel, times(1)).setPerson(eq(TEST_PERSON));
    }

    @Test
    public void shouldCallBaseViewModelPersonSaveLogic() {
        // when
        androidViewModel.onPersonSave();

        // then
        verify(basicViewModel, times(1)).onSavePerson();
    }

    @Test
    public void shouldCallBaseViewModelOnShow() {
        // when
        androidViewModel.onShow();

        // then
        verify(basicViewModel, times(1)).onShow();
    }

    @Test
    public void shouldCallBaseViewModelOnHide() {
        // when
        androidViewModel.onHide();

        // then
        verify(basicViewModel, times(1)).onHide();
    }

    @Test
    public void shouldSavePersonDataWhenInstanceStateIsSaved() {
        // given
        Bundle savedState = new Bundle();

        // when
        androidViewModel.onSaveState(savedState);

        // then
        assertEquals(savedState.getString(STATE_KEY_NAME),
                TEST_PERSON.getName());
        assertEquals(savedState.getInt(STATE_KEY_AGE),
                TEST_PERSON.getAge());
    }

    @Test
    public void shouldRestorePersonDataWhenInstanceStateIsRestored() {
        // given
        Bundle savedState = new Bundle();
        savedState.putString(STATE_KEY_NAME, TEST_PERSON.getName());
        savedState.putInt(STATE_KEY_AGE, TEST_PERSON.getAge());

        PersonWrapper setPerson = configureBaseModelPersonSetter();

        // when
        androidViewModel.onRestoreState(savedState);

        // then
        assertEquals(setPerson.person.getName(), TEST_PERSON.getName());
        assertEquals(setPerson.person.getAge(), TEST_PERSON.getAge());
    }

    private PersonWrapper configureBaseModelPersonSetter() {
        final PersonWrapper person = new PersonWrapper();
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                person.person = invocation.getArgument(0);
                return null;
            }
        }).when(basicViewModel).setPerson(any(Person.class));
        return person;
    }

    @Test
    public void personNameWatcherShouldSetCorrectPersonName() {
        // given
        TextWatcher watcher = androidViewModel.getPersonNameWatcher();
        PersonWrapper setPerson = configureBaseModelPersonSetter();

        // when
        int nameLength = TEST_PERSON.getName().length();
        watcher.onTextChanged(TEST_PERSON.getName(), 0, nameLength - 1, nameLength);

        // then
        assertEquals(setPerson.person.getName(), TEST_PERSON.getName());
    }

    @Test
    public void personAgeWatcherShouldSetCorrectPersonAge() {
        // given
        TextWatcher watcher = androidViewModel.getPersonAgeWatcher();
        PersonWrapper setPerson = configureBaseModelPersonSetter();

        // when
        String ageString = Integer.toString(TEST_PERSON.getAge());
        int ageLength = ageString.length();
        watcher.onTextChanged(ageString, 0, ageLength - 1, ageLength);

        // then
        assertEquals(setPerson.person.getAge(), TEST_PERSON.getAge());
    }

    private final static class PersonWrapper {
        private Person person;
    }
}