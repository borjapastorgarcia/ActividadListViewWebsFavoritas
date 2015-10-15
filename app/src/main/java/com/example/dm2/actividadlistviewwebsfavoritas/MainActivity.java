package com.example.dm2.actividadlistviewwebsfavoritas;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listaWebs;
    Web[]datosWeb;
    ImageButton fab;
    private ArrayList<Web>arrDatos=new ArrayList<Web>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fab=(ImageButton)findViewById(R.id.imageButton);
        fab.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                //crear nueva Activity para añadir nuevo boton a la lista
                Intent intent = new Intent(MainActivity.this, AniadeElementox.class);
                startActivityForResult(intent, 1234);

            }
        });
        actualizaListView(null);
    }
    @Override
    protected void onActivityResult (int requestCode,int resultCode, Intent data) {
        if (requestCode==1234 && resultCode==RESULT_OK){
            Toast.makeText(this,"Ha llegado resultado. ",Toast.LENGTH_SHORT).show();
            Bundle extras = data.getExtras();
            String urlLeida=extras.getString("url");
            String nombrePAgina=extras.getString("nombrePag");
            String direccionImagen=extras.getString("direccionImagen");
            File imgFile=new File(direccionImagen);
            Bitmap myBitMap= BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            Log.e("VAlores que llegan","NombnrePaguna"+nombrePAgina+",url-->"+urlLeida+"Path de la imagen-->"+direccionImagen+",, Bitmaps id-->"+myBitMap.getGenerationId());
            actualizaListView(new Web(nombrePAgina, urlLeida, myBitMap.getGenerationId()));
        }
    }


    public void actualizaListView(Web w){
        if(w!=null)
            arrDatos.add(w);

        arrDatos.add(new Web("Google","http://www.google.es",R.drawable.ic_google));
        arrDatos.add(new Web("Bing","http://www.bing.es",R.drawable.ic_bing));
        arrDatos.add(new Web("Yahoo","http://www.yahoo.es",R.drawable.ic_yahoo));
        arrDatos.add(new Web("Marca","http://www.marca.es",R.drawable.ic_marca));
        arrDatos.add(new Web("El Correo","http://www.elcorreo.es",R.drawable.ic_elcorreo));
        arrDatos.add(new Web("Google","http://www.google.es",R.drawable.ic_google));
        arrDatos.add(new Web("Bing","http://www.bing.es",R.drawable.ic_bing));
        arrDatos.add(new Web("Yahoo","http://www.yahoo.es",R.drawable.ic_yahoo));
        arrDatos.add(new Web("Marca","http://www.marca.es",R.drawable.ic_marca));
        arrDatos.add(new Web("El Correo","http://www.elcorreo.es",R.drawable.ic_elcorreo));
        arrDatos.add(new Web("Google","http://www.google.es",R.drawable.ic_google));
        arrDatos.add(new Web("Bing","http://www.bing.es",R.drawable.ic_bing));
        arrDatos.add(new Web("Yahoo","http://www.yahoo.es",R.drawable.ic_yahoo));
        arrDatos.add(new Web("Marca","http://www.marca.es",R.drawable.ic_marca));
        arrDatos.add(new Web("El Correo","http://www.elcorreo.es",R.drawable.ic_elcorreo));
        datosWeb=new Web[arrDatos.size()];
        for(int i=0;i<arrDatos.size();i++)
            datosWeb[i]=arrDatos.get(i);






        AdaptadorUrl adaptador=new AdaptadorUrl(this,datosWeb);
        listaWebs=(ListView)findViewById(R.id.ListViewWebs);
        listaWebs.setAdapter(adaptador);
        listaWebs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Uri uri= Uri.parse(datosWeb[position].getUrl());
                Intent intent=new Intent(Intent.ACTION_VIEW,uri);
                startActivity(intent);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
