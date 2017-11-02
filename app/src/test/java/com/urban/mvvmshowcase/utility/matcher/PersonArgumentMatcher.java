package com.urban.mvvmshowcase.utility.matcher;

import com.urban.mvvmshowcase.person.Person;

import org.mockito.ArgumentMatcher;

import java.util.Objects;

import static org.mockito.ArgumentMatchers.argThat;

public class PersonArgumentMatcher implements ArgumentMatcher<Person> {
    private final Person expected;

    private PersonArgumentMatcher(Person expected) {
        this.expected = expected;
    }

    public static Person personMatches(Person person) {
        return argThat(new PersonArgumentMatcher(person));
    }

    @Override
    public boolean matches(Person argument) {
        if (expected != null && argument != null) {
            if (expected.getAge() != argument.getAge() ||
                    !Objects.equals(expected.getName(), argument.getName()))
                return false;
        }
        return true;
    }
}
