package com.dvgiang.electricitybillingsystem.model;

public class Customer {
    private int id;
    private String name;
    private Address address;
    private String phone;

    public Customer() {}

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address.street + " " + address.suite + " " + address.city;
    }

    public void setAddress(String street, String suite, String city) {
        this.address.street = street;
        this.address.suite = suite;
        this.address.city = city;
    }
}

class Address {
    public String street;
    public String suite;
    public String city;
}
