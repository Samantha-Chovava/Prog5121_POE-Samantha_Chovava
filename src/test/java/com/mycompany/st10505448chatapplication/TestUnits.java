package com.mycompany.st10505448chatapplication;

import java.io.File;
import java.util.ArrayList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestUnits {

    private static final String TEST_JSON = "test_messages.json";
    private MessageManagement manager;
    private Message msg1;
    private Message msg2;
    private Message msg3;
    private Message msg4;
    private Message msg5;

    @BeforeEach
    public void setUp() {
        File file = new File(TEST_JSON);
        if (file.exists()) {
            file.delete();
        }

        manager = new MessageManagement(TEST_JSON);

        // Test Data Message 1 (Sent)
        msg1 = new Message("+27834557896", "Did you get the cake?", 1);
        msg1.SentMessage("1");

        // Test Data Message 2 (Stored)
        msg2 = new Message("+27838884567", "Where are you? You are late! I have asked you to be on time.", 2);
        msg2.SentMessage("3");

        // Test Data Message 3 (Disregard)
        msg3 = new Message("+27834484567", "Yohoooo, I am at your gate.", 3);
        msg3.SentMessage("2");

        // Test Data Message 4 (Sent)
        msg4 = new Message("Developer", "+27838884567", "It is dinner time !", 4);
        msg4.setMessageID("0838884567");
        msg4.SentMessage("1");

        // Test Data Message 5 (Stored)
        msg5 = new Message("+27838884567", "Ok, I am leaving without you.", 5);
        msg5.SentMessage("3");
    }

    @AfterEach
    public void tearDown() {
        File file = new File(TEST_JSON);
        if (file.exists()) {
            file.delete();
        }
    }

    // --- PART 2 UNIT TESTS ---

    @Test
    public void testMessageID() {
        Message message = new Message(
                "+27718693002",
                "Hi Mike, can you join us for dinner tonight?",
                0
        );

        assertTrue(message.checkMessageID());
        assertEquals(10, message.getMessageID().length());
        assertTrue(message.getMessageID().matches("\\d{10}"));
    }

    @Test
    public void testValidRecipientCell() {
        Message message = new Message("+27718693002", "Hello", 0);
        assertEquals(
                "Cell phone number successfully captured.",
                message.checkRecipientCell()
        );
    }

    @Test
    public void testInvalidRecipientCell() {
        Message message = new Message("08575975889", "Hello", 0);
        assertEquals(
                "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.",
                message.checkRecipientCell()
        );
    }

    @Test
    public void testMessageLengthSuccess() {
        Message message = new Message("+27718693002", "Hello, how are you?", 0);
        assertEquals("Message ready to send.", message.checkMessageLength());
    }

    @Test
    public void testMessageLengthFailure() {
        String longMessage = "A".repeat(251);
        Message message = new Message("+27718693002", longMessage, 0);
        assertEquals(
                "Message exceeds 250 characters by 1; please reduce the size.",
                message.checkMessageLength()
        );
    }

    @Test
    public void testMessageHash() {
        Message message = new Message(
                "+27718693002",
                "Hi Mike, can you join us for dinner tonight?",
                0
        );

        String expectedHash = message.getMessageID().substring(0, 2) + ":0:HITONIGHT";
        assertEquals(expectedHash, message.createMessageHash());
    }

    @Test
    public void testSendMessage() {
        Message message = new Message("+27718693002", "Hello", 0);
        assertEquals("Message successfully sent.", message.SentMessage("1"));
        assertEquals("Sent", message.getMessageStatus());
    }

    @Test
    public void testDisregardMessage() {
        Message message = new Message("+27718693002", "Hello", 0);
        assertEquals("Press 0 to delete the message.", message.SentMessage("2"));
        assertEquals("Disregarded", message.getMessageStatus());
    }

    @Test
    public void testStoreMessage() {
        Message message = new Message("+27718693002", "Hello", 0);
        assertEquals("Message successfully stored.", message.SentMessage("3"));
        assertEquals("Stored", message.getMessageStatus());
    }

    // --- PART 3 UNIT TESTS ---

    @Test
    public void testSentMessagesArrayPopulated() {
        manager.addMessage(msg1);
        manager.addMessage(msg2);
        manager.addMessage(msg3);
        manager.addMessage(msg4);

        ArrayList<Message> sent = manager.getSentMessages();
        assertEquals(2, sent.size());
        assertEquals("Did you get the cake?", sent.get(0).getMessageText());
        assertEquals("It is dinner time !", sent.get(1).getMessageText());
    }

    @Test
    public void testDisplayLongestMessage() {
        manager.addMessage(msg1);
        manager.addMessage(msg2);
        manager.addMessage(msg3);
        manager.addMessage(msg4);

        Message longest = manager.getLongestMessage();
        assertNotNull(longest);
        assertEquals(
                "Where are you? You are late! I have asked you to be on time.",
                longest.getMessageText()
        );
    }

    @Test
    public void testSearchForMessageID() {
        manager.addMessage(msg1);
        manager.addMessage(msg2);
        manager.addMessage(msg3);
        manager.addMessage(msg4);

        Message found = manager.searchMessageByID("0838884567");
        assertNotNull(found);
        assertEquals("It is dinner time !", found.getMessageText());
    }

    @Test
    public void testSearchMessagesByRecipient() {
        manager.addMessage(msg2);
        manager.addMessage(msg5);

        ArrayList<Message> results = manager.searchMessagesByRecipient("+27838884567");
        assertEquals(2, results.size());
        assertEquals(
                "Where are you? You are late! I have asked you to be on time.",
                results.get(0).getMessageText()
        );
        assertEquals(
                "Ok, I am leaving without you.",
                results.get(1).getMessageText()
        );
    }

    @Test
    public void testDeleteMessageByHash() {
        manager.addMessage(msg2);

        String hash = msg2.getMessageHash();
        String response = manager.deleteMessageByHash(hash);

        assertEquals(
                "Message \"Where are you? You are late! I have asked you to be on time.\" successfully deleted.",
                response
        );
        assertTrue(manager.getStoredMessages().isEmpty());
    }

    @Test
    public void testDisplaySentReport() {
        manager.addMessage(msg1);
        manager.addMessage(msg4);

        String report = manager.generateSentReport();

        assertTrue(report.contains(msg1.getMessageHash()));
        assertTrue(report.contains("+27834557896"));
        assertTrue(report.contains("Did you get the cake?"));

        assertTrue(report.contains(msg4.getMessageHash()));
        assertTrue(report.contains("+27838884567"));
        assertTrue(report.contains("It is dinner time !"));
    }
}
