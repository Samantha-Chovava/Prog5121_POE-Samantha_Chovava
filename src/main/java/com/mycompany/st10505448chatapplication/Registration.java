package com.mycompany.st10505448chatapplication;

import java.util.Scanner;
import java.util.ArrayList;

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

        Login login = new Login(
                firstName,
                lastName,
                userName,
                password,
                cellPhoneNumber
        );

        System.out.println();
        String registrationResult = login.registerUser();
        System.out.println(registrationResult);

        if (!registrationResult.equals("User registered successfully.")) {
            System.out.println("Please restart the application and register again.");
            return;
        }

        System.out.println();
        System.out.println("Please enter your login details.");

        System.out.print("Enter username: ");
        loginUserName = input.nextLine();

        System.out.print("Enter password: ");
        loginPassword = input.nextLine();

        login.setLoginDetails(loginUserName, loginPassword);

        if (login.loginUser()) {
            System.out.println("Login successful.");
            System.out.println("Welcome " + firstName + ", "
                    + lastName + " it's great to see you again.");

            runQuickChat(input, firstName + " " + lastName);
        } else {
            System.out.println(
                    "Username or password incorrect, please try again."
            );
        }
    }

    public static void runQuickChat(Scanner input, String senderName) {

        System.out.println();
        System.out.println("Welcome to QuickChat.");
        MessageManagement messageManagement = new MessageManagement();

        int messageLimit;

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

        while (true) {
            System.out.println();
            System.out.println("Please select an option:");
            System.out.println("1) Send Messages");
            System.out.println("2) Show recently sent messages");
            System.out.println("3) Quit");
            System.out.println("4) Stored Messages");

            String choice = input.nextLine();

            switch (choice) {
                case "1":
                    if (messagesCreated >= messageLimit) {
                        System.out.println("You have reached your message limit.");
                        break;
                    }

                    while (messagesCreated < messageLimit) {
                        String recipient;

                        while (true) {
                            System.out.print(
                                    "Enter recipient cellphone number with international code: "
                            );
                            recipient = input.nextLine();

                            if (recipient.matches("^\\+[1-9]\\d{7,10}$")) {
                                System.out.println("Cell phone number successfully captured.");
                                break;
                            }

                            System.out.println(
                                    "Cell phone number is incorrectly formatted "
                                    + "or does not contain an international code. "
                                    + "Please correct the number and try again."
                            );
                        }

                        String messageText;

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

                        Message message = new Message(
                                senderName,
                                recipient,
                                messageText,
                                messagesCreated
                        );

                        System.out.println("Message ID generated: " + message.getMessageID());
                        System.out.println("Message Hash: " + message.getMessageHash());

                        System.out.println();
                        System.out.println("Choose an option:");
                        System.out.println("1. Send Message");
                        System.out.println("2. Disregard Message");
                        System.out.println("3. Store Message to send later");

                        String messageChoice = input.nextLine();
                        System.out.println(message.SentMessage(messageChoice));

                        messageManagement.addMessage(message);

                        System.out.println();
                        System.out.println(message.printMessages());

                        messagesCreated++;

                        if (messagesCreated == messageLimit) {
                            System.out.println();
                            System.out.println(
                                    "Total messages sent: " + Message.returnTotalMessagess()
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
                                "Total messages sent: " + Message.returnTotalMessagess()
                        );
                    }
                    System.out.println("Thank you for using QuickChat.");
                    return;

                case "4":
                    boolean returnToMainMenu = false;

                    while (!returnToMainMenu) {
                        System.out.println("\n--- STORED MESSAGES ---");
                        System.out.println("a) Display sender and recipient of all stored messages");
                        System.out.println("b) Display the longest stored message");
                        System.out.println("c) Search for a message ID");
                        System.out.println("d) Search all messages for a particular recipient");
                        System.out.println("e) Delete a message using the message hash");
                        System.out.println("f) Display full stored messages report");
                        System.out.println("g) Return to main menu");

                        String storedChoice = input.nextLine().toLowerCase();

                        switch (storedChoice) {
                            case "a":
                                System.out.println(
                                        messageManagement.displayStoredSendersAndRecipients()
                                );
                                break;

                            case "b":
                                Message longest = messageManagement.getLongestMessage();
                                if (longest != null) {
                                    System.out.println(longest.printMessages());
                                } else {
                                    System.out.println("No messages found.");
                                }
                                break;

                            case "c":
                                System.out.print("Enter message ID: ");
                                String searchID = input.nextLine();
                                Message found = messageManagement.searchMessageByID(searchID);

                                if (found != null) {
                                    System.out.println("Recipient: " + found.getRecipient());
                                    System.out.println("Message: " + found.getMessageText());
                                } else {
                                    System.out.println("Message not found.");
                                }
                                break;

                                                        case "d":
                                System.out.print("Enter recipient cellphone number: ");
                                String searchRecipient = input.nextLine();
                                ArrayList<Message> results = messageManagement.searchMessagesByRecipient(searchRecipient);

                                if (results.isEmpty()) {
                                    System.out.println("No messages found for this recipient.");
                                } else {
                                    for (Message message : results) {
                                        System.out.println(message.printMessages());
                                        System.out.println();
                                    }
                                }
                                break;

                            case "e":
                                System.out.print("Enter message hash: ");
                                String deleteHash = input.nextLine();
                                System.out.println(
                                        messageManagement.deleteMessageByHash(deleteHash)
                                );
                                break;

                            case "f":
                                System.out.println(
                                        messageManagement.generateStoredReport()
                                );
                                break;

                            case "g":
                                returnToMainMenu = true;
                                break;

                            default:
                                System.out.println("Invalid option. Please try again.");
                        }
                    }
                    break;

                default:
                    System.out.println("Invalid option. Please select 1, 2, 3, or 4.");
            }
        }
    }
}
