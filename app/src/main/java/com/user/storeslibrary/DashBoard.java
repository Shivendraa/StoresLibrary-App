package com.user.storeslibrary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class DashBoard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    private ImageView menuIcon;
    boolean doubleBackToExitPressedOnce = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_dashboard);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        menuIcon = findViewById(R.id.menu_icon);
        navigationDrawer();
    }
    private void navigationDrawer() {
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);

        menuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(drawerLayout.isDrawerVisible(GravityCompat.START))
                    drawerLayout.closeDrawer(GravityCompat.START);
                else
                    drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }
    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerVisible(GravityCompat.START))
            drawerLayout.closeDrawer(GravityCompat.START);
        else{
            if (doubleBackToExitPressedOnce) {
                super.onBackPressed();
                return;
            }

            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, "Please click BACK again to exit. \nYou will be logged out automatically.", Toast.LENGTH_LONG).show();

            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    doubleBackToExitPressedOnce=false;
                }
            }, 2000);
        }
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.nav_logout){
            Intent intent = new Intent(DashBoard.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
        else if(id == R.id.nav_profile){
            Intent intent = new Intent(DashBoard.this, ProfileActivity.class);
            intent.putExtra("name", getIntent().getStringExtra("name"));
            intent.putExtra("mail", getIntent().getStringExtra("mail"));
            startActivity(intent);
            finish();
        }
        else if(id == R.id.nav_home){
            Intent intent = new Intent(DashBoard.this, DashBoard.class);
            intent.putExtra("name", getIntent().getStringExtra("name"));
            intent.putExtra("mail", getIntent().getStringExtra("mail"));
            startActivity(intent);
            finish();
        }
        else if(id == R.id.nav_addBook){
            Intent intent = new Intent(DashBoard.this, AddBookActivity.class);
            intent.putExtra("name", getIntent().getStringExtra("name"));
            intent.putExtra("mail", getIntent().getStringExtra("mail"));
            startActivity(intent);
            finish();
        }
        else if (id == R.id.nav_buyBook){
            Intent intent = new Intent(DashBoard.this, SellBookActivity.class);
            intent.putExtra("name", getIntent().getStringExtra("name"));
            intent.putExtra("mail", getIntent().getStringExtra("mail"));
            startActivity(intent);
            finish();
        }
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

}