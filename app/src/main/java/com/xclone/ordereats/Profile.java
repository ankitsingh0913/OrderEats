package com.xclone.ordereats;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Profile extends AppCompatActivity {

    TextView  UserEmail;
    LinearLayout Home, Setting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        SharedPreferences preferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String userName = preferences.getString("USERNAME", "User");
        String LastName = preferences.getString("Lastname", "Name");
        TextView firstname = findViewById(R.id.userFirstName);
        TextView lastname = findViewById(R.id.userLastName);
        firstname.setText(userName);
        lastname.setText(LastName);

        Navigation();
    }

    private void Navigation() {
        Home = findViewById(R.id.homeBtn);
        Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(Profile.this,MainActivity.class);
                startActivity(a);
            }
        });
        Setting = findViewById(R.id.settingBtn);
        Setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent b = new Intent(Profile.this,Settings.class);
                startActivity(b);
            }
        });
        FloatingActionButton floatingActionButton = findViewById(R.id.cartBtn);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Profile.this,CartListActivity.class));
            }
        });
        LinearLayout Support = findViewById(R.id.supportBtn);
        Support.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Profile.this, com.xclone.ordereats.Support.class));
            }
        });
    }
}