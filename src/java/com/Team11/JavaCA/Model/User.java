
package com.Team11.JavaCA.Model;


public class User {
    private String userName;
    private String userPassword;
    
    public User() {
    
    }
    
    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
    
    public User(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "User{" + "userName=" + userName + '}';
    }
    
}
