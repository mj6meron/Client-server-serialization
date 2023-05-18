package com.example.demo1;
import java.io.Serializable;

public class Person implements Serializable {
    private final String name;
    private final String address;
    private final int age;
    public Person(String name, String address, int age) {

        this.name = name;
        this.address = address;
        this.age = age;
    }
    public String getDetails() {
        return this.name + " is " + this.age + " years of age and lives in " + this.address + ".";
    }
}