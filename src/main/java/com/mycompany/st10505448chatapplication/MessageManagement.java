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

    private final File jsonFile = new File("messages.json");

    public MessageManagement() {
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

            ArrayList<Message> loadedMessages =
                    gson.fromJson(json, listType);

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
    private void saveStoredMessages() {

        try {
            Files.writeString(
                    jsonFile.toPath(),
                    gson.toJson(storedMessages)
            );

        } catch (IOException e) {
            System.out.println("Error saving messages: " + e.getMessage());
        }
    }

    // Get the longest stored message
    public Message getLongestStoredMessage() {

        if (storedMessages.isEmpty()) {
            return null;
        }

        Message longest = storedMessages.get(0);

        for (Message message : storedMessages) {

            if (message.getMessageText().length()
                    > longest.getMessageText().length()) {

                longest = message;
            }
        }

        return longest;
    }

    // Search for a message using its ID
    public Message searchMessageByID(String messageID) {

        for (Message message : storedMessages) {

            if (message.getMessageID().equals(messageID)) {
                return message;
            }
        }

        return null;
    }

    // Search stored messages by recipient
    public ArrayList<Message> searchMessagesByRecipient(String recipient) {

        ArrayList<Message> results = new ArrayList<>();

        for (Message message : storedMessages) {

            if (message.getRecipient().equals(recipient)) {
                results.add(message);
            }
        }

        return results;
    }

    // Delete a stored message using its hash
    public boolean deleteMessageByHash(String messageHash) {

        boolean deleted = storedMessages.removeIf(
                message -> message.getMessageHash().equals(messageHash)
        );

        if (deleted) {
            saveStoredMessages();
        }

        return deleted;
    }

    // Generate a report of stored messages
    public String generateReport() {

        StringBuilder report = new StringBuilder();

        for (Message message : storedMessages) {

            report.append(message.printMessages());
            report.append("\nStatus: ");
            report.append(message.getMessageStatus());
            report.append("\n\n");
        }

        return report.toString();
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