package com.xclone.ordereats;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.hbb20.CountryCodePicker;

public class LoginWithPhone extends AppCompatActivity {

    EditText num;
    CountryCodePicker cpp;
    FirebaseAuth Fauth;
    String number;
    TextView btnEmail,sendotp,signinemail, signup;
    ConstraintLayout bgImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_with_phone);

        AnimationDrawable animationDrawable = new AnimationDrawable();
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.bg2),6000);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.img2),6000);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.img3),6000);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.img4),6000);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.img5),6000);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.img6),6000);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.img7),6000);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.img8),6000);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.bg3),6000);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.img9),6000);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.img10),6000);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.img11),6000);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.img11),6000);

        animationDrawable.setOneShot(false);
        animationDrawable.setEnterFadeDuration(850);
        animationDrawable.setExitFadeDuration(1600);
        bgImage = findViewById(R.id.back5);
        bgImage.setBackgroundDrawable(animationDrawable);
        animationDrawable.start();

        num = (EditText)findViewById(R.id.number);
        sendotp = (TextView) findViewById(R.id.otp);
        cpp=(CountryCodePicker)findViewById(R.id.CountryCode);
        signinemail=(TextView) findViewById(R.id.btnEmail);
        signup = (TextView)findViewById(R.id.acsignup);

        Fauth = FirebaseAuth.getInstance();

        sendotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                number=num.getText().toString().trim();
                String Phonenum = cpp.getSelectedCountryCodeWithPlus()+number;
                Intent b = new Intent(LoginWithPhone.this,Chefsendotp.class);

                b.putExtra("Phonenum",Phonenum);
                startActivity(b);
                finish();

            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginWithPhone.this,UserResistration.class));
                finish();
            }
        });
        signinemail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginWithPhone.this,LoginWithEmail.class));
                finish();
            }
        });

    }
}