package com.kidstart.kidstart.Presentation;

import android.app.Activity;
import android.text.TextUtils;

import com.google.firebase.auth.FirebaseAuth;

public class LoginController {
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private FireBaseAccesObject FBAO;

    public LoginController(Activity context) {
        FBAO = new FireBaseAccesObject(context);
    }

    public Boolean userLogin(String email, String password){
//        if(TextUtils.isEmpty(email)|| TextUtils.isEmpty(password)){
//            return false;
//        }
        return FBAO.validateLogin(email, password);
    }

}
