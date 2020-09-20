package com.user.storeslibrary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class SellBookActivity extends AppCompatActivity {

    private ImageView bookImg, backIco;
    private TextView bookName, bookPrice, bookCat, bookUploader, bookUploadMail, bookUploadMob;
    private StorageReference mStorageRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sell);

        backIco = findViewById(R.id.back_icon2);
        bookImg = findViewById(R.id.bookIg);
        bookName = findViewById(R.id.bookNam);
        bookPrice = findViewById(R.id.bookPric);
        bookUploader = findViewById(R.id.bookUpldr);
        bookCat =  findViewById(R.id.bookCat);
        bookUploadMail = findViewById(R.id.bookUpldMail);
        bookUploadMob = findViewById(R.id.bookUpldMob);
        mStorageRef = FirebaseStorage.getInstance().getReference().child("Images/Book.jpg");

        readBookImg();
        readBook();

        backIco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SellBookActivity.this, DashBoard.class);
                intent.putExtra("name", getIntent().getStringExtra("name"));
                intent.putExtra("mail", getIntent().getStringExtra("mail"));
                startActivity(intent);
                finish();
            }
        });



    }

    private void readBookImg() {
        try {
            final File file = File.createTempFile("image","jpg");
            mStorageRef.getFile(file).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                    bookImg.setImageBitmap(bitmap);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readBook() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Books").child("Book");
        reference.addValueEventListener(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()) {
                    String name = Objects.requireNonNull(dataSnapshot.child("bookName").getValue()).toString();
                    String price = Objects.requireNonNull(dataSnapshot.child("bookPrice").getValue()).toString();
                    String category = Objects.requireNonNull(dataSnapshot.child("bookCategory").getValue()).toString();
                    String uploadedBy = Objects.requireNonNull(dataSnapshot.child("bookUploader").getValue()).toString();

                    bookName.setText(name);
                    bookPrice.setText(price);
                    bookCat.setText(category);
                    bookUploader.setText(uploadedBy);
                    if(uploadedBy.equals("Ekta Das")){
                        bookUploadMail.setText("ektadas01@gmail.com");
                        bookUploadMob.setText("7218772187");
                    } else if(uploadedBy.equals("Priti Pal")){
                        bookUploadMail.setText("priti0011pal@gmail.com");
                        bookUploadMob.setText("9552495524");
                    }
                }
                else {
                    Toast.makeText(SellBookActivity.this,"No Such Subtree exists",Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(SellBookActivity.this, DashBoard.class);
        intent.putExtra("name", getIntent().getStringExtra("name"));
        intent.putExtra("mail", getIntent().getStringExtra("mail"));
        startActivity(intent);
        finish();
    }
}
