package com.urban.mvvmshowcase.matcher;

import com.urban.mvvmshowcase.model.entity.Person;

import org.mockito.ArgumentMatcher;

import java.util.Objects;

import static org.mockito.ArgumentMatchers.argThat;

public class PersonArgumentMatcher implements ArgumentMatcher<Person> {
    private final Person expected;

    public static Person personMatches(Person person) {
        return argThat(new PersonArgumentMatcher(person));
    }

    private PersonArgumentMatcher(Person expected) {
        this.expected = expected;
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
