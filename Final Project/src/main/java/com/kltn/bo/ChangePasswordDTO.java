package com.kltn.bo;

/**
 * Created by TinNguyen on 6/20/17.
 */
public class ChangePasswordDTO {
    String oldPass;
    String newPass;
    String verifyNewPass;

    public String getOldPass() {
        return oldPass;
    }

    public void setOldPass(String oldPass) {
        this.oldPass = oldPass;
    }

    public String getNewPass() {
        return newPass;
    }

    public void setNewPass(String newPass) {
        this.newPass = newPass;
    }

    public String getVerifyNewPass() {
        return verifyNewPass;
    }

    public void setVerifyNewPass(String verifyNewPass) {
        this.verifyNewPass = verifyNewPass;
    }
}
