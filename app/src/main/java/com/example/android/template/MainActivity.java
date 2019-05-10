package com.example.android.template;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private ImageView imageBox;
    private static final int GALLERY_REQUEST =1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageBox = (ImageView) findViewById(R.id.imageBox);

        imageBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(imageBox.getDrawable() == null) {
                    addImage();
                }else{
                    Toast.makeText(getApplicationContext(),"You already have an image",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void addImage(){
        Intent photopicker = new Intent(Intent.ACTION_PICK);
        photopicker.setType("image/*");
        startActivityForResult(photopicker, GALLERY_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK)
            switch (requestCode){
                case GALLERY_REQUEST:
                    Uri selectedImage = data.getData();
                    try {
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImage);
                        imageBox.setImageBitmap(bitmap);
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                    break;
            }
    }
}
