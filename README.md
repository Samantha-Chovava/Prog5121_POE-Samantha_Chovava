# PROG5121 POE – Part 1

**Student Name:** Samantha Chovava
**Student Number:** ST10505448
**Module:** PROG5121
**Part:** Part 1 – Registration and Login Feature

## About the Project

This project is a Java chat application developed as part of my PROG5121 Programming course. Part 1 focuses on creating the registration and login feature for the chat application. 

## Part 1 Features

The application allows a user to:

* Enter their first name and surname.
* Register a username.
* Register a password.
* Enter a cellphone number.
* Check that the username contains an underscore and is no more than five characters long.
* Check that the password has at least eight characters, a capital letter, a number, and a special character.
* Check that the cellphone number is correctly formatted using the South African international code.
* Log in using the registered username and password.
* Receive a message showing whether the login was successful or unsuccessful.

## Unit Testing

JUnit 5 was used to test the Part 1 features.

The tests check:

* Username validation
* Password complexity
* Cellphone number validation
* User registration
* Successful login
* Unsuccessful login

There are **6 unit tests**, and all 6 tests passed successfully.

## How to Run the Application

1. Open the project in NetBeans.
2. Run the `Registration` class.
3. Enter the registration details when prompted.
4. Enter the login details when prompted.
5. The application will display whether the login was successful.

## Files

* `Registration.java` – Contains the main program and user interaction.
* `Login.java` – Contains the registration, validation, and login methods.
* `TestUnits.java` – Contains the JUnit 5 unit tests.

## Part 1 Status

Part 1 registration, validation, login functionality, and unit testing have been completed.
