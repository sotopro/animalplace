package com.example.animalplace;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SupportActivity extends AppCompatActivity {

    private static final int PERMISSION_CODE = 1200;
    private static final int CAPTURE_CODE = 1000;
    private Button takePhotoButton;
    private Button goBackButton;
    private ImageView supportImage;
    private Uri imageUri;
    private EditText supportEditText;
    private Button supportButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_support);

        takePhotoButton = findViewById(R.id.take_photo_button);
        supportImage = findViewById(R.id.support_image);
        goBackButton = findViewById(R.id.go_back_button);
        supportEditText = findViewById(R.id.support_edit_text);
        supportButton = findViewById(R.id.support_button);


        takePhotoButton.setOnClickListener(v -> {
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                if(checkSelfPermission(Manifest.permission.CAMERA) ==
                        PackageManager.PERMISSION_DENIED ||
                        checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) ==
                                PackageManager.PERMISSION_DENIED){
                    String[] permission = {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
                    requestPermissions(permission, PERMISSION_CODE);
                }
                else {
                    openCamera();
                }
            }
            else {
                openCamera();
            }
        });

        supportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(supportEditText.getText().toString().isEmpty() || imageUri == null){
                    Toast.makeText(SupportActivity.this, "Please enter a message", Toast.LENGTH_SHORT).show();
                }
                else {
                    postAnimal(supportEditText.getText().toString(), imageUri.toString());
                    Intent intent = new Intent(SupportActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });

        goBackButton.setOnClickListener(v -> {
            Intent intent = new Intent(SupportActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }

    private void postAnimal(String message, String imageUri){
        SupportModel supportModel = new SupportModel(imageUri, message);
        RetrofitClient.getRetrofitClient().postAnimal(supportModel).enqueue(new Callback<SupportModel>() {
            @Override
            public void onResponse(Call<SupportModel> call, Response<SupportModel> response) {
                if(response.isSuccessful()){
                    Toast.makeText(SupportActivity.this, "Animal added", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SupportModel> call, Throwable t) {
                Toast.makeText(SupportActivity.this, "Error adding animal", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String [] permissions, @NonNull int [] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode){
            case PERMISSION_CODE:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    openCamera();
                } else {
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
                }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK) {
            supportImage.setImageURI(imageUri);
        }
    }

    private void openCamera() {
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, "New Picture");
        values.put(MediaStore.Images.Media.DESCRIPTION, "From the Camera");
        imageUri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);

        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        startActivityForResult(cameraIntent, CAPTURE_CODE);
    }
}