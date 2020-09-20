package com.user.storeslibrary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class ProfileActivity extends AppCompatActivity {

    private ImageView backIcon;
    private TextInputLayout name, email, depart, phno;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_profile);

        backIcon = findViewById(R.id.back_icon);
        name = findViewById(R.id.pname);
        email = findViewById(R.id.pmail);
        depart =findViewById(R.id.pdept);
        phno = findViewById(R.id.pnum);

        Objects.requireNonNull(name.getEditText()).setText(getIntent().getStringExtra("name"));
        Objects.requireNonNull(email.getEditText()).setText(getIntent().getStringExtra("mail"));
        Objects.requireNonNull(depart.getEditText()).setText("Computer");
        Objects.requireNonNull(phno.getEditText()).setText("1234567890");

        backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this, DashBoard.class);
                intent.putExtra("name", getIntent().getStringExtra("name"));
                intent.putExtra("mail", getIntent().getStringExtra("mail"));
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ProfileActivity.this, DashBoard.class);
        intent.putExtra("name", getIntent().getStringExtra("name"));
        intent.putExtra("mail", getIntent().getStringExtra("mail"));
        startActivity(intent);
        finish();
    }
}
