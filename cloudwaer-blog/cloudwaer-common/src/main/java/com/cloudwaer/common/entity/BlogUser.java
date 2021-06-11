package com.cloudwaer.common.entity;


public class BlogUser {

    private long id;
    private String userAccount;
    private String userPassword;
    private String userShowname;
    private String userHadeimg;
    private String userBrief;
    private String userLabelCode;
    private String userIdeneity;
    private String userEmail;
    private long userPhone;

    @Override
    public String toString() {
        return "BlogUser{" +
                "id=" + id +
                ", userAccount='" + userAccount + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userShowname='" + userShowname + '\'' +
                ", userHadeimg='" + userHadeimg + '\'' +
                ", userBrief='" + userBrief + '\'' +
                ", userLabelCode='" + userLabelCode + '\'' +
                ", userIdeneity='" + userIdeneity + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userPhone=" + userPhone +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }


    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }


    public String getUserShowname() {
        return userShowname;
    }

    public void setUserShowname(String userShowname) {
        this.userShowname = userShowname;
    }


    public String getUserHadeimg() {
        return userHadeimg;
    }

    public void setUserHadeimg(String userHadeimg) {
        this.userHadeimg = userHadeimg;
    }


    public String getUserBrief() {
        return userBrief;
    }

    public void setUserBrief(String userBrief) {
        this.userBrief = userBrief;
    }


    public String getUserLabelCode() {
        return userLabelCode;
    }

    public void setUserLabelCode(String userLabelCode) {
        this.userLabelCode = userLabelCode;
    }


    public String getUserIdeneity() {
        return userIdeneity;
    }

    public void setUserIdeneity(String userIdeneity) {
        this.userIdeneity = userIdeneity;
    }


    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }


    public long getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(long userPhone) {
        this.userPhone = userPhone;
    }

}
