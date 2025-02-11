package com.app.model;

import java.sql.Date;

public class User {
    private int id; 
    private String username; 
    private String password;
    private String role;
    private String lastname;
    private String firstname;
    private String middlename;
    private String gender;
    private String address;
    private String contact;
    private Date registrationDate;
    private Date expirationDate;
    private String status; 
    private int packageId; 
    private String packageName;
    private int archived; 

    // Empty constructor
    public User(){

    }

    // Constructor with all the fields 
     public User(int id, String username, String password, String role, String lastname, String firstname, String middlename, String gender, String address, String contact, Date registrationDate, Date expirationDate, String status, int packageId, int archived, String packageName) {
         this.id = id;
         this.username = username;
         this.password = password;
         this.role = role;
         this.lastname = lastname;
         this.firstname = firstname;
         this.middlename = middlename;
         this.gender = gender;
         this.address = address;
         this.contact = contact;
         this.registrationDate = registrationDate;
         this.expirationDate = expirationDate;
         this.status = status;
         this.packageId = packageId;
         this.archived = archived;
         this.packageName = packageName;
     }

     public User(int id, String username, String password, String role, String lastname, String firstname, String middlename, String gender, String address, String contact, Date registrationDate, Date expirationDate, String status, int packageId, int archived) {
         this.id = id;
         this.username = username;
         this.password = password;
         this.role = role;
         this.lastname = lastname;
         this.firstname = firstname;
         this.middlename = middlename;
         this.gender = gender;
         this.address = address;
         this.contact = contact;
         this.registrationDate = registrationDate;
         this.expirationDate = expirationDate;
         this.status = status;
         this.packageId = packageId;
         this.archived = archived;
     }

     //Constructor with all fields except id
     public User(String username, String password, String role, String lastname, String firstname, String middlename, String gender, String address, String contact, Date registrationDate, Date expirationDate, String status, int packageId, int archived) {
         this.username = username;
         this.password = password;
         this.role = role;
         this.lastname = lastname;
         this.firstname = firstname;
         this.middlename = middlename;
         this.gender = gender;
         this.address = address;
         this.contact = contact;
         this.registrationDate = registrationDate;
         this.expirationDate = expirationDate;
         this.status = status;
         this.packageId = packageId;
         this.archived = archived;
     }

     public User(String lastname, String firstname, String middlename, String gender, String address, String contact, String status) {
         this.lastname = lastname;
         this.firstname = firstname;
         this.middlename = middlename;
         this.gender = gender;
         this.address = address;
         this.contact = contact;
         this.status = status;
     }



     public int getId() {
         return id;
     }

     public void setId(int id) {
         this.id = id;
     }

     public String getUsername() {
         return username;
     }

     public void setUsername(String username) {
         this.username = username;
     }

     public String getPassword() {
         return password;
     }

     public void setPassword(String password) {
         this.password = password;
     }

     public String getRole() {
         return role;
     }

     public void setRole(String role) {
         this.role = role;
     }

     public String getLastname() {
         return lastname;
     }

     public void setLastname(String lastname) {
         this.lastname = lastname;
     }

     public String getFirstname() {
         return firstname;
     }

     public void setFirstname(String firstname) {
         this.firstname = firstname;
     }

     public String getMiddlename() {
         return middlename;
     }

     public void setMiddlename(String middlename) {
         this.middlename = middlename;
     }

     public String getGender() {
         return gender;
     }

     public void setGender(String gender) {
         this.gender = gender;
     }

     public String getAddress() {
         return address;
     }

     public void setAddress(String address) {
         this.address = address;
     }

     public String getContact() {
         return contact;
     }

     public void setContact(String contact) {
         this.contact = contact;
     }

     public Date getRegistrationDate() {
         return registrationDate;
     }

     public void setRegistrationDate(Date registrationDate) {
         this.registrationDate = registrationDate;
     }

     public Date getExpirationDate() {
         return expirationDate;
     }

     public void setExpirationDate(Date expirationDate) {
         this.expirationDate = expirationDate;
     }

     public String getStatus() {
         return status;
     }

     public void setStatus(String status) {
         this.status = status;
     }

     public int getPackageId() {
         return packageId;
     }

     public void setPackageId(int packageId) {
         this.packageId = packageId;
     }

     public int getArchived() {
         return archived;
     }

     public void setArchived(int archived) {
         this.archived = archived;
     }

     public String getPackageName() {
         return packageName;
     }

    public void setPackageName(String packageName) {
         this.packageName = packageName;
    }
}
