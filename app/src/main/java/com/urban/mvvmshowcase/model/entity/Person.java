package com.urban.mvvmshowcase.model.entity;

import java.util.Locale;
import java.util.UUID;

public final class Person {
    public static final int DEFAULT_AGE = 18;

    private final UUID id;
    private final String name;
    private final int age;

    public Person() {
        this("", DEFAULT_AGE);
    }

    public Person(String name, int age) {
        this(UUID.randomUUID(), name, age);
    }

    private Person(UUID id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Person withAge(int age) {
        return new Person(id, getName(), age);
    }

    public Person withName(String name) {
        return new Person(id, name, getAge());
    }

    @Override
    public String toString() {
        return String.format(Locale.getDefault(), "%s [%d]", getName(), getAge());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        return id.equals(person.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
