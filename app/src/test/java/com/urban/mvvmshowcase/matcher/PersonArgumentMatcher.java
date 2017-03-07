package com.urban.mvvmshowcase.matcher;

import com.urban.mvvmshowcase.model.entity.Person;

import org.mockito.ArgumentMatcher;

import java.util.Objects;

import static org.mockito.ArgumentMatchers.argThat;

public class PersonArgumentMatcher implements ArgumentMatcher<Person> {
    private final Person mExpected;

    public static Person personMatches(Person person) {
        return argThat(new PersonArgumentMatcher(person));
    }

    private PersonArgumentMatcher(Person expected) {
        mExpected = expected;
    }

    @Override
    public boolean matches(Person argument) {
        if (mExpected != null && argument != null) {
            if (mExpected.getAge() != argument.getAge() ||
                    !Objects.equals(mExpected.getName(), argument.getName()))
                return false;
        }
        return true;
    }
}
