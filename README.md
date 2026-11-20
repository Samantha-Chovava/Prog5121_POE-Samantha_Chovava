# PROG5121 POE – Part 1, Part 2 and Part 3

**Student Name:** Samantha Chovava  
**Student Number:** ST10505448  
**Module:** PROG5121  

## About the Project
This project is a Java chat application developed as part of my PROG5121 Programming course. It includes user registration and login, messaging features, message validation, message hashes, stored message management using JSON, and automated JUnit 5 unit tests.

## Part 1 – Registration and Login
The application allows a user to:
* Enter their first name and surname.
* Register a username and password.
* Enter a cellphone number.
* Validate that the username contains an underscore and is no more than five characters long.
* Validate that the password has at least eight characters, a capital letter, a number, and a special character.
* Validate the cellphone number (`+27` international format).
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
* Save stored messages in a JSON file (`messages.json`).
* Display message details and keep track of the total number of messages sent.

## Part 3 – Stored Messages
Part 3 extends the messaging functionality by adding a fourth main menu option (**Stored Messages**) with full array and JSON management features:
* Populate collections dynamically for Sent Messages, Disregarded Messages, Stored Messages (loaded from JSON using Gson), Message Hashes, and Message IDs.
* **a)** Display the sender and recipient of all stored messages.
* **b)** Display the longest stored message.
* **c)** Search for a message ID and display the corresponding recipient and message.
* **d)** Search for all messages stored or sent for a particular recipient.
* **e)** Delete a message using the message hash.
* **f)** Display a report that lists the full details of all stored messages.
* **g)** Return to the main messaging menu.

## Unit Testing
JUnit 5 is used to automate testing across Parts 1, 2, and 3 (`TestUnits.java`):
* Message ID generation and length validation.
* Recipient cellphone number validation.
* Message length validation (250-character limit).
* Message hash formatting (`XX:N:FIRSTLAST`).
* Sending, disregarding, and storing message statuses.
* Populating the Sent Messages array with test data.
* Finding and displaying the longest message.
* Searching for a message by Message ID (`0838884567`).
* Searching all sent or stored messages for a specific recipient (`+27838884567`).
* Deleting a message using its message hash.
* Generating the Sent Messages report (Message Hash, Recipient, and Message).

## How to Run the Application
1. Open the project in NetBeans.
2. Run the `Registration` class (`Shift + F6`).
3. Register and log in using your details.
4. Follow the main menu options (`1` to `4`) and select **4) Stored Messages** to manage stored messages (`a` to `g`).
5. Run `TestUnits.java` (`Ctrl + F6`) to execute all automated JUnit 5 tests.

## Main Files
* `Registration.java` – Main program entry point and user menus.
* `Login.java` – Registration, validation, and login methods.
* `Message.java` – Message validation, ID generation, hash creation, and message details.
* `MessageManagement.java` – Handles stored/sent/disregarded arrays, Gson JSON storage, searching, deletion, and reporting.
* `TestUnits.java` – Automated JUnit 5 unit tests for Parts 2 and 3.
* `messages.json` – Stores persisted messages saved by the application.
* `pom.xml` – Maven project configuration and Gson/JUnit dependencies.
* `.github/workflows/maven-tests.yml` – Automated builds and tests via GitHub Actions.
