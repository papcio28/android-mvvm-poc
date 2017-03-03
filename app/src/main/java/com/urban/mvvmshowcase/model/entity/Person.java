package com.urban.mvvmshowcase.model.entity;

import com.urban.mvvmshowcase.model.repository.Skill;

import java.util.LinkedList;
import java.util.List;

import lombok.AccessLevel;
import lombok.Value;
import lombok.experimental.NonFinal;
import lombok.experimental.Wither;

@Value
public class Person {
    String name;
    int age;
    @Wither(AccessLevel.PUBLIC)
    @NonFinal
    List<Skill> skills = new LinkedList<>();
}
