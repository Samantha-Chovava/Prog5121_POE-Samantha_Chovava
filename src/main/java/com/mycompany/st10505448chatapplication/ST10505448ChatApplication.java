/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.st10505448chatapplication;

import java.util.Scanner;

/**
 *
 * @author User
 */
public class ST10505448ChatApplication {

    public static void main(String[] args) {
        // Scanner
        Scanner input = new Scanner(System.in);

        // Declarations
        String firstName;
        String lastName;
        String userName;
        String password;
        String cellPhoneNumber;
        String loginUserName;
        String loginPassword;

        // Ask for registration details
        System.out.print("Enter your first name: ");
        firstName = input.nextLine();

        System.out.print("Enter your surname: ");
        lastName = input.nextLine();

        System.out.print("Enter username: ");
        userName = input.nextLine();

        System.out.print("Enter your password: ");
        password = input.nextLine();

        System.out.print("Enter your cellphone number: ");
        cellPhoneNumber = input.nextLine();

        // Create the registered user
        Login login = new Login(firstName, lastName, userName, password, cellPhoneNumber);

        // Register the user
        System.out.println();
        System.out.println(login.registerUser());

        // Ask for login details
        System.out.println();
        System.out.println("Please enter your login details.");

        System.out.print("Enter username: ");
        loginUserName = input.nextLine();

        System.out.print("Enter password: ");
        loginPassword = input.nextLine();

        // Store the login details entered
        login.setLoginDetails(loginUserName, loginPassword);

        // Check login details
        if (login.loginUser()) {
            System.out.println("Login successful.");
            System.out.println("Welcome " + firstName + ", " + lastName + " it's great to see you again.");
        } else {
            System.out.println("Username or password incorrect, please try again.");
        }
    }
}