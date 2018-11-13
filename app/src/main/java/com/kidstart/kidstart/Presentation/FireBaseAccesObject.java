package com.kidstart.kidstart.Presentation;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class FireBaseAccesObject {
    private Activity context;
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private Boolean success = false;

    public FireBaseAccesObject (Activity context) {
        this.context = context;
    }

    public Boolean validateLogin(String email, String password) {
        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(context, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                success = (task.isSuccessful());
            }

        });
        return success;
    }
}
