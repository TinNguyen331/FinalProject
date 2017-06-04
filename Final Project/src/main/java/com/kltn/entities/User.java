package com.kltn.entities;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * Created by TinNguyen on 5/5/17.
 */
@Document(collection = "user")
public class User {
    @Id
    private ObjectId id;
    private String userName;
    private String passWord;
    private List<Authority> authorities;
    private String fullName;
    private Date dateOfBirth;
    private String phone;
    private List<String> address;
    private int activeIndexAddress =-1;
    private String email;
    @NotNull
    private Boolean enabled;
    @NotNull
    private Date lastPasswordResetDate;
    @DBRef
    private List<Order> orderList;
    private List<SpecialDayOfUser> specialDayOfUsers;

    public String getId() {
        return id.toString();
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public List<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<Authority> authorities) {
        this.authorities = authorities;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<String> getAddress() {
        return address;
    }

    public void setAddress(List<String> address) {
        this.address = address;
    }

    public int getActiveIndexAddress() {
        return activeIndexAddress;
    }

    public void setActiveIndexAddress(int activeIndexAddress) {
        this.activeIndexAddress = activeIndexAddress;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Date getLastPasswordResetDate() {
        return lastPasswordResetDate;
    }

    public void setLastPasswordResetDate(Date lastPasswordResetDate) {
        this.lastPasswordResetDate = lastPasswordResetDate;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }

    public List<SpecialDayOfUser> getSpecialDayOfUsers() {
        return specialDayOfUsers;
    }

    public void setSpecialDayOfUsers(List<SpecialDayOfUser> specialDayOfUsers) {
        this.specialDayOfUsers = specialDayOfUsers;
    }
}
