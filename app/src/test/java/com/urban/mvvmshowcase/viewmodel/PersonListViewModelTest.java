package com.urban.mvvmshowcase.viewmodel;

import com.urban.mvvmshowcase.person.Person;
import com.urban.mvvmshowcase.person.PersonRepository;
import com.urban.mvvmshowcase.person.list.PersonListViewAccess;
import com.urban.mvvmshowcase.person.list.PersonListViewModel;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;

import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.Subject;

import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PersonListViewModelTest {
    @Mock
    private PersonRepository peopleRepository;
    @Mock
    private PersonListViewAccess peopleViewAccess;

    private PersonListViewModel viewModel;

    @Before
    public void setUp() {
        viewModel = new PersonListViewModel(peopleRepository, peopleViewAccess);
    }

    @Test
    public void shouldLoadOnFirstShow() {
        assertFalse(viewModel.getLoading().get());
    }

    @Test
    public void shouldNotLoadAfterDataLoaded() {
        // given
        Subject<List<Person>> testSubject = BehaviorSubject.createDefault(
                Collections.<Person>emptyList());
        when(peopleRepository.peopleList()).thenReturn(testSubject);

        // when
        viewModel.loadPeople();

        // then
        verify(peopleRepository).peopleList();
        verify(peopleViewAccess).notifyDataChanged();
        assertFalse(viewModel.getLoading().get());
    }

}