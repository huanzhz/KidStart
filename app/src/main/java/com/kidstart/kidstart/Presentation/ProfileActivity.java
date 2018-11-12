package com.kidstart.kidstart.Presentation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.kidstart.kidstart.R;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener{
    private FirebaseAuth firebaseAuth;
    private EditText editText;
    private Button buttonLogout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() == null){
            finish();
            startActivity(new Intent(this,LoginActivity.class));
        }

        FirebaseUser user = firebaseAuth.getCurrentUser();

        editText = (EditText)findViewById(R.id.editText);
        editText.setText("Welcome "+ user.getEmail());
        buttonLogout = (Button)findViewById(R.id.buttonLogout);

        buttonLogout.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if(view == buttonLogout){
            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(this,LoginActivity.class));
        }
    }
}
