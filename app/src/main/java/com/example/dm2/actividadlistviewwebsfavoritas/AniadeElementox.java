package com.example.dm2.actividadlistviewwebsfavoritas;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class AniadeElementox extends AppCompatActivity {
    ImageView imEligeFotografia;
    Button btnGuardar;
    EditText etNombreWeb,etUrl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aniade_elementox);
        imEligeFotografia=(ImageView)findViewById(R.id.imElegirFotografia);
        imEligeFotografia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, 1);

            }
        });
        btnGuardar=(Button)findViewById(R.id.btnGuardar);
        etNombreWeb=(EditText)findViewById(R.id.etNombreWEb);
        etUrl=(EditText)findViewById(R.id.etUrl);
        String nombrePag=etNombreWeb.getText().toString();
        String url=etUrl.getText().toString();
        //mirar si estan llenos los edittext para pdoer crear el evento
        btnGuardar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i2=new Intent(AniadeElementox.this,MainActivity.class);

                setResult(RESULT_OK, i2);
                finish();
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

        switch(requestCode) {
            case 1:
                if(resultCode == RESULT_OK){
                    Uri selectedImage = imageReturnedIntent.getData();
                    InputStream imageStream = null;
                    try {
                        imageStream = getContentResolver().openInputStream(selectedImage);
                        Bitmap yourSelectedImage = BitmapFactory.decodeStream(imageStream);
                        imEligeFotografia.setImageBitmap(yourSelectedImage);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
        }
    }
}
