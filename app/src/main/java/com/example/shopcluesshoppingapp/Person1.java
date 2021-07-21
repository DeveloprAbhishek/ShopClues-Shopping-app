package com.example.shopcluesshoppingapp;

public class Person1 {
        int roll;
        String name;
        String address;

        public Person1(int roll, String name, String address) {
            this.roll = roll;
            this.name = name;
            this.address = address;
        }

    public int getRoll() {
        return roll;
    }

    public void setRoll(int roll) {
        this.roll = roll;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
