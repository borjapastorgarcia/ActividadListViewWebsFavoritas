package com.example.dm2.actividadlistviewwebsfavoritas;

import android.widget.ImageView;

/**
 * Created by DM2 on 13/10/2015.
 */
public class Web {
    private String titulo;
    private String url;
    private int imageIcon;

    public Web(String titulo, String url, int imageIcon) {
        this.titulo = titulo;
        this.url = url;
        this.imageIcon = imageIcon;
    }

    public int getImageIcon() {
        return imageIcon;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getUrl() {
        return url;
    }
}
