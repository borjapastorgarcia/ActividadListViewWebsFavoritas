package com.example.dm2.actividadlistviewwebsfavoritas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by DM2 on 13/10/2015.
 */
public class AdaptadorUrl extends ArrayAdapter<Web> {
    private Web[]datosWeb;
    public AdaptadorUrl(Context context, Web[]datosWeb) {
        super(context, R.layout.activity_main,datosWeb);
        this.datosWeb=datosWeb;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater=LayoutInflater.from(getContext());

        View item=inflater.inflate(R.layout.listwebs, null);

        TextView tvNombreWeb=(TextView)item.findViewById(R.id.TvTituloWeb);
        tvNombreWeb.setText(datosWeb[position].getTitulo());

        TextView tvUrlWEb=(TextView)item.findViewById(R.id.TvUrlWeb);
        tvUrlWEb.setText(datosWeb[position].getUrl());

        ImageView imWeb=(ImageView)item.findViewById(R.id.imIconWeb);
        imWeb.setImageResource(datosWeb[position].getImageIcon());

        return item;
    }
}
