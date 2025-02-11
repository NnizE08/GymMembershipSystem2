/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.app.util;

import com.app.controller.UserController;
import com.app.dao.UserDAO;
import com.app.dao.impl.UserDAOIMPL;
import com.app.model.User;
import com.app.view.UserView;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

/**
 *
 * @author MJ
 */
public class Helper {
    
    public static UserDAO userDAO = new UserDAOIMPL();
    public static UserController userController = new UserController(userDAO);
    public static User user = null;
    
   // public static UserController userController = new UserController();
    
    public static User getUserInfo(int userId) {
        user = null;
        try{
            user = userController.getUserInfo(userId);
        }catch(Exception ex){
            System.out.println(ex.toString());
        }
       System.out.println("user: " + user.toString());
        return  user != null ? user: null ;
    }
    
    public static boolean isNumeric(String str) {
        return str.matches("\\d+"); // Matches only digits (positive numbers)
    }
    
    public static String repeat(char ch, int times) {
        StringBuilder sb = new StringBuilder(times);
        for (int i = 0; i < times; i++) {
            sb.append(ch);
        }
        return sb.toString();
    }
    
    public static boolean isValidUser(String username, String password){
        String encryptedPassword = encryptPassword(password);
        System.out.println("encrypted: " + encryptedPassword);
        try{
            return userController.isCorrectCredentials(username, encryptedPassword);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
            StackTraceElement[] stackTrace = ex.getStackTrace();
            for (StackTraceElement element : stackTrace) {
                // Print method name and line number
                System.out.println("Exception in method: " + element.getMethodName() + " at line: " + element.getLineNumber());
            }
        }

        return false;       
    }
    
    public static void main(String[] args) {
        System.out.println("pass:" + encryptPassword("password"));
    }
    
    public static String encryptPassword(String password) {
        String encryptedPass = null;
        try {
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.update(password.getBytes());  
            byte[] bytes = m.digest();  
            
            StringBuilder s = new StringBuilder();  
            for(int i = 0; i < bytes.length; i++) {  
                s.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));  
            }  
            
            encryptedPass = s.toString();  
        } catch (NoSuchAlgorithmException e) {
            System.out.println("No Such Algorithm Exists " + e.getMessage());
        }
        return encryptedPass;
    }

    
}
