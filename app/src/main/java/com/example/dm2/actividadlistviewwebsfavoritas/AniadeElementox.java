package com.example.dm2.actividadlistviewwebsfavoritas;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class AniadeElementox extends AppCompatActivity {
    ImageView imEligeFotografia;
    Button btnGuardar;
    EditText etNombreWeb,etUrl;
    String nombrePag, url;
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
        //mirar si estan llenos los edittext para pdoer crear el evento
            btnGuardar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    etNombreWeb=(EditText)findViewById(R.id.etNombreWeb);
                    etUrl=(EditText)findViewById(R.id.editUrl);
                    nombrePag=etNombreWeb.getText().toString();
                    url=etUrl.getText().toString();
                   // Toast.makeText(v.getContext(),"Rellena todos los campos para poder añadir el contacto. nombrePAg: '"+nombrePag+"' url-->'"+url+"' .",Toast.LENGTH_SHORT).show();
                    if(nombrePag.equals("")||url.equals("")) {
                        //   Toast.makeText(v.getContext(),"Rellena todos los campos para poder añadir el contacto. ",Toast.LENGTH_SHORT).show();
                    }else{
                     //   Toast.makeText(v.getContext(),"Llega a metodo onclick",Toast.LENGTH_SHORT).show();
                        Intent i2 = new Intent(AniadeElementox.this, MainActivity.class);
                        i2.putExtra("url", url);
                        Uri bmpUri=getLocalBitmapUri(imEligeFotografia);
                        Toast.makeText(v.getContext(),"La direccion de la imagen es: "+bmpUri.getPath(),Toast.LENGTH_SHORT).show();
                       // Log.e("Path de la imagen",bmpUri.getPath());
                        i2.putExtra("direccionImagen",bmpUri.getPath());
                        i2.putExtra("nombrePagina",nombrePag);
                        setResult(RESULT_OK, i2);
                        finish();
                    }
                }
            });
    }
    public Uri getLocalBitmapUri(ImageView imageView) {
        // Extract Bitmap from ImageView drawable
        Drawable drawable = imageView.getDrawable();
        Bitmap bmp = null;
        if (drawable instanceof BitmapDrawable){
            bmp = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        } else {
            return null;
        }
        // Store image to default external storage directory
        Uri bmpUri = null;
        try {
            File file =  new File(Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_DOWNLOADS), "share_image_" + System.currentTimeMillis() + ".png");
            file.getParentFile().mkdirs();
            FileOutputStream out = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.PNG, 90, out);
            out.close();
            bmpUri = Uri.fromFile(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bmpUri;
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
