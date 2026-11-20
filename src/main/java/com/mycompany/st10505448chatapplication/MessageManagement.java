package com.mycompany.st10505448chatapplication;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.util.ArrayList;

public class MessageManagement {

    private ArrayList<Message> sentMessages = new ArrayList<>();
    private ArrayList<Message> disregardedMessages = new ArrayList<>();
    private ArrayList<Message> storedMessages = new ArrayList<>();

    private ArrayList<String> messageHashes = new ArrayList<>();
    private ArrayList<String> messageIDs = new ArrayList<>();

    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private final File jsonFile;

    public MessageManagement() {
        this("messages.json");
    }

    public MessageManagement(String filePath) {
        this.jsonFile = new File(filePath);
        loadStoredMessages();
    }

    // Add messages to the correct arrays
    public void addMessage(Message message) {
        if (message == null) {
            return;
        }

        if (!messageIDs.contains(message.getMessageID())) {
            messageIDs.add(message.getMessageID());
        }

        if (!messageHashes.contains(message.getMessageHash())) {
            messageHashes.add(message.getMessageHash());
        }

        switch (message.getMessageStatus()) {
            case "Sent":
                sentMessages.add(message);
                break;

            case "Disregarded":
                disregardedMessages.add(message);
                break;

            case "Stored":
                storedMessages.add(message);
                saveStoredMessages();
                break;
        }
    }

    // Read stored messages from JSON
    public void loadStoredMessages() {
        if (!jsonFile.exists() || jsonFile.length() == 0) {
            return;
        }

        try {
            String json = Files.readString(jsonFile.toPath());
            Type listType = new TypeToken<ArrayList<Message>>() {}.getType();
            ArrayList<Message> loadedMessages = gson.fromJson(json, listType);

            if (loadedMessages != null) {
                storedMessages.clear();

                for (Message message : loadedMessages) {
                    storedMessages.add(message);

                    if (!messageIDs.contains(message.getMessageID())) {
                        messageIDs.add(message.getMessageID());
                    }

                    if (!messageHashes.contains(message.getMessageHash())) {
                        messageHashes.add(message.getMessageHash());
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading stored messages: " + e.getMessage());
        }
    }

    // Save stored messages to JSON
    public void saveStoredMessages() {
        try {
            Files.writeString(
                    jsonFile.toPath(),
                    gson.toJson(storedMessages)
            );
        } catch (IOException e) {
            System.out.println("Error saving messages: " + e.getMessage());
        }
    }

    // Option 2a: Display sender and recipient of all stored messages
    public String displayStoredSendersAndRecipients() {
        if (storedMessages.isEmpty()) {
            return "No stored messages found.";
        }

        StringBuilder sb = new StringBuilder();
        for (Message message : storedMessages) {
            sb.append("Sender: ").append(message.getSender())
              .append(" | Recipient: ").append(message.getRecipient())
              .append("\n");
        }
        return sb.toString().trim();
    }

    // Option 2b & Unit Test 2: Get the longest message across sent and stored messages
    public Message getLongestMessage() {
        ArrayList<Message> allMessages = new ArrayList<>();
        allMessages.addAll(sentMessages);
        allMessages.addAll(storedMessages);

        if (allMessages.isEmpty()) {
            return null;
        }

        Message longest = allMessages.get(0);
        for (Message message : allMessages) {
            if (message.getMessageText().length() > longest.getMessageText().length()) {
                longest = message;
            }
        }
        return longest;
    }

    // Option 2c & Unit Test 3: Search for a message using its ID
    public Message searchMessageByID(String messageID) {
        for (Message message : sentMessages) {
            if (message.getMessageID().equals(messageID)) {
                return message;
            }
        }

        for (Message message : storedMessages) {
            if (message.getMessageID().equals(messageID)) {
                return message;
            }
        }

        return null;
    }

    // Option 2d & Unit Test 4: Search sent or stored messages by recipient
    public ArrayList<Message> searchMessagesByRecipient(String recipient) {
        ArrayList<Message> results = new ArrayList<>();

        for (Message message : sentMessages) {
            if (message.getRecipient().equals(recipient)) {
                results.add(message);
            }
        }

        for (Message message : storedMessages) {
            if (message.getRecipient().equals(recipient)) {
                results.add(message);
            }
        }

        return results;
    }

    // Option 2e & Unit Test 5: Delete a message using its hash
    public String deleteMessageByHash(String messageHash) {
        for (int i = 0; i < storedMessages.size(); i++) {
            Message msg = storedMessages.get(i);
            if (msg.getMessageHash().equals(messageHash)) {
                String deletedText = msg.getMessageText();
                storedMessages.remove(i);
                messageHashes.remove(messageHash);
                messageIDs.remove(msg.getMessageID());
                saveStoredMessages();
                return "Message \"" + deletedText + "\" successfully deleted.";
            }
        }

        for (int i = 0; i < sentMessages.size(); i++) {
            Message msg = sentMessages.get(i);
            if (msg.getMessageHash().equals(messageHash)) {
                String deletedText = msg.getMessageText();
                sentMessages.remove(i);
                messageHashes.remove(messageHash);
                messageIDs.remove(msg.getMessageID());
                return "Message \"" + deletedText + "\" successfully deleted.";
            }
        }

        return "Message not found.";
    }

    // Option 2f: Generate a report of all stored messages
    public String generateStoredReport() {
        if (storedMessages.isEmpty()) {
            return "No stored messages found.";
        }

        StringBuilder report = new StringBuilder();
        for (Message message : storedMessages) {
            report.append(message.printMessages())
                  .append("\nStatus: ")
                  .append(message.getMessageStatus())
                  .append("\n\n");
        }
        return report.toString().trim();
    }

    // Unit Test 6: Generate a report of all sent messages (Hash, Recipient, Message)
    public String generateSentReport() {
        if (sentMessages.isEmpty()) {
            return "No sent messages found.";
        }

        StringBuilder report = new StringBuilder();
        for (Message message : sentMessages) {
            report.append("Message Hash: ").append(message.getMessageHash())
                  .append("\nRecipient: ").append(message.getRecipient())
                  .append("\nMessage: ").append(message.getMessageText())
                  .append("\n\n");
        }
        return report.toString().trim();
    }

    // Getters for the arrays
    public ArrayList<Message> getSentMessages() {
        return sentMessages;
    }

    public ArrayList<Message> getDisregardedMessages() {
        return disregardedMessages;
    }

    public ArrayList<Message> getStoredMessages() {
        return storedMessages;
    }

    public ArrayList<String> getMessageHashes() {
        return messageHashes;
    }

    public ArrayList<String> getMessageIDs() {
        return messageIDs;
    }
}
