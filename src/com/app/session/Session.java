package com.app.session;

import com.app.model.User;

public class Session {
    private int logged_user_id;
    private String logged_username;

    public Session() {
        this.logged_user_id = 0;
        this.logged_username = "";
    }

    public void loginSession(User user) {
        this.logged_user_id = user.getId(); 
        this.logged_username = user.getUsername();
    }

    public int getLoggedUserId() {
        return this.logged_user_id;
    }
    
    public String getLoggedUserName() {
        return this.logged_username;
    }
    
    public void clearSession() {
        logged_user_id = 0;
        logged_username = "";
    }
}