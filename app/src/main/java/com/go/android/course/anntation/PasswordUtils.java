package com.go.android.course.anntation;

/**
 * Created by go on 2018/2/1.
 */

public class PasswordUtils {
    @UserCase(id = 47, description = "password must contain one number")
    public boolean validatePassword(String password){
        return true;
    }
    @UserCase(id = 48)
    public String encryptPassword(String password){
        return "encryptPassword()";
    }
    @UserCase(id = 49, description = "new password can't equal previously used ones")
    public boolean checkouForNewPassword(){
        return false;
    }

}
