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
    private static final Person TEST_PERSON = new Person("Test Person", 27);

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
        verify(peopleRepository).add(personMatches(TEST_PERSON));
    }

    private void setViewModelPersonParams() {
        viewModel.setPerson(TEST_PERSON);
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