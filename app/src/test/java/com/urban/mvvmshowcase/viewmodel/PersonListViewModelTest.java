package com.urban.mvvmshowcase.viewmodel;

import com.urban.mvvmshowcase.model.entity.Person;
import com.urban.mvvmshowcase.model.repository.PersonRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;

import io.reactivex.subjects.PublishSubject;
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
    private PersonRepository mPersonRepository;
    private PersonListViewModel mViewModel;

    @Before
    public void setUp() {
        mViewModel = new PersonListViewModel(mPersonRepository);
    }

    @Test
    public void shouldLoadOnFirstShow() {
        assertTrue(mViewModel.isLoading());
    }

    @Test
    public void shouldNotLoadAfterDataLoaded() {
        // given
        Subject<List<Person>> testSubject = configureRepositoryWithTestSubject();

        // when
        mViewModel.onShow();
        testSubject.onNext(Collections.emptyList());

        // then
        assertFalse(mViewModel.isLoading());
    }

    @Test
    public void shouldNotifyObserverAboutListChange() {
        // given
        Subject<List<Person>> testSubject = configureRepositoryWithTestSubject();
        PersonListViewModel.PeopleListObserver mockObserver = mock(PersonListViewModel.PeopleListObserver.class);
        mViewModel.setListObserver(mockObserver);

        // when
        mViewModel.onShow();
        testSubject.onNext(Collections.emptyList());

        // then
        verify(mockObserver, times(1)).onPeopleListChanged();
    }

    @Test
    public void shouldReceiveLatesListWhenReused() {
        // given
        Subject<List<Person>> testSubject = configureRepositoryWithTestSubject();
        PersonListViewModel.PeopleListObserver mockObserver = mock(PersonListViewModel.PeopleListObserver.class);
        mViewModel.setListObserver(mockObserver);

        // when : first usage
        mViewModel.onShow();
        testSubject.onNext(Collections.emptyList());
        mViewModel.onHide();
        mViewModel.setListObserver(null);

        // when : second usage
        mViewModel.setListObserver(mockObserver);
        mViewModel.onShow();

        // then
        verify(mockObserver, times(2)).onPeopleListChanged();
    }

    private Subject<List<Person>> configureRepositoryWithTestSubject() {
        Subject<List<Person>> testSubject = ReplaySubject.createWithSize(1);
        when(mPersonRepository.peopleList()).thenReturn(testSubject);

        return testSubject;
    }
}