package com.kidstart.kidstart.Presentation;

import android.app.ProgressDialog;
import android.content.Intent;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.kidstart.kidstart.R;

import org.w3c.dom.Text;

public class ChangePasswordUI extends AppCompatActivity implements View.OnClickListener {


    private TextView title;
    private EditText edittextpassword;
    private TextView errormessage;
    private Button button;
    private ProgressDialog progressDialog;
    FirebaseAuth firebaseAuth;
    private EditText edittextcurrentpassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        firebaseAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_change_password_ui);
        title = (TextView)findViewById(R.id.textView);
        button = (Button)findViewById(R.id.buttonChangePassword);
        errormessage = (TextView)findViewById(R.id.textView1);
        edittextpassword = (EditText)findViewById(R.id.editTextPassword);
        title.setText("Change Password for: " +firebaseAuth.getCurrentUser().getEmail());
        progressDialog = new ProgressDialog(this);
        edittextcurrentpassword = (EditText)findViewById(R.id.editTextCurrentPassword);

        button.setOnClickListener((View.OnClickListener)this);
    }

    @Override
    public void onClick(View v) {
        if(v == button){
            String password = edittextpassword.getText().toString().trim();
            String currentpassword = edittextcurrentpassword.getText().toString().trim();

            if(TextUtils.isEmpty(password)){

                errormessage.setText("Please enter password");
                return;
            }

            if(password.length()<6){
                errormessage.setText("Password is too short. Please enter at least 6 characters");
                return;
            }

            if(TextUtils.isEmpty(password)){
                //password is empty
                //Toast.makeText(this, "Please enter password",Toast.LENGTH_SHORT).show();
                errormessage.setText("Please enter password");
                return;
            }

/*            FirebaseUser user = firebaseAuth.getCurrentUser();
            AuthCredential credential = EmailAuthProvider.getCredential(user.getEmail(),currentpassword);

            user.reauthenticate(credential).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(getApplicationContext(),"user reauthenticated",Toast.LENGTH_SHORT).show();
                        return;
                    }else{
                        Toast.makeText(getApplicationContext(),"current password invalid",Toast.LENGTH_SHORT).show();
                        return;
                    }

                }
            });

            progressDialog.setMessage("Changing Password, Please wait...");
            progressDialog.show();


            firebaseAuth.getCurrentUser().updatePassword(password).addOnCompleteListener(this, new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    progressDialog.dismiss();
                    if(task.isSuccessful()){
                        //start the profile activity

                        Intent intent = new Intent(getApplicationContext(), HomePageUI.class);
                        startActivityForResult(intent, 3);
                        finish();
                    }else{
                        Toast.makeText(getApplicationContext(), "Unable to change password, Please try again!",Toast.LENGTH_SHORT).show();
                    }
                }
            });*/
        }
    }
}
