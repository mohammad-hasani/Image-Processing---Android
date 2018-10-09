package com.image.processing.imageprocessing;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.image.processing.Struct.CascadeStruct;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 19/04/2018.
 */

public class MainPage extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_page);

        Button btnCamera = (Button) findViewById(R.id.btn_camera);
        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainPage.this, Camera.class);
                startActivity(intent);
            }
        });

        Button btnGallery = (Button) findViewById(R.id.btn_gallery);
        btnGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainPage.this, Gallery.class);
                startActivity(intent);
            }
        });

        Button btnDetection = (Button) findViewById(R.id.btn_detection);
        btnDetection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainPage.this, Detection.class);
                startActivity(intent);
            }
        });

        Button btnFaces = (Button) findViewById(R.id.btn_faces);
        btnFaces.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainPage.this, Faces.class);
                startActivity(intent);
            }
        });
    }
}
