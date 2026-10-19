package com.mycompany.st10505448chatapplication;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestUnits {

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
        Message message = new Message(
                "+27718693002",
                "Hello",
                0
        );

        assertEquals(
                "Cell phone number successfully captured.",
                message.checkRecipientCell()
        );
    }

    @Test
    public void testInvalidRecipientCell() {
        Message message = new Message(
                "08575975889",
                "Hello",
                0
        );

        assertEquals(
                "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.",
                message.checkRecipientCell()
        );
    }

    @Test
    public void testMessageLengthSuccess() {
        Message message = new Message(
                "+27718693002",
                "Hello, how are you?",
                0
        );

        assertEquals(
                "Message ready to send.",
                message.checkMessageLength()
        );
    }

    @Test
    public void testMessageLengthFailure() {
        String longMessage = "A".repeat(251);

        Message message = new Message(
                "+27718693002",
                longMessage,
                0
        );

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

        String expectedHash = message.getMessageID().substring(0, 2)
                + ":0:HITONIGHT";

        assertEquals(expectedHash, message.createMessageHash());
    }

    @Test
    public void testSendMessage() {
        Message message = new Message(
                "+27718693002",
                "Hello",
                0
        );

        assertEquals(
                "Message successfully sent.",
                message.SentMessage("1")
        );

        assertEquals("Sent", message.getMessageStatus());
    }

    @Test
    public void testDisregardMessage() {
        Message message = new Message(
                "+27718693002",
                "Hello",
                0
        );

        assertEquals(
                "Press 0 to delete the message.",
                message.SentMessage("2")
        );

        assertEquals("Disregarded", message.getMessageStatus());
    }

    @Test
    public void testStoreMessage() {
        Message message = new Message(
                "+27718693002",
                "Hello",
                0
        );

        assertEquals(
                "Message successfully stored.",
                message.SentMessage("3")
        );

        assertEquals("Stored", message.getMessageStatus());
    }
}