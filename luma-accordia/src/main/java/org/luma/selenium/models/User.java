package org.luma.selenium.models;

import com.github.javafaker.Faker;

public class User {
    private static final Faker faker = new Faker();

    public static String generateRandomEmail() {
        return faker.internet().emailAddress();
    }

    public static String getPassword() {
        return "download123@!"; // current solution to pass form validator each time, probably faker inbuilt methods supports paramization
    }

    public static String generateRandomFirstName() {
        return faker.name().firstName();
    }

    public static String generateRandomLastName() {
        return faker.name().lastName();
    }
}