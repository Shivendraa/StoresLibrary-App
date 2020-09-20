package com.user.storeslibrary;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.Objects;

public class AddBookActivity extends AppCompatActivity {
    private static final int PICK_IMAGE_REQUEST = 234;
    private StorageReference mStorageRef;
    private Uri filePath;
    private ImageView backIco,imgBook;
    private TextInputLayout bookName, bookPrice, bookCategory;
    private Button uploadBook;
    private FirebaseDatabase rootNode;
    private DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_add);

        backIco = findViewById(R.id.back_icon1);
        imgBook = findViewById(R.id.pickImage);
        bookName = findViewById(R.id.aBookName);
        bookPrice = findViewById(R.id.aPrice);
        bookCategory = findViewById(R.id.aCategory);
        uploadBook = findViewById(R.id.uploadBook);
        mStorageRef = FirebaseStorage.getInstance().getReference();
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("users");

        backIco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddBookActivity.this, DashBoard.class);
                intent.putExtra("name", getIntent().getStringExtra("name"));
                intent.putExtra("mail", getIntent().getStringExtra("mail"));
                startActivity(intent);
                finish();
            }
        });

        imgBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Select an Image"),PICK_IMAGE_REQUEST);
            }
        });

        uploadBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("Books");
                String name = Objects.requireNonNull(bookName.getEditText()).getText().toString();
                String price = Objects.requireNonNull(bookPrice.getEditText()).getText().toString();
                String category = Objects.requireNonNull(bookCategory.getEditText()).getText().toString();
                String uploadedBy = getIntent().getStringExtra("name");

                if(name.equals("") | price.equals("") | category.equals("")){
                    Toast.makeText(AddBookActivity.this,"Kindly Fill all details and upload image!",Toast.LENGTH_LONG).show();
                }
                else {
                    uploadFile();
                    UserHelperClass user = new UserHelperClass(name,price,category,uploadedBy);
                    reference.child("Book").setValue(user);
                    Toast.makeText(AddBookActivity.this,"Book Uploaded Successfully",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(AddBookActivity.this, AddBookActivity.class);
                    intent.putExtra("name", getIntent().getStringExtra("name"));
                    intent.putExtra("mail", getIntent().getStringExtra("mail"));
                    startActivity(intent);
                    finish();
                }
            }
        });

    }

    private void uploadFile() {
        if(filePath != null){
            StorageReference riversRef = mStorageRef.child("Images/Book.jpg");
            riversRef.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                        }
                    });
        }
        else {
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data.getData() != null) {
            filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),filePath);
                imgBook.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(AddBookActivity.this, DashBoard.class);
        intent.putExtra("name", getIntent().getStringExtra("name"));
        intent.putExtra("mail", getIntent().getStringExtra("mail"));
        startActivity(intent);
        finish();
    }
}
