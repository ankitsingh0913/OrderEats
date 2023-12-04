package com.xclone.ordereats;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginWithEmail extends AppCompatActivity {
    public static String PREFS_NAME = "MyPrefsFile";
    TextInputLayout email,pass;
    TextView signInPhone,Signin,Forgotpassword , signup;
    FirebaseAuth Fauth;
    String emailid,pwd;

    ConstraintLayout bgImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_with_email);

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
        bgImage = findViewById(R.id.back6);
        bgImage.setBackgroundDrawable(animationDrawable);
        animationDrawable.start();

        try{

            email = (TextInputLayout)findViewById(R.id.Lemail);
            pass = (TextInputLayout)findViewById(R.id.Lpassword);
            Signin = (TextView) findViewById(R.id.button4);
            signup = (TextView) findViewById(R.id.textView3);
            Forgotpassword = (TextView)findViewById(R.id.forgot);
            signInPhone = (TextView)findViewById(R.id.btnPhone);

            Fauth = FirebaseAuth.getInstance();

            Signin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    emailid = email.getEditText().getText().toString().trim();
                    pwd = pass.getEditText().getText().toString().trim();

                    if(isValid()){

                        final ProgressDialog mDialog = new ProgressDialog(LoginWithEmail.this);
                        mDialog.setCanceledOnTouchOutside(false);
                        mDialog.setCancelable(false);
                        mDialog.setMessage("Sign In Please Wait.......");
                        mDialog.show();

                        Fauth.signInWithEmailAndPassword(emailid,pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if(task.isSuccessful()){
                                    mDialog.dismiss();

                                    if(Fauth.getCurrentUser().isEmailVerified()){
                                        SharedPreferences sharedPreferences = getSharedPreferences(LoginWithEmail.PREFS_NAME,0);
                                        SharedPreferences.Editor editor = sharedPreferences.edit();
                                        editor.putBoolean("hasLoggedIn",true);
                                        editor.commit();
                                        mDialog.dismiss();
                                        Toast.makeText(LoginWithEmail.this, "Congratulation! You Have Successfully Login", Toast.LENGTH_SHORT).show();
                                        Intent Z = new Intent(LoginWithEmail.this,IntroActivity.class);
                                        startActivity(Z);
                                        finish();

                                    }else{
                                        ReusableCodeForAll.ShowAlert(LoginWithEmail.this,"Verification Failed","You Have Not Verified Your Email");

                                    }
                                }else{
                                    mDialog.dismiss();
                                    ReusableCodeForAll.ShowAlert(LoginWithEmail.this,"Error",task.getException().getMessage());
                                }
                            }
                        });
                    }
                }
            });
            signup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(LoginWithEmail.this,UserResistration.class));
                    finish();
                }
            });
            Forgotpassword.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(LoginWithEmail.this,ChefForgotPassword.class));
                    finish();
                }
            });
            signInPhone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(LoginWithEmail.this,LoginWithPhone.class));
                    finish();
                }
            });
        }catch (Exception e){
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
        }

    }
    String emailpattern  = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    public boolean isValid(){

        email.setErrorEnabled(false);
        email.setError("");
        pass.setErrorEnabled(false);
        pass.setError("");

        boolean isvalid=false,isvalidemail=false,isvalidpassword=false;
        if(TextUtils.isEmpty(emailid)){
            email.setErrorEnabled(true);
            email.setError("Email is required");
        }else{
            if(emailid.matches(emailpattern)){
                isvalidemail=true;
            }else{
                email.setErrorEnabled(true);
                email.setError("Invalid Email Address");
            }
        }
        if(TextUtils.isEmpty(pwd)){

            pass.setErrorEnabled(true);
            pass.setError("Password is Required");
        }else{
            isvalidpassword=true;
        }
        isvalid=(isvalidemail && isvalidpassword)?true:false;
        return isvalid;

    }
}