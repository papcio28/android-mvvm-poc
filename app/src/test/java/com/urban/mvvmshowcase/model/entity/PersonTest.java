package com.urban.mvvmshowcase.model.entity;

import com.urban.mvvmshowcase.person.Person;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class PersonTest {
    private static final String TEST_NAME = "Test Person";
    private static final int TEST_AGE = 27;

    @Test
    public void shouldHaveDefaultAgeSet() {
        assertEquals(new Person().getAge(), 18);
    }

    @Test
    public void shouldContainAgeAndNameInStringDescription() {
        Person person = new Person(TEST_NAME, TEST_AGE);

        assertTrue(person.toString().contains(TEST_NAME));
        assertTrue(person.toString().contains(Integer.toString(TEST_AGE)));
    }

    @Test
    public void shouldCreateNewInstanceWhileChangingName() {
        Person person = new Person();
        Person personWithNewName = person.withName(TEST_NAME);

        assertFalse(person == personWithNewName);
        assertNotEquals(person.getName(), TEST_NAME);
        assertEquals(personWithNewName.getName(), TEST_NAME);
    }

    @Test
    public void shouldCreateNewInstanceWhileChangingAge() {
        Person person = new Person();
        Person personWithNewAge = person.withAge(TEST_AGE);

        assertFalse(person == personWithNewAge);
        assertNotEquals(person.getAge(), TEST_AGE);
        assertEquals(personWithNewAge.getAge(), TEST_AGE);
    }
}