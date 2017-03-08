package com.urban.mvvmshowcase.model.entity;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor(suppressConstructorProperties = true)
public class Person {
    String name;
    int age;
}
