package com.example.travelassistant;

public class Volunteer {
    private String name;
    private String status;
    private int age;
    private String phone;

    public Volunteer(String name, String status, int age, String phone) {
        this.name = name;
        this.status = status;
        this.age = age;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public int getAge() {
        return age;
    }

    public String getPhone() {
        return phone;
    }
}