package edu.uncc.assessment02.models;

import java.io.Serializable;

public class User implements Serializable {
    State state;
    String name;
    int age,  creditScore;

    public User() {
    }

    public User(State state, String name, int age, int creditScore) {
        this.state = state;
        this.name = name;
        this.age = age;
        this.creditScore = creditScore;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
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

    public int getCreditScore() {
        return creditScore;
    }

    public void setCreditScore(int creditScore) {
        this.creditScore = creditScore;
    }
}
