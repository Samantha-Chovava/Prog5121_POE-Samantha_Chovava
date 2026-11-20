package com.mycompany.st10505448chatapplication;

import java.util.Random;

public class Message {

    private String messageID;
    private int messageNumber;
    private String sender;
    private String recipient;
    private String messageText;
    private String messageHash;
    private String messageStatus;

    private static int totalMessages = 0;
    private static int messagesSent = 0;

    public Message() {
    }

    public Message(String recipient, String messageText, int messageNumber) {
        this("Developer", recipient, messageText, messageNumber);
    }

    public Message(String sender, String recipient, String messageText, int messageNumber) {
        this.messageID = generateMessageID();
        this.sender = (sender == null || sender.isBlank()) ? "Developer" : sender;
        this.recipient = recipient;
        this.messageText = messageText;
        this.messageNumber = messageNumber;
        this.messageHash = createMessageHash();
        this.messageStatus = "Pending";
        totalMessages++;
    }

    private String generateMessageID() {
        Random random = new Random();
        StringBuilder id = new StringBuilder();

        for (int i = 0; i < 10; i++) {
            id.append(random.nextInt(10));
        }

        return id.toString();
    }

    public boolean checkMessageID() {
        return messageID != null && messageID.length() <= 10;
    }

    public String checkRecipientCell() {
        if (recipient != null && recipient.matches("^\\+[1-9]\\d{7,10}$")) {
            return "Cell phone number successfully captured.";
        }

        return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
    }
    
    public String checkMessageLength() {
        if (messageText == null) {
            return "Please enter a message.";
        }

        if (messageText.length() <= 250) {
            return "Message ready to send.";
        }

        int excess = messageText.length() - 250;

        return "Message exceeds 250 characters by " + excess
                + "; please reduce the size.";
    }

    public String createMessageHash() {
        if (messageText == null || messageText.trim().isEmpty()) {
            return messageID.substring(0, 2) + ":"
                    + messageNumber + ":";
        }

        String[] words = messageText.trim().split("\\s+");

        String firstWord = words[0].replaceAll("[^a-zA-Z0-9]", "");
        String lastWord = words[words.length - 1].replaceAll("[^a-zA-Z0-9]", "");

        return (messageID.substring(0, 2) + ":"
                + messageNumber + ":"
                + firstWord + lastWord).toUpperCase();
    }

    public String SentMessage(String choice) {
        switch (choice) {
            case "1":
                messageStatus = "Sent";
                messagesSent++;
                System.out.println("Message successfully sent.");
                return "Message successfully sent.";

            case "2":
                messageStatus = "Disregarded";
                System.out.println("Press 0 to delete the message.");
                return "Press 0 to delete the message.";

            case "3":
                messageStatus = "Stored";
                System.out.println("Message successfully stored.");
                return "Message successfully stored.";

            default:
                return "Invalid option selected.";
        }
    }

    public String printMessages() {
        return "Message ID: " + messageID
                + "\nMessage Hash: " + messageHash
                + "\nSender: " + sender
                + "\nRecipient: " + recipient
                + "\nMessage: " + messageText;
    }

    public static int returnTotalMessagess() {
        return messagesSent;
    }

    public void setMessageStatus(String messageStatus) {
        this.messageStatus = messageStatus;
    }

    public void setMessageID(String messageID) {
        this.messageID = messageID;
        this.messageHash = createMessageHash();
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getMessageID() {
        return messageID;
    }

    public int getMessageNumber() {
        return messageNumber;
    }

    public String getSender() {
        return sender;
    }

    public String getRecipient() {
        return recipient;
    }

    public String getMessageText() {
        return messageText;
    }

    public String getMessageHash() {
        return messageHash;
    }

    public String getMessageStatus() {
        return messageStatus;
    }
}
