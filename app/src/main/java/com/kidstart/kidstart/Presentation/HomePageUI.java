package com.kidstart.kidstart.Presentation;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.kidstart.kidstart.R;

import org.w3c.dom.Text;

/**
 * This is the main class
 * @author HuanZhang
 */
public class HomePageUI extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private FirebaseAuth firebaseAuth;
    public static final String MAIN_MESSAGE = "com.kidstart.kidstart.MAINMESSAGE";
    private AlertDialog.Builder builder;
    private Button button;
    private NavigationView navigationView;
    private TextView loggedintext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout = (DrawerLayout)findViewById(R.id.drawerLayout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.Open, R.string.Close);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        firebaseAuth = FirebaseAuth.getInstance();
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        loggedintext = (TextView)findViewById(R.id.loggedintextfield);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        navigationView = (NavigationView)findViewById(R.id.nav_view);

        if(firebaseAuth.getCurrentUser()!=null){
            navigationView.getMenu().findItem(R.id.login).setVisible(false);
            loggedintext.setVisibility(View.VISIBLE);
            loggedintext.setText("Logged in as: "+firebaseAuth.getCurrentUser().getEmail());
            navigationView.getMenu().findItem(R.id.displayUser).setVisible(true);
            navigationView.getMenu().findItem(R.id.changepassword).setVisible(true);
            navigationView.getMenu().findItem(R.id.displayUser).setTitle(firebaseAuth.getCurrentUser().getEmail());
            navigationView.getMenu().findItem(R.id.logout).setVisible(true);
            navigationView.getMenu().findItem(R.id.changelanguage).setVisible(false);
            navigationView.getMenu().findItem(R.id.displayfavorites).setVisible(true);
        }else{
            loggedintext.setVisibility(View.INVISIBLE);
            navigationView.getMenu().findItem(R.id.logout).setVisible(false);
            navigationView.getMenu().findItem(R.id.displayUser).setVisible(false);
            navigationView.getMenu().findItem(R.id.login).setVisible(true);
            navigationView.getMenu().findItem(R.id.changepassword).setVisible(false);
            navigationView.getMenu().findItem(R.id.changelanguage).setVisible(false);
            navigationView.getMenu().findItem(R.id.displayfavorites).setVisible(false);
        }
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();

                if(id == R.id.login)
                {
                    Toast.makeText(HomePageUI.this, "MyProfile",Toast.LENGTH_SHORT);
                    if (firebaseAuth.getCurrentUser()==null) {
//                        navigationView.getMenu().findItem(R.id.login).setVisible(false);
                        startActivity(new Intent(getApplicationContext(), LoginUI.class));
                    } else {
                        Toast.makeText(HomePageUI.this, "You are already logined!", Toast.LENGTH_SHORT).show();
                    }
                }
                else if(id == R.id.displayfavorites)
                {
                    Toast.makeText(HomePageUI.this, "Settings",Toast.LENGTH_SHORT);
                }
                else if(id == R.id.changelanguage)
                {
                    Toast.makeText(HomePageUI.this, "EditProfile",Toast.LENGTH_SHORT);
                }
                else if(id == R.id.changepassword)
                {
                    Toast.makeText(HomePageUI.this, "EditProfile",Toast.LENGTH_SHORT);
                    startActivity(new Intent(getApplicationContext(), ChangePasswordUI.class));
                }
                else if(id == R.id.logout)
                {
                    /*builder = new AlertDialog.Builder(getApplicationContext());
                    builder.setTitle("Logout");
                    builder.setMessage("You will be returned to the main page");

                    builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // User clicked OK button
                            firebaseAuth.signOut();
                            finish();
                            startActivity(new Intent(getApplicationContext(),LoginUI.class));
                        }
                    });
                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // User cancelled the dialog
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();*/
                    firebaseAuth.signOut();
                    finish();
                    startActivity(new Intent(getApplicationContext(),HomePageUI.class));
                }

                return false;
            }
        });
    }

    public void displayResultView(View view){

        Intent intent = new Intent(this, DisplayResultUI.class);
        EditText editText = (EditText) findViewById(R.id.editText);
        String message = editText.getText().toString();
        message = message.toUpperCase();
        intent.putExtra(MAIN_MESSAGE, message);
        startActivityForResult(intent, 0);
    }

   /* @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 3) {
            if(firebaseAuth.getCurrentUser()!=null){
                Toast.makeText(this,"login set visibility to false",Toast.LENGTH_SHORT).show();
                navigationView.getMenu().findItem(R.id.login).setVisible(false);
            }
        }
    }*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return actionBarDrawerToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }
}
