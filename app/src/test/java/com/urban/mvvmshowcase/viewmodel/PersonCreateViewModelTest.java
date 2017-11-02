package com.urban.mvvmshowcase.viewmodel;

import com.urban.mvvmshowcase.person.Person;
import com.urban.mvvmshowcase.person.PersonRepository;
import com.urban.mvvmshowcase.person.create.PersonCreateViewAccess;
import com.urban.mvvmshowcase.person.create.PersonCreateViewModel;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static com.urban.mvvmshowcase.utility.matcher.PersonArgumentMatcher.personMatches;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class PersonCreateViewModelTest {
    private static final Person TEST_PERSON = new Person("Test Person", 27);

    @Mock
    private PersonRepository peopleRepository;
    @Mock
    private PersonCreateViewAccess viewAccess;
    private PersonCreateViewModel viewModel;

    @Before
    public void setUp() {
        viewModel = new PersonCreateViewModel(peopleRepository, viewAccess);
    }

    @Test
    public void shouldPersistPersonInRepository() {
        // given
        viewModel.setPerson(TEST_PERSON);

        // when
        viewModel.savePerson();

        // then
        verify(peopleRepository).save(personMatches(TEST_PERSON));
        verify(viewAccess, times(1)).hide();
    }
}