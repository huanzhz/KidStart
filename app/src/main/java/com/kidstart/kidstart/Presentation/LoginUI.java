package com.kidstart.kidstart.Presentation;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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

    private LoginController loginController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        buttonLogin = (Button)findViewById(R.id.buttonLogin);
        buttonRegister = (Button)findViewById(R.id.buttonRegister);
        editTextEmail = (EditText)findViewById(R.id.editTextEmail);
        editTextPassword = (EditText)findViewById(R.id.editTextPassword);
        progressDialog = new ProgressDialog(this);
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

        loginController = new LoginController(this);

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

        if(TextUtils.isEmpty(password)){
            //password is empty
            //Toast.makeText(this, "Please enter password",Toast.LENGTH_SHORT).show();
            emailErrorText.setText("");
            passwordErrorText.setText("Please enter password");
            return;
        }

        progressDialog.setMessage("Logging In, Please wait...");
        progressDialog.show();

        Boolean success = loginController.userLogin(email,password);

        if(!success){
            emailErrorText.setText("Invalid Login Credentials! Please Try Again!");
            return;
        } else {
            Intent intent = new Intent(getApplicationContext(), HomePageUI.class);
            startActivityForResult(intent, 3);
            finish();
        }


        progressDialog.dismiss();
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
