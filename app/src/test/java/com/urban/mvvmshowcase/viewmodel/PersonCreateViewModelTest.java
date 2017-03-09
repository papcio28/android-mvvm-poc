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
    private PersonRepository peopleRepository;
    @Mock
    private Navigator navigator;
    private PersonCreateViewModel viewModel;

    @Before
    public void setUp() {
        viewModel = new PersonCreateViewModel(navigator, peopleRepository);
    }

    @Test
    public void shouldPersistPersonInRepository() {
        // given
        setViewModelPersonParams();

        // when
        viewModel.onSavePerson();

        // then
        verify(peopleRepository)
                .add(personMatches(new Person(TEST_PERSON_NAME, TEST_PERSON_AGE)));
    }

    private void setViewModelPersonParams() {
        viewModel.setPersonName(TEST_PERSON_NAME);
        viewModel.setPersonAge(TEST_PERSON_AGE);
    }

    @Test
    public void shouldHideViewModelAfterSave() {
        // given
        setViewModelPersonParams();

        // when
        viewModel.onSavePerson();

        // then
        verify(navigator, times(1)).hide();
    }
}