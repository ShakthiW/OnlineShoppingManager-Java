package org.example;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class User {
    protected String userName;
    protected String password;

    // Constructor
    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    // validate password
    private static boolean validatePassword(String password) {
        // Using regular expressions to check for one capital letter and one numeric value
        String regex = "^(?=.*[A-Z])(?=.*\\d).+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);

        return matcher.matches();
    }

    // ------------ Getters starts ------------------
    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
    // ------------- Getters ends -------------------


    // ------------ Setters starts ------------------
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    // ------------- Setters ends -------------------


    // validate user credentials on login
    public boolean validateCredentials(String enteredUsername, String enteredPassword) {
        return userName.equals(enteredUsername) && password.equals(enteredPassword);
    }
}
