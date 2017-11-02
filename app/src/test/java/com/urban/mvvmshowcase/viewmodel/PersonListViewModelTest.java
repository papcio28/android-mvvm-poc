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
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PersonListViewModelTest {
    private static final Person TEST_PERSON = new Person("Test Person", 27);

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
        assertTrue(viewModel.getLoading().get());
    }

    @Test
    public void shouldNotLoadAfterDataLoaded() {
        // given
        Subject<List<Person>> testSubject = configureRepositoryWithTestSubject();

        // when
        testSubject.onNext(Collections.<Person>emptyList());

        // then
        assertFalse(viewModel.getLoading().get());
    }

    private Subject<List<Person>> configureRepositoryWithTestSubject() {
        Subject<List<Person>> testSubject = ReplaySubject.createWithSize(1);
        when(peopleRepository.peopleList()).thenReturn(testSubject);

        return testSubject;
    }
}