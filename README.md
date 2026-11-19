# PROG5121 POE – Part 1, Part 2 and Part 3

**Student Name:** Samantha Chovava  
**Student Number:** ST10505448  
**Module:** PROG5121

## About the Project

This project is a Java chat application developed as part of my PROG5121 Programming course. It includes user registration and login, messaging features, message validation, message hashes, and stored message management using JSON.

## Part 1 – Registration and Login

The application allows a user to:

- Enter their first name and surname.
- Register a username and password.
- Enter a cellphone number.
- Validate that the username contains an underscore and is no more than five characters long.
- Validate that the password has at least eight characters, a capital letter, a number, and a special character.
- Validate the cellphone number.
- Log in using the registered username and password.
- Receive a message showing whether the login was successful or unsuccessful.

## Part 2 – Messaging

The messaging feature allows a user to:

- Choose the number of messages they want to send.
- Create messages for different recipients.
- Generate a unique 10-digit message ID.
- Validate recipient cellphone numbers.
- Check that messages do not exceed 250 characters.
- Generate a message hash using the message ID, message number, and the first and last words of the message.
- Send, disregard, or store messages.
- Save stored messages in a JSON file.
- Display message details.
- Keep track of the total number of messages sent.

## Part 3 – Stored Messages

Part 3 extends the messaging functionality by adding message management features.

The application allows users to:

- Store messages in a JSON file using Gson.
- Load previously stored messages when the application starts.
- Display all stored messages.
- Find and display the longest stored message.
- Search for a stored message using its message ID.
- Search for messages using a recipient's cellphone number.
- Delete a stored message using its message hash.
- Generate a report containing stored message details.
- Return to the main messaging menu after managing stored messages.

The `MessageManagement.java` class handles the storage, searching, deletion, and reporting of messages.

## Unit Testing

JUnit 5 is used to test the application.

The tests cover features such as:

- Username and password validation.
- Cellphone number validation.
- Registration and login.
- Message ID validation.
- Message length validation.
- Recipient cellphone number validation.
- Message hash creation.
- Message sending and storage.

Automated testing is also supported through GitHub Actions.

## How to Run the Application

1. Open the project in NetBeans.
2. Run the `Registration` class.
3. Enter the registration details when prompted.
4. Log in using the registered details.
5. Follow the messaging menu options displayed by the application.
6. Select the Stored Messages option to access message management features.

## Main Files

- `Registration.java` – Contains the main program and user interaction.
- `Login.java` – Contains registration, validation, and login methods.
- `Message.java` – Contains message validation, ID generation, hash creation, and message details.
- `MessageManagement.java` – Handles stored messages, JSON storage, searching, deletion, and reporting.
- `TestUnits.java` – Contains the JUnit 5 unit tests.
- `messages.json` – Stores messages saved by the application.
- `pom.xml` – Contains the Maven project configuration and dependencies.
- `.github/workflows/maven-tests.yml` – Configures automated builds and tests through GitHub Actions.

## Project Status

Part 1 covers registration, validation, and login functionality. Part 2 adds messaging features, message validation, and message hashes. Part 3 extends the application with stored message management, JSON file handling, searching, deletion, and message reporting.