package com.urban.mvvmshowcase.viewmodel;

import com.urban.mvvmshowcase.model.entity.Person;
import com.urban.mvvmshowcase.model.repository.PersonRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;

import io.reactivex.subjects.ReplaySubject;
import io.reactivex.subjects.Subject;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PersonListViewModelTest {
    @Mock
    private PersonRepository peopleRepository;
    private PersonListViewModel viewModel;

    @Before
    public void setUp() {
        viewModel = new PersonListViewModel(peopleRepository);
    }

    @Test
    public void shouldLoadOnFirstShow() {
        assertTrue(viewModel.isLoading());
    }

    @Test
    public void shouldNotLoadAfterDataLoaded() {
        // given
        Subject<List<Person>> testSubject = configureRepositoryWithTestSubject();

        // when
        viewModel.onShow();
        testSubject.onNext(Collections.<Person>emptyList());

        // then
        assertFalse(viewModel.isLoading());
    }

    @Test
    public void shouldNotifyObserverAboutListChange() {
        // given
        Subject<List<Person>> testSubject = configureRepositoryWithTestSubject();
        PersonListViewModel.PeopleListObserver mockObserver = mock(PersonListViewModel.PeopleListObserver.class);
        viewModel.setListObserver(mockObserver);

        // when
        viewModel.onShow();
        testSubject.onNext(Collections.<Person>emptyList());

        // then
        verify(mockObserver, times(1)).onPeopleListChanged(ArgumentMatchers.<Person>anyList());
    }

    @Test
    public void shouldReceiveLatestListWhenReused() {
        // given
        Subject<List<Person>> testSubject = configureRepositoryWithTestSubject();
        PersonListViewModel.PeopleListObserver mockObserver = mock(PersonListViewModel.PeopleListObserver.class);
        viewModel.setListObserver(mockObserver);

        // when : first usage
        viewModel.onShow();
        testSubject.onNext(Collections.<Person>emptyList());
        viewModel.onHide();
        viewModel.setListObserver(null);

        // when : second usage
        viewModel.setListObserver(mockObserver);
        viewModel.onShow();

        // then
        verify(mockObserver, times(2)).onPeopleListChanged(ArgumentMatchers.<Person>anyList());
    }

    private Subject<List<Person>> configureRepositoryWithTestSubject() {
        Subject<List<Person>> testSubject = ReplaySubject.createWithSize(1);
        when(peopleRepository.peopleList()).thenReturn(testSubject);

        return testSubject;
    }
}