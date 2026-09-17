/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.st10505448chatapplication;

/**
 *
 * @author User
 */
public class Login {
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private String cellPhoneNumber;
    
    // Check if the username is correctly formatted
    public boolean checkUserName(String userName) {
        return userName != null
                && userName.contains("_")
                && userName.length() <= 5;
    }  
    
    // Check if the password is correctly formatted
    public boolean checkPasswordComplexity(String password) {
        if (password == null || password.length() < 8) {
            return false;
        }
        
        boolean hasCapitalLetter = false;
        boolean hasNumber = false;
        boolean hasSpecialCharacter = false;
        
        for (int i = 0; i < password.length(); i++) {
            char character = password.charAt(i);
            
            if (Character.isUpperCase(character)) {
                hasCapitalLetter = true;
            } else if (Character.isDigit(character)) {
                hasNumber = true;
            } else if (!Character.isLetterOrDigit(character)) {
                hasSpecialCharacter = true;
            }
        }
        return hasCapitalLetter && hasNumber && hasSpecialCharacter;
    }
        // Check if the cellphone number is correctly formatted
    public boolean checkCellPhoneNumber(String cellPhoneNumber) {
        return cellPhoneNumber != null
                && cellPhoneNumber.matches("^\\+27\\d{9}$");
    }
}
