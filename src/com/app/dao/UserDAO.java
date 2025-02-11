package com.app.dao;

import com.app.model.User;
import java.util.ArrayList;

public interface UserDAO {
    // Create
    public boolean registerUser(User user);
    
    // Read
    public User getUser(int id);
    public ArrayList<User> fetchUsers();  
    public ArrayList<User> searchUsers(String criteria);
    
    // Update
    public boolean updateUser(User newUser, User oldUser);
    public boolean updateUserPassword(String username, String password);
    
    // Delete
    public void archiveOrRestoreUser(User user);
    public void deleteUser(User user);
    
    // Login
    
    public User loginUser(String username, String password);
    public boolean isValidUserAccount(String username, String password);
}
