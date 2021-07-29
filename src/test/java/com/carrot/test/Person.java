package com.carrot.test;

import java.util.Objects;

public class Person {

    private int age; // 나이
    private String name; // 이름

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return age == person.age && Objects.equals(name, person.name);
    }

//    @Override
//    public int hashCode() {
//        return Objects.hash(age, name);
//    }
    @Override
    public int hashCode() {
        return 1089;
    }

}
