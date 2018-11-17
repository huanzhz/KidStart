package com.kidstart.kidstart.Presentation;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.kidstart.kidstart.R;

/**
 * This class will display the register interface
 * @author JoonWoon
 */

public class RegisterUI extends AppCompatActivity implements  View.OnClickListener {

    private Button buttonRegister;
    private Button buttonLogin;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    private TextView emailErrorText;
    private TextView passwordErrorText;

    /**
     * Similar to constructor
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        firebaseAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(this);
        buttonRegister = (Button)findViewById(R.id.buttonRegister);
        buttonLogin = (Button)findViewById(R.id.buttonLogin);
        editTextEmail = (EditText)findViewById(R.id.editTextEmail);
        editTextPassword = (EditText)findViewById(R.id.editTextPassword);
        emailErrorText = (TextView)findViewById(R.id.textView);
        passwordErrorText = (TextView)findViewById(R.id.textView1);
        buttonRegister.setOnClickListener((View.OnClickListener) this);
        buttonLogin.setOnClickListener((View.OnClickListener) this);


    }

    /**
     * Validate and register user using email and password
     * @param
     */
    private void registerUser(){
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if(TextUtils.isEmpty(email)&& TextUtils.isEmpty(password)){
            emailErrorText.setText("Please enter email");
            passwordErrorText.setText("Please enter password");
            return;
        }
        if(TextUtils.isEmpty(email)){
            //email is empty
            //Toast.makeText(this, "Please enter email",Toast.LENGTH_SHORT).show();
            passwordErrorText.setText("");
            emailErrorText.setText("Please enter email");
            return;
        }
        if(password.length()<6){
            passwordErrorText.setText("Password is too short. Please enter at least 6 characters");
            return;
        }

        if(TextUtils.isEmpty(password)){
            //password is empty
            //Toast.makeText(this, "Please enter password",Toast.LENGTH_SHORT).show();
            emailErrorText.setText("");
            passwordErrorText.setText("Please enter password");
            return;
        }
        progressDialog.setMessage("Registering User...");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    //user is registered successfully
                    //we will start the profile activity here
                    //right now lets display a toast message
                    Toast.makeText(RegisterUI.this,"Registered Successfully",Toast.LENGTH_SHORT).show();
                    finish();
                    startActivity(new Intent(getApplicationContext(),LoginUI.class));
                }else{
                    Toast.makeText(RegisterUI.this,"Could not register.. Please try again",Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }
        });
    }

    /**
     * Triggered when buttonLogin or buttonRegister is pressed
     * @param view
     */
    @Override
    public void onClick(View view){
        if(view == buttonRegister){
            registerUser();
            return;
        }

        if(view == buttonLogin){
            startActivity(new Intent(this,LoginUI.class));
            return;
        }
    }

}
