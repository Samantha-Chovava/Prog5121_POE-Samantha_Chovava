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
    private String loginUserName;
    private String loginPassword;
    
    public Login() {
}
    
public Login(String firstName, String lastName, String userName, String password, String cellPhoneNumber) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.userName = userName;
    this.password = password;
    this.cellPhoneNumber = cellPhoneNumber;
}
    
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
        public void setLoginDetails(String loginUserName, String loginPassword) {
    this.loginUserName = loginUserName;
    this.loginPassword = loginPassword;
}
        
        public boolean loginUser() {
    return userName != null
            && password != null
            && userName.equals(loginUserName)
            && password.equals(loginPassword);
}
        
        public String registerUser() {
    if (!checkUserName(userName)) {
        return "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";
    }

    if (!checkPasswordComplexity(password)) {
        return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
    }

    if (!checkCellPhoneNumber(cellPhoneNumber)) {
        return "Cell phone number is incorrectly formatted or does not contain an international code; please correct the number and try again.";
    }

    return "User registered successfully.";
}
}
