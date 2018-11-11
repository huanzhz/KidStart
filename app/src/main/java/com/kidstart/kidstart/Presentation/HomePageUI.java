package com.kidstart.kidstart.Presentation;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.kidstart.kidstart.R;

/**
 * This is the main class
 * @author HuanZhang
 */
public class HomePageUI extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;

    public static final String MAIN_MESSAGE = "com.kidstart.kidstart.MAINMESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout = (DrawerLayout)findViewById(R.id.drawerLayout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.Open, R.string.Close);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final NavigationView navigationView = (NavigationView)findViewById(R.id.nav_view);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();

                if(id == R.id.login)
                {
                    Toast.makeText(HomePageUI.this, "MyProfile",Toast.LENGTH_SHORT);
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
                }
                else if(id == R.id.logout)
                {
                    Toast.makeText(HomePageUI.this, "EditProfile",Toast.LENGTH_SHORT);
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
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return actionBarDrawerToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }
}
