package com.app.controller;

import com.app.dao.PackageDAO;
import com.app.dao.UserDAO;
import com.app.dao.impl.PackageDAOIMPL;
import com.app.model.User;
import com.app.util.PromptManager;
import com.app.util.TextHelper;
import com.app.view.PackageView;
import com.app.view.UserView;
import java.util.ArrayList;
import java.util.Calendar;


public class UserController {
    private UserDAO userDAO; 
    private UserView userView; 
    private PromptManager prompt;
    private TextHelper th = new TextHelper();
    
    private PackageDAO packageDAO = new PackageDAOIMPL();
    private PackageView packageView = new PackageView();
    
    private PackageController packageController = new PackageController(packageDAO, packageView);
    
    public UserController() {
        super();
    }
    
    public UserController( UserDAO userDAO, UserView userView ) {
        this.userDAO = userDAO;
        this.userView = userView;
    }
    
    public UserController( UserDAO userDAO) {
        this.userDAO = userDAO;
    }
    
    /* 
        -------------------------------------------------------------------------
                                        CREATE
        -------------------------------------------------------------------------
    */
    
    public void registerUsers(){
        
        User newUser = userView.createUsers(); 
        java.sql.Date registration_date, expiration_date;
        int package_id = 0;
        
        // Data Cleaning 
        if(newUser.getRole().equalsIgnoreCase("admin")){
            newUser.setRole("admin");
        }
        if(newUser.getRole().equalsIgnoreCase("member")){
            newUser.setRole("member");
        }
        if(newUser.getGender().equalsIgnoreCase("male")){
            newUser.setGender("Male");
        }
        if(newUser.getGender().equalsIgnoreCase("female")){
            newUser.setGender("Female");
        }
        if(newUser.getStatus().equalsIgnoreCase("single")){
            newUser.setStatus("Single");
        }
        if(newUser.getStatus().equalsIgnoreCase("married")){
            newUser.setStatus("Married");
        }
        if(newUser.getStatus().equalsIgnoreCase("widow")){
            newUser.setStatus("Widow");
        }
        if(newUser.getStatus().equalsIgnoreCase("separated")){
            newUser.setStatus("Separated");
        }
        
        Calendar cal = Calendar.getInstance();
        registration_date = new java.sql.Date(cal.getTime().getTime());
        
        if(newUser.getRole().equals("admin")){
            //Add 5 years expiration
            cal.add(Calendar.YEAR, 5); // add 5 years to expiration date
            expiration_date = new java.sql.Date(cal.getTime().getTime());
        }else{
            packageController.displayPackages();
            package_id = packageView.getPackageId();
            int duration = packageController.getTrainingDuration(package_id);
            cal.add(Calendar.DAY_OF_MONTH,duration);
            expiration_date = new java.sql.Date(cal.getTime().getTime());
        }
        
        newUser.setPackageId(package_id);
        newUser.setRegistrationDate(registration_date); 
        newUser.setExpirationDate(expiration_date);
        
        if(validateUser(newUser)){
            userDAO.registerUser(newUser);
            System.out.println(th.COLOR_BLUE + "Registration Successful!" + th.COLOR_RESET);
        }
        else{
            System.out.println(th.COLOR_RED + "Invalid Inputs. Please check the following details." + th.COLOR_RESET);
        }
        
    }
    /* 
        -------------------------------------------------------------------------
                                        READ
        -------------------------------------------------------------------------
    */
    
    public void displayUsers(){
        ArrayList<User> users = userDAO.fetchUsers();         
        userView.displayUsers(users); 
    }
    
    public void displayUser(){
        
        int id = userView.getUser("VIEW");
        User user = userDAO.getUser(id);
        
        if(user.getId() > 0 ){
            userView.displayUser(user);
        }
        else{
            System.out.println(th.COLOR_RED + "User Not Found!" + th.COLOR_RESET);
        }
        
    }
    
    public User getUserInfo(int userId){
        System.out.println("user id: " + userId);
        User user = userDAO.getUser(userId);
        return user;        
    }
    
    public void searchUsers(){
         
        String criteria = userView.searchUsers();
        
        ArrayList<User> users = userDAO.searchUsers(criteria);
        userView.displayUsers(users);
    }
    
    /* 
        -------------------------------------------------------------------------
                                        UPDATE
        -------------------------------------------------------------------------
    */
    
    public void updateUser(){
        
        int id = userView.getUser("UPDATE"); 
        User user = userDAO.getUser(id);
        
        User newUser = userView.updateUser(user);
        if (userDAO.updateUser(newUser, user)){
            System.out.println(th.COLOR_BLUE + "Update Sucessful!" + th.COLOR_RESET);
        }
        else{
            System.out.println(th.COLOR_RED + "User Not Found!" + th.COLOR_RESET);
        }   
    }
    
    public void updateUserPassword(){     
        
        try{
            User user = userView.displayUserPassword(); 
            if(null != user){
                boolean isSuccessfulUpdate = userDAO.updateUserPassword(user.getUsername(), user.getPassword());

                if(isSuccessfulUpdate){
                    System.out.println("displayinng successful page");
                    userView.displaySuccessfullPage();
                }else{
                     System.out.println("displayinng error page");
                    userView.displayErrorPage();
                }
            }  else{
                System.out.println("user is null");
            } 
        }catch(Exception ex){
        
        }
            
    }
    
    public boolean isCorrectCredentials(String username, String password){
        try{
            return userDAO.isValidUserAccount(username, password);
        }catch(Exception e){
            System.out.println(e.getMessage());
            StackTraceElement[] stackTrace = e.getStackTrace();
            for (StackTraceElement element : stackTrace) {
                // Print method name and line number
                System.out.println("Exception in method: " + element.getMethodName() + " at line: " + element.getLineNumber());
            }
        }        
        return false;
    }
    
    /* 
        -------------------------------------------------------------------------
                                        DELETE
        -------------------------------------------------------------------------
    */
    
    public void softDeleteUser(){
        prompt = new PromptManager();
        int id = userView.archiveUser(); 
        
        User user = userDAO.getUser(id);
        
        if(user.getId() > 0 ){
            boolean confirm = prompt.showConfirmMessage("archive user " + id);
            if(confirm){
                if(user.getArchived() == 0){
                    userDAO.archiveOrRestoreUser(user);
                    System.out.println(th.COLOR_BLUE + "User Archived Successfully!" + th.COLOR_RESET);
                }
                else{
                    System.out.println(th.COLOR_RED + "Cannot Archive an Archived User!" + th.COLOR_RESET);
                }
            }
            else{
                System.out.println(th.COLOR_RED + "User Archive Cancelled!" + th.COLOR_RESET);
            }
        }
        else{
            System.out.println(th.COLOR_RED + "User Not Found!" + th.COLOR_RESET);
        } 
        
    }
    
    public void restoreUser(){
        prompt = new PromptManager();
        int id = userView.restoreUser(); 
        
        User user = userDAO.getUser(id);
        
        if(user.getId() > 0 ){
            boolean confirm = prompt.showConfirmMessage("restore user " + id);
            if(confirm){
                if(user.getArchived() == 1){
                    userDAO.archiveOrRestoreUser(user);
                    System.out.println(th.COLOR_BLUE + "User Restored Successfully!" + th.COLOR_RESET);
                }
                else{
                    System.out.println(th.COLOR_RED + "Cannot Restore a Restored User!" + th.COLOR_RESET);
                }
            }
            else{
                System.out.println(th.COLOR_RED + "User Restoration Cancelled!" + th.COLOR_RESET);
            }
        }
        else{
            System.out.println(th.COLOR_RED + "User Not Found!" + th.COLOR_RESET);
        }
    }
    
    public void permaDeleteUser(){
        prompt = new PromptManager();
        int id = userView.permaDeleteUser();
        User user = userDAO.getUser(id);
        if(user.getId() > 0){
            boolean confirm = prompt.showConfirmMessage("delete user " + id);
            if(confirm){
                    userDAO.deleteUser(user);
                    System.out.println(th.COLOR_BLUE + "User Deleted Successfully!" + th.COLOR_RESET);
                }
            else{
                System.out.println(th.COLOR_RED + "User Deletion Cancelled!" + th.COLOR_RESET);
            }         
        }
        else{
                System.out.println(th.COLOR_RED + "User Not Found!" + th.COLOR_RESET);
        }
    }
   
    
    /* 
        -------------------------------------------------------------------------
                                        LOGIN
        -------------------------------------------------------------------------
    */
    
    public User loginUser(){
        User user = userView.loginUser();
        prompt = new PromptManager();
        
        User loggedUser = userDAO.loginUser(user.getUsername(), user.getPassword());
        
        return loggedUser;
        
    }
    
    public void displayLoggedUser(int id){
        User user = userDAO.getUser(id);
        
        if(user.getId() > 0 ){
            userView.displayUser(user);
        }
        else{
            System.out.println(th.COLOR_RED + "User Not Found!" + th.COLOR_RESET);
        }
    }
    
    /* 
        -------------------------------------------------------------------------
                                        VALIDATION
        -------------------------------------------------------------------------
    */
    
    public boolean validateUser(User user){
        //check for required fields
        
        if(user.getUsername().isEmpty() || user.getPassword().isEmpty() || user.getRole().isEmpty() 
                || user.getLastname().isEmpty() || user.getFirstname().isEmpty() 
                || user.getGender().isEmpty() || user.getAddress().isEmpty() 
                || user.getContact().isEmpty() || user.getStatus().isEmpty()){
            return false; 
        }
        
        return true;
    }
}
