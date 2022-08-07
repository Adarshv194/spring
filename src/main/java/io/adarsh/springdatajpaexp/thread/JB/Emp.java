package io.adarsh.springdatajpaexp.thread.JB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Emp {
    private String name;
    private int age;
    private String gender;
    private double salary;

    public Emp(String name, int age, String gender, double salary) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public int hashCode() {
        return 1;
    }

    @Override
    public boolean equals(Object obj) {
        return true;
    }

    public static void main(String[] args) {
        List<Emp> empList = Arrays.asList(new Emp("Adarsh", 24, null, 400000));
        // female salary less than 30

        empList.stream().collect(Collectors.toMap((emp -> emp), emp -> emp.getSalary()));

        List<String> femaleList = empList.stream().filter(emp -> (emp.getSalary() < 30) && "Female".equals(emp.gender))
                .map(emp -> emp.getName())
                .collect(Collectors.toList());

        System.out.println(femaleList);
    }
}
