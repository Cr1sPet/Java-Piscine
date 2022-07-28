package edu.school21.reflection.classes;

import java.util.StringJoiner;

public class User {
    private String firstName;
    private String lastName;
    private Integer height;
    public User() {
        this.firstName = "Default first name";
        this.lastName = "Default last name";
        this.height = 0;
    }
    public User(String firstName, String lastName, int height) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.height = height;
    }
    public int grow(Integer value) {
        this.height += value;
        return height;
    }
    public Integer print() {
        System.out.println("HELLO");
        return 100;
    }
    public void print(String a) {
        System.out.println("HELLO " + a);
    }
    public void print(String a, Integer b, Boolean c, Double d, Long e) {
        System.out.printf("HELLO %s, %d, %b, %f, %dPOPPO", a, b, c, d, e);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Car.class.getSimpleName() + "[", "]")
                .add("firstName='" + firstName + "'")
                .add("lastName='" + lastName + "'")
                .add("height=" + height)
                .toString() ;
    }
}