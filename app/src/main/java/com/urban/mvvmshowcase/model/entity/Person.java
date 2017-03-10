package com.urban.mvvmshowcase.model.entity;

import java.util.Locale;

public final class Person {
    public static final int DEFAULT_AGE = 18;

    private final String name;
    private final int age;

    public Person() {
        this.name = "";
        this.age = DEFAULT_AGE;
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Person withAge(int age) {
        return new Person(getName(), age);
    }

    public Person withName(String name) {
        return new Person(name, getAge());
    }

    @Override
    public String toString() {
        return String.format(Locale.getDefault(), "%s [%d]", getName(), getAge());
    }
}
