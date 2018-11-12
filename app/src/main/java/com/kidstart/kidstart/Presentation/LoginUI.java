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

public class LoginUI extends AppCompatActivity implements View.OnClickListener
{

    private Button buttonLogin;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private Button buttonRegister;
    private TextView emailErrorText;
    private TextView passwordErrorText;

    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        buttonLogin = (Button)findViewById(R.id.buttonLogin);
        buttonRegister = (Button)findViewById(R.id.buttonRegister);
        editTextEmail = (EditText)findViewById(R.id.editTextEmail);
        editTextPassword = (EditText)findViewById(R.id.editTextPassword);
        progressDialog = new ProgressDialog(this);
        firebaseAuth = FirebaseAuth.getInstance();
        emailErrorText = (TextView)findViewById(R.id.textView);
        passwordErrorText = (TextView)findViewById(R.id.textView1);


        //if user is already logged in
        //if(firebaseAuth.getCurrentUser()!=null){
        //start profile activity
        //add the activity to open from huanzhang
        //finish();
        //  startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
        //}

        buttonRegister.setOnClickListener((View.OnClickListener)this);
        buttonLogin.setOnClickListener((View.OnClickListener)this);

    }


    private void userLogin(){
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

        progressDialog.setMessage("Logging In, Please wait...");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressDialog.dismiss();
                if(task.isSuccessful()){
                    //start the profile activity

                    Intent intent = new Intent(getApplicationContext(), HomePageUI.class);
                    startActivityForResult(intent, 3);
                    finish();
                }else{
                    Toast.makeText(getApplicationContext(), "Invalid Login Credentials",Toast.LENGTH_SHORT).show();
                }
            }

        });
    }

    @Override
    public void onClick(View view){
        if(view == buttonLogin){
            userLogin();
        }
        if(view == buttonRegister){
            finish();
            startActivity(new Intent(this,RegisterUI.class));
        }
    }
}
