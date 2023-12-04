package com.xclone.ordereats;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.TextView;

public class ChooseOne extends AppCompatActivity {

    TextView chef,Customer, DeliveryPerson;
    Intent intent;
    String type;
    ConstraintLayout bgImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_one);

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

        bgImage = findViewById(R.id.back3);
        bgImage.setBackgroundDrawable(animationDrawable);
        animationDrawable.start();

        intent = getIntent();
        type = intent.getStringExtra("Home").toString().trim();

        chef = (TextView) findViewById(R.id.chef);
        DeliveryPerson = (TextView) findViewById(R.id.delivery);
        Customer = (TextView) findViewById(R.id.customer);

        Customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Chef = new Intent(ChooseOne.this,IntroActivity.class);
                Chef.putExtra("Home","Phone");
                startActivity(Chef);
                finish();
            }
        });
    }


}