/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.st10505448chatapplication;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author User
 */
public class LoginTest {
    
    public LoginTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

        @Test
    public void testCheckUserName() {
        Login login = new Login();

        assertTrue(login.checkUserName("sam_"));
        assertFalse(login.checkUserName("samantha"));
    }

    @Test
    public void testCheckPasswordComplexity() {
        Login login = new Login();

        assertTrue(login.checkPasswordComplexity("Password1!"));
        assertFalse(login.checkPasswordComplexity("password"));
    }

    @Test
    public void testCheckCellPhoneNumber() {
        Login login = new Login();

        assertTrue(login.checkCellPhoneNumber("+27821234567"));
        assertFalse(login.checkCellPhoneNumber("0821234567"));
    }

    @Test
    public void testRegisterUser() {
        Login login = new Login(
                "Samantha",
                "Chovava",
                "sam_",
                "Password1!",
                "+27821234567"
        );

        assertEquals("User registered successfully.", login.registerUser());
    }

    @Test
    public void testLoginUser() {
        Login login = new Login(
                "Samantha",
                "Chovava",
                "sam_",
                "Password1!",
                "+27821234567"
        );

        login.setLoginDetails("sam_", "Password1!");

        assertTrue(login.loginUser());
    }

    @Test
    public void testLoginUserIncorrectDetails() {
        Login login = new Login(
                "Samantha",
                "Chovava",
                "sam_",
                "Password1!",
                "+27821234567"
        );

        login.setLoginDetails("wrong_", "wrongPassword");

        assertFalse(login.loginUser());
    }
}
