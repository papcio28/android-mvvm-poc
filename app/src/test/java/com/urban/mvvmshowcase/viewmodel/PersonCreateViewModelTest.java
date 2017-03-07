package com.urban.mvvmshowcase.viewmodel;

import com.urban.mvvmshowcase.model.entity.Person;
import com.urban.mvvmshowcase.model.repository.PersonRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static com.urban.mvvmshowcase.matcher.PersonArgumentMatcher.personMatches;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class PersonCreateViewModelTest {
    public static final String TEST_PERSON_NAME = "Test Person";
    public static final int TEST_PERSON_AGE = 27;
    @Mock
    private PersonRepository mPersonRepository;
    @Mock
    private Navigator mNavigator;
    private PersonCreateViewModel mViewModel;

    @Before
    public void setUp() {
        mViewModel = new PersonCreateViewModel(mNavigator, mPersonRepository);
    }

    @Test
    public void shouldPersistPersonInRepository() {
        // given
        setViewModelPersonParams();

        // when
        mViewModel.onSavePerson();

        // then
        verify(mPersonRepository)
                .add(personMatches(new Person(TEST_PERSON_NAME, TEST_PERSON_AGE, null)));
    }

    private void setViewModelPersonParams() {
        mViewModel.setPersonName(TEST_PERSON_NAME);
        mViewModel.setPersonAge(TEST_PERSON_AGE);
    }

    @Test
    public void shouldHideViewModelAfterSave() {
        // given
        setViewModelPersonParams();

        // when
        mViewModel.onSavePerson();

        // then
        verify(mNavigator, times(1)).hide();
    }
}