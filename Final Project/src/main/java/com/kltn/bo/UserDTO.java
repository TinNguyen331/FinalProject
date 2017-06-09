package com.kltn.bo;

import com.kltn.Util.AuthorityName;
import com.kltn.entities.Authority;
import com.kltn.entities.Order;
import com.kltn.entities.SpecialDayOfUser;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by TinNguyen on 6/10/17.
 */
public class UserDTO {
    private String id;
    private String userName;
    private String passWord;
    private String fullName;
    private Date dateOfBirth;
    private String phone;
    private List<String> address;
    private int activeIndexAddress =-1;
    private String email;

    public String getId() {
        return id==null?"":id;
    }

    public void setId(String id) {
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
}
