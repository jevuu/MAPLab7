package com.example.d8.maplab7;

public class Employee {
    String name;
    String dept;
    String year;

    public Employee(String n, String d, String y){
        name = n;
        dept = d;
        year = y;
    }

    public String getName(){
        return name;
    }

    public String getDept(){
        return dept;
    }

    public String getYear(){
        return year;
    }
}
