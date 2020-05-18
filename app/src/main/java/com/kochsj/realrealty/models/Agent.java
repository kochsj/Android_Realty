package com.kochsj.realrealty.models;

public class Agent {
    public String firstName;
    public String lastName;
    public String phoneNumber;
    public String email;
    public String company;

    public Agent(String firstName, String lastName, String phoneNumber, String email, String company){
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.company = company;
    }
}
