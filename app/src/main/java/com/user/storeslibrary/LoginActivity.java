package com.user.storeslibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    TextInputLayout uName,uPass;
    boolean doubleBackToExitPressedOnce = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        uName = findViewById(R.id.username);
        uPass = findViewById(R.id.password);
        Button newUser = findViewById(R.id.newUser);
        Button btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = Objects.requireNonNull(uName.getEditText()).getText().toString();
                String userPass = Objects.requireNonNull(uPass.getEditText()).getText().toString();
                if (!ValidateUsername() | !ValidatePassword()) {
                    Toast.makeText(LoginActivity.this, "Can Not Login", Toast.LENGTH_SHORT).show();
                } else {
                    if (userName.equalsIgnoreCase("priti0011pal@gmail.com") | userName.equalsIgnoreCase("ektadas01@gmail.com")) {
                        if (userName.equalsIgnoreCase("priti0011pal@gmail.com") && userPass.equals("1234")) {
                            Intent intent = new Intent(LoginActivity.this, DashBoard.class);
                            intent.putExtra("name", "Priti Pal");
                            intent.putExtra("mail", ""+userName);
                            startActivity(intent);
                            finish();
                        } else if (userName.equalsIgnoreCase("ektadas01@gmail.com") && userPass.equals("hello1234")) {
                            Intent intent = new Intent(LoginActivity.this, DashBoard.class);
                            intent.putExtra("name", "Ekta Das");
                            intent.putExtra("mail", ""+userName);
                            startActivity(intent);
                            finish();
                        } else {
                            uPass.setError("Wrong Password!");
                        }
                    } else {
                        uName.setError("No Such Account Exist!");
                    }
                }
            }
        });

        newUser.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ShowToast")
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }

    private Boolean ValidateUsername(){
        String val = Objects.requireNonNull(uName.getEditText()).getText().toString();
        if(val.isEmpty()){
            uName.setError("Field Cannot be empty!");
            return false;
        }
        else {
            uName.setError(null);
            uName.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean ValidatePassword(){
        String val = Objects.requireNonNull(uPass.getEditText()).getText().toString();
        if(val.isEmpty()){
            uPass.setError("Password Cannot be empty!");
            return false;
        }
        else {
            uPass.setError(null);
            uPass.setErrorEnabled(false);
            return true;
        }
    }

}
