package com.xclone.ordereats;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class registration extends AppCompatActivity {


    TextView signInEmail, signInPhone, signUp;
    ImageView bgImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        final Animation ZoomIn = AnimationUtils.loadAnimation(this,R.anim.zoomin);
        final Animation ZoomOut = AnimationUtils.loadAnimation(this,R.anim.zoomout);

        bgImage=findViewById(R.id.back2);
        bgImage.setAnimation(ZoomIn);
        bgImage.setAnimation(ZoomOut);

        ZoomOut.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                bgImage.startAnimation(ZoomIn);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        ZoomIn.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                bgImage.startAnimation(ZoomOut);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        signInEmail = (TextView) findViewById(R.id.signInwithEmail);
        signInPhone = (TextView) findViewById(R.id.signInwithPhone);
        signUp = (TextView) findViewById(R.id.signup);

        signInEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signemail = new Intent(registration.this, LoginWithEmail.class);
                signemail.putExtra("Home","Email");
                startActivity(signemail);
                finish();
            }
        });
        signInPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signphone = new Intent(registration.this,LoginWithPhone.class);
                signphone.putExtra("Home","Phone");
                startActivity(signphone);
                finish();
            }
        });
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signup = new Intent(registration.this,UserResistration.class);
                signup.putExtra("Home","Phone");
                startActivity(signup);
                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.gc();
    }
}