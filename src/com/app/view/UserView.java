package com.app.view;

import com.app.model.User;
import com.app.util.Helper;
import java.util.ArrayList;
import java.util.Scanner;
import com.app.util.TextHelper;
import java.awt.*;
import javax.swing.*;

public class UserView {
        
    TextHelper th = new TextHelper();
    
    /* 
        -------------------------------------------------------------------------
                                        READ
        -------------------------------------------------------------------------
    */
    
    public void displayUsers(ArrayList<User> users){
        if(users.isEmpty()){
            System.out.println(th.COLOR_RED + "No Users Found!" + th.COLOR_RESET);
        }
        else{
            System.out.println("\n--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.printf("%-4s| %-30s| %-10s| %-10s| %-20s| %-20s| %-20s| %-20s| %-10s| %-20s| %n", "ID", "NAME", "ROLE", "GENDER", "ADDRESS", "CONTACT", "REGISTRATION DATE", "EXPIRATION DATE", "STATUS", "PACKAGE_NAME");
            System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

            users.forEach((user) -> {
                System.out.printf("%-4s| %-30s| %-10s| %-10s| %-20s| %-20s| %-20s| %-20s| %-10s| %-20s| %n", user.getId(), user.getLastname() + ", " + user.getFirstname() + " " + user.getMiddlename() , user.getRole(), user.getGender(), user.getAddress(), user.getContact(), user.getRegistrationDate(), user.getExpirationDate(), user.getStatus(), user.getPackageName());            
            });
            System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        }
    }   
    
    public void displayUser(User user){
        System.out.println("\n--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-4s| %-30s| %-10s| %-10s| %-20s| %-20s| %-20s| %-20s| %-10s| %-20s| %n", "ID", "NAME", "ROLE", "GENDER", "ADDRESS", "CONTACT", "REGISTRATION DATE", "EXPIRATION DATE", "STATUS", "PACKAGE NAME");
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        
        System.out.printf("%-4s| %-30s| %-10s| %-10s| %-20s| %-20s| %-20s| %-20s| %-10s| %-20s| %n", user.getId(), user.getLastname() + ", " + user.getFirstname() + " " + user.getMiddlename() , user.getRole(),user.getGender(), user.getAddress(), user.getContact(), user.getRegistrationDate(), user.getExpirationDate(), user.getStatus(), user.getPackageName());
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
    }
    
    public int getUser(String operation){
        Scanner sc = new Scanner(System.in);
        int id;
        
        System.out.println(th.DIVIDER);
        System.out.println(th.COLOR_BLUE + "\t\t\t " + operation + " USER" + th.COLOR_RESET);
        System.out.println(th.DIVIDER);
        System.out.print("Enter ID: ");
        id = sc.nextInt(); 
        
        return id;
    }
    
    public String searchUsers(){
        Scanner sc = new Scanner(System.in); 
        String criteria; 
        
        System.out.println(th.DIVIDER);
        System.out.println(th.COLOR_BLUE + "\t\t\tSEARCH USERS" + th.COLOR_RESET);
        System.out.println(th.DIVIDER);
        
        System.out.println("Enter name to search: ");
        criteria = sc.nextLine(); 
        
        return criteria;    
    }
    
    
    /* 
        -------------------------------------------------------------------------
                                        CREATE
        -------------------------------------------------------------------------
    */
    
    public User createUsers(){
        Scanner sc = new Scanner(System.in);   
        String username, password, role, lastname, firstname, middlename, gender, address, contact, status;
               
        username = "";
        password = "";
        
        System.out.println(th.DIVIDER);
        System.out.println(th.COLOR_BLUE + "\t\t\tREGISTER USER" + th.COLOR_RESET);
        System.out.println(th.DIVIDER);
        System.out.print("Enter ID: ");
        
        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel userPanel = new JPanel();
        JLabel usernameLabel = new JLabel("Username: "); 
        JTextField usernameField = new JTextField(15);
        userPanel.add(usernameLabel);
        userPanel.add(usernameField);
        JPanel passwordPanel = new JPanel();
        JLabel passwordLabel = new JLabel("Password: ");
        JPasswordField passwordField = new JPasswordField(15);
        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordField);
        mainPanel.add(userPanel, BorderLayout.NORTH);
        mainPanel.add(passwordPanel, BorderLayout.SOUTH);
        
        int option = JOptionPane.showOptionDialog(null, mainPanel, "Enter Login Credentials", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);
            
        if(option == JOptionPane.OK_OPTION){
            username = usernameField.getText();
            char[] char_password;
            char_password = passwordField.getPassword();
            password = new String(char_password);
        }
        
        //At this point we will assume that roles are only admin and member
        System.out.print("Role [admin or member]: ");
        role = sc.nextLine(); 
        System.out.print("Lastname: ");
        lastname = sc.nextLine(); 
        System.out.print("Firstname: ");
        firstname = sc.nextLine(); 
        System.out.print("Middlename: ");
        middlename = sc.nextLine();
        System.out.print("Gender: [male or female]: "); 
        gender = sc.nextLine();
        System.out.print("Address: ");
        address = sc.nextLine();
        System.out.print("Contact Number:");
        contact = sc.nextLine();
        System.out.print("Civil Status: [single, married, widow or separated]: ");
        status = sc.nextLine();

        
        User user = new User(); 
        user.setUsername(username);
        user.setPassword(password);
        user.setRole(role);
        user.setLastname(lastname);
        user.setFirstname(firstname);
        user.setMiddlename(middlename);
        user.setGender(gender);
        user.setAddress(address);
        user.setContact(contact);
        user.setStatus(status);
        
        return user; 
        
    }
    
    /* 
        -------------------------------------------------------------------------
                                        UPDATE
        -------------------------------------------------------------------------
    */
    
    public User updateUser(User user){
        Scanner sc = new Scanner(System.in);
        String lastname, firstname, middlename, gender, address, contact, status; 
             
        System.out.println(th.DIVIDER);
        System.out.println(th.COLOR_BLUE + "\t\t\tUPDATE USER" + th.COLOR_RESET);
        System.out.println(th.DIVIDER);
        System.out.print("Enter ID: ");
        
        System.out.print("Lastname [PRESS ENTER TO RETAIN (" + user.getLastname() + ") ]: ");
        lastname = sc.nextLine(); 
        System.out.print("Firstname [PRESS ENTER TO RETAIN (" + user.getFirstname() + ") ]: ");
        firstname = sc.nextLine();
        System.out.print("Middlename [PRESS ENTER TO RETAIN (" + user.getMiddlename() + ") ]: ");
        middlename = sc.nextLine();
        System.out.print("Gender [PRESS ENTER TO RETAIN (" + user.getGender() + ") ]: ");
        gender = sc.nextLine();
        System.out.print("Adress [PRESS ENTER TO RETAIN (" + user.getAddress() + ") ]: ");
        address = sc.nextLine();
        System.out.print("contact [PRESS ENTER TO RETAIN (" + user.getContact() + ") ]: ");
        contact = sc.nextLine();
        System.out.print("Status [PRESS ENTER TO RETAIN (" + user.getStatus() + ") ]: ");
        status = sc.nextLine(); 
        
        return new User(
                lastname, 
                firstname,
                middlename, 
                gender,
                address, 
                contact,
                status
        );
    }
    
    /* 
        -------------------------------------------------------------------------
                                       DELETE
        -------------------------------------------------------------------------
    */
    
    public int permaDeleteUser(){
        return getUser("PERMANENT DELETE");
    }
    
    public int archiveUser(){
        return getUser("ARCHIVE");
    }
    
    public int restoreUser(){
        return getUser("RESTORE");
    }
    
    /* 
        -------------------------------------------------------------------------
                                        LOGIN
        -------------------------------------------------------------------------
    */
    
    public User loginUser(){
        String username = ""; 
        String password = "";
        
        System.out.println(th.DIVIDER);
        System.out.println(th.COLOR_BLUE + "\t\t\tLOGIN" + th.COLOR_RESET);
        System.out.println(th.DIVIDER);
        System.out.println(th.COLOR_RED + "For security purposes, use the Login Dialog Box for login. Thank you." + th.COLOR_RESET);
        
        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel userPanel = new JPanel();
        JLabel usernameLabel = new JLabel("Username: "); 
        JTextField usernameField = new JTextField(15);
        userPanel.add(usernameLabel);
        userPanel.add(usernameField);
        JPanel passwordPanel = new JPanel();
        JLabel passwordLabel = new JLabel("Password: ");
        JPasswordField passwordField = new JPasswordField(15);
        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordField);
        mainPanel.add(userPanel, BorderLayout.NORTH);
        mainPanel.add(passwordPanel, BorderLayout.SOUTH);



        int option = JOptionPane.showOptionDialog(null, mainPanel, "Login", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);
            
        if(option == JOptionPane.OK_OPTION){
            username = usernameField.getText();
            char[] char_password;
            char_password = passwordField.getPassword();
            password = new String(char_password);
        }
        //password = sc.nextLine();
              
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        
        return user;
    }
    
    public User displayUserPassword() {
        
        User user = new User();
        
        try{
            while (true) {
            
                JPanel panel = new JPanel(new GridBagLayout());
                panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

                GridBagConstraints gbc = new GridBagConstraints();
                gbc.fill = GridBagConstraints.HORIZONTAL;
                gbc.insets = new Insets(10, 10, 10, 10); 

                // Title Label
                JLabel titleLabel = new JLabel("Update Password");
                titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
                titleLabel.setForeground(new Color(50, 50, 150)); 
                gbc.gridx = 0;
                gbc.gridy = 0;
                gbc.gridwidth = 2;
                gbc.anchor = GridBagConstraints.CENTER;
                panel.add(titleLabel, gbc);

                // Reset grid width
                gbc.gridwidth = 1;
                gbc.anchor = GridBagConstraints.WEST;

                // User ID
                JLabel usernameLabel = new JLabel("Username: ");
                usernameLabel.setFont(new Font("Arial", Font.PLAIN, 14));
                gbc.gridx = 0;
                gbc.gridy = 1;
                panel.add(usernameLabel, gbc);

                JTextField usernameField = new JTextField(15);
                gbc.gridx = 1;
                panel.add(usernameField, gbc);

                // Old Password
                JLabel oldPasswordLabel = new JLabel("Old Password: ");
                oldPasswordLabel.setFont(new Font("Arial", Font.PLAIN, 14));
                gbc.gridx = 0;
                gbc.gridy = 2;
                panel.add(oldPasswordLabel, gbc);

                JPasswordField oldPasswordField = new JPasswordField(15);
                gbc.gridx = 1;
                panel.add(oldPasswordField, gbc);

                // New Password
                JLabel newPasswordLabel = new JLabel("New Password: ");
                newPasswordLabel.setFont(new Font("Arial", Font.PLAIN, 14));
                gbc.gridx = 0;
                gbc.gridy = 3;
                panel.add(newPasswordLabel, gbc);

                JPasswordField newPasswordField = new JPasswordField(15);
                gbc.gridx = 1;
                panel.add(newPasswordField, gbc);

                // Confirm New Password
                JLabel confirmPasswordLabel = new JLabel("Confirm New Password: ");
                confirmPasswordLabel.setFont(new Font("Arial", Font.PLAIN, 14));
                gbc.gridx = 0;
                gbc.gridy = 4;
                panel.add(confirmPasswordLabel, gbc);

                JPasswordField confirmPasswordField = new JPasswordField(15);
                gbc.gridx = 1;
                panel.add(confirmPasswordField, gbc);

                // Show the panel in a dialog box
                int option = JOptionPane.showConfirmDialog(null, panel, "Update Password",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);


                // If user clicks OK, process the input
                if (option == JOptionPane.OK_OPTION) {
                    String username = usernameField.getText();
                    String oldPassword = new String(oldPasswordField.getPassword());
                    String newPassword = new String(newPasswordField.getPassword());
                    String confirmPassword = new String(confirmPasswordField.getPassword());

                    // Validate inputs
                    if (username.isEmpty() || oldPassword.isEmpty() || newPassword.isEmpty() || confirmPassword.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "All fields must be filled!", "Error", JOptionPane.ERROR_MESSAGE);
                        continue; 
                    }

                    if (!newPassword.equals(confirmPassword)) {
                        JOptionPane.showMessageDialog(null, "New passwords do not match!", "Error", JOptionPane.ERROR_MESSAGE);
                        continue; 
                    }

                    if (!Helper.isValidUser(username, oldPassword)) {
                        JOptionPane.showMessageDialog(null, "Old password is incorrect!", "Error", JOptionPane.ERROR_MESSAGE);
                        continue; 
                    } else {
                        user.setUsername(username);
                        user.setPassword(newPassword);
                        break;
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Password update canceled.", "Canceled", JOptionPane.WARNING_MESSAGE);
                    break;
                }
            }
            
        }catch(Exception e){
            System.out.println(e.getMessage());
            StackTraceElement[] stackTrace = e.getStackTrace();
            for (StackTraceElement element : stackTrace) {
                // Print method name and line number
                System.out.println("Exception in method: " + element.getMethodName() + " at line: " + element.getLineNumber());
            }
        }
        
        return user;
    }
    
    public void displaySuccessfullPage(){
        JOptionPane.showMessageDialog(null, "Password updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void displayErrorPage(){
        JOptionPane.showMessageDialog(null, "Failed to update password", "error", JOptionPane.INFORMATION_MESSAGE);
    }
}
