# PROG5121 POE – Part 1 and Part 2

**Student Name:** Samantha Chovava
**Student Number:** ST10505448
**Module:** PROG5121

## About the Project

This project is a Java chat application developed as part of my PROG5121 Programming course. Part 1 focuses on user registration and login, while Part 2 adds messaging features, message validation, message hashes, and JSON storage.

## Part 1 – Registration and Login

The application allows a user to:

* Enter their first name and surname.
* Register a username and password.
* Enter a cellphone number.
* Check that the username contains an underscore and is no more than five characters long.
* Check that the password has at least eight characters, a capital letter, a number, and a special character.
* Validate the cellphone number using the South African international code.
* Log in using the registered username and password.
* Receive a message showing whether the login was successful or unsuccessful.

## Part 2 – Messaging

The messaging feature allows a user to:

* Choose the number of messages they want to send.
* Create messages for different recipients.
* Generate a unique 10-digit message ID.
* Validate recipient cellphone numbers.
* Check that messages do not exceed 250 characters.
* Generate a message hash using the message ID, message number, and the first and last words of the message.
* Send, disregard, or store messages.
* Save stored messages in a JSON file.
* Display the message ID, message hash, recipient, and message.
* Keep track of the total number of messages sent.
* Select the option to view recently sent messages, which currently displays "Coming Soon."

## Unit Testing

JUnit 5 is used to test the application.

The tests cover features such as:

* Username and password validation.
* Cellphone number validation.
* Registration and login.
* Message ID validation.
* Message length validation.
* Recipient cellphone number validation.
* Message hash creation.
* Message sending and storage.

The automated tests have passed successfully. GitHub Actions is also configured to build the project and run the tests when changes are pushed to the Part 2 branch.

## How to Run the Application

1. Open the project in NetBeans.
2. Run the `Registration` class.
3. Enter the registration details when prompted.
4. Log in using the registered details.
5. Follow the messaging menu options displayed by the application.

## Main Files

* `Registration.java` – Contains the main program and user interaction.
* `Login.java` – Contains registration, validation, and login methods.
* `Message.java` – Contains message validation, ID generation, hash creation, and message storage.
* `TestUnits.java` – Contains the JUnit 5 unit tests.
* `messages.json` – Stores messages saved by the application during testing.
* `pom.xml` – Contains the Maven project configuration and dependencies.
* `.github/workflows/maven-tests.yml` – Configures automated builds and tests on GitHub Actions.

## Project Status

Part 1 registration, validation, login functionality, and unit testing have been completed. Part 2 adds messaging functionality, message validation, message hashes, JSON storage, and automated testing.
