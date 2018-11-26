package com.example.saajan.dreamapp;

public class Request {
    boolean isFullfilled;
    private String fullfilledBy;
    private String patientName, name, photoURL, bloodGroup, description, contacts, date;
    private String email, noOfBagNeeded, reqID, reqDate;

    public Request() {
        ;
    }

    public Request(String patientName, String name, String photoURL, String bloodGroup,
                   String description, String contacts, String email, String noOfBagNeeded, String reqID, String reqDate) {
        this.patientName = patientName;
        this.name = name;
        this.photoURL = photoURL;
        this.bloodGroup = bloodGroup;
        this.description = description;
        this.contacts = contacts;
        this.email = email;
        this.noOfBagNeeded = noOfBagNeeded;
        this.reqID = reqID;
        this.reqDate = reqDate;

        isFullfilled = false;

    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNoOfBagNeeded() {
        return noOfBagNeeded;
    }

    public void setNoOfBagNeeded(String noOfBagNeeded) {
        this.noOfBagNeeded = noOfBagNeeded;
    }

    public String getReqID() {
        return reqID;
    }

    public void setReqID(String reqID) {
        this.reqID = reqID;
    }

    public String getReqDate() {
        return reqDate;
    }

    public void setReqDate(String reqDate) {
        this.reqDate = reqDate;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhotoURL() {
        return photoURL;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isFullfilled() {
        return isFullfilled;
    }

    public void setFullfilled(boolean fullfilled) {
        isFullfilled = fullfilled;
    }

    public String getFullfilledBy() {
        return fullfilledBy;
    }

    public void setFullfilledBy(String fullfilledByRoll) {
        this.fullfilledBy = fullfilledByRoll;
    }
}
