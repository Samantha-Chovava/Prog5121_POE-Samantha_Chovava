/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.st10505448chatapplication;

import java.util.Scanner;

public class Registration {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

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
        Login login = new Login(
                firstName,
                lastName,
                userName,
                password,
                cellPhoneNumber
        );

        // Register the user
        System.out.println();
        String registrationResult = login.registerUser();
        System.out.println(registrationResult);

        if (!registrationResult.equals("User registered successfully.")) {
            System.out.println("Please restart the application and register again.");
            return;
        }

        // Ask for login details
        System.out.println();
        System.out.println("Please enter your login details.");

        System.out.print("Enter username: ");
        loginUserName = input.nextLine();

        System.out.print("Enter password: ");
        loginPassword = input.nextLine();

        login.setLoginDetails(loginUserName, loginPassword);

        // Check login details
        if (login.loginUser()) {

            System.out.println("Login successful.");
            System.out.println("Welcome " + firstName + ", "
                    + lastName + " it's great to see you again.");

            runQuickChat(input);

        } else {
            System.out.println(
                    "Username or password incorrect, please try again."
            );
        }
    }

    public static void runQuickChat(Scanner input) {

        System.out.println();
        System.out.println("Welcome to QuickChat.");

        int messageLimit;

        // Ask the user how many messages they want to create
        while (true) {
            System.out.print("How many messages would you like to send? ");

            String numberInput = input.nextLine();

            try {
                messageLimit = Integer.parseInt(numberInput);

                if (messageLimit > 0) {
                    break;
                }

                System.out.println("Please enter a number greater than zero.");

            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid whole number.");
            }
        }

        int messagesCreated = 0;
        boolean totalDisplayed = false;

        // Main messaging menu
        while (true) {

            System.out.println();
            System.out.println("Please select an option:");
            System.out.println("1) Send Messages");
            System.out.println("2) Show recently sent messages");
            System.out.println("3) Quit");

            String choice = input.nextLine();

            switch (choice) {

                case "1":

                    if (messagesCreated >= messageLimit) {
                        System.out.println(
                                "You have reached your message limit."
                        );
                        break;
                    }

                    // Create the remaining messages
                    while (messagesCreated < messageLimit) {

                        String recipient;

                        // Validate the recipient number
                        while (true) {

                            System.out.print(
                                    "Enter recipient cellphone number "
                                    + "with international code: "
                            );

                            recipient = input.nextLine();

                            if (recipient.matches("^\\+[1-9]\\d{7,10}$")) {
                                System.out.println(
                                        "Cell phone number successfully captured."
                                );
                                break;
                            }

                            System.out.println(
                                    "Cell phone number is incorrectly formatted "
                                    + "or does not contain an international code. "
                                    + "Please correct the number and try again."
                            );
                        }

                        String messageText;

                        // Validate the message length
                        while (true) {

                            System.out.print("Enter your message: ");
                            messageText = input.nextLine();

                            if (messageText.length() <= 250) {
                                System.out.println("Message ready to send.");
                                break;
                            }

                            int excess = messageText.length() - 250;

                            System.out.println(
                                    "Message exceeds 250 characters by "
                                    + excess
                                    + "; please reduce the size."
                            );
                        }

                        // Create the message
                        Message message = new Message(
                                recipient,
                                messageText,
                                messagesCreated
                        );

                        System.out.println(
                                "Message ID generated: "
                                + message.getMessageID()
                        );

                        System.out.println(
                                "Message Hash: " + message.getMessageHash()
                        );

                        // Choose what to do with the message
                        System.out.println();
                        System.out.println("Choose an option:");
                        System.out.println("1. Send Message");
                        System.out.println("2. Disregard Message");
                        System.out.println("3. Store Message to send later");

                        String messageChoice = input.nextLine();

                        System.out.println(
                                message.SentMessage(messageChoice)
                        );

                        // Display the message details
                        System.out.println();
                        System.out.println(message.printMessages());

                        messagesCreated++;

                        // Display total sent messages after all are processed
                        if (messagesCreated == messageLimit) {
                            System.out.println();
                            System.out.println(
                                    "Total messages sent: "
                                    + Message.returnTotalMessagess()
                            );

                            totalDisplayed = true;
                        }
                    }

                    break;

                case "2":
                    System.out.println("Coming Soon.");
                    break;

                case "3":
                    if (!totalDisplayed) {
                        System.out.println(
                                "Total messages sent: "
                                + Message.returnTotalMessagess()
                        );
                    }

                    System.out.println("Thank you for using QuickChat.");
                    return;

                default:
                    System.out.println(
                            "Invalid option. Please select 1, 2, or 3."
                    );
            }
        }
    }
}