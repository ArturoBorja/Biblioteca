package com.example.biblioteca;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;

import java.util.List;

public class LibrosAdapter extends BaseAdapter {
    Context context;
    int layout;
    List<Libro> datos;

    public LibrosAdapter(Context context, int layout, List<Libro> datos) {
        this.context = context;
        this.layout = layout;
        this.datos = datos;
    }

    @Override
    public int getCount() {
        return datos.size();
    }

    @Override
    public Object getItem(int i) {
        return datos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(layout,null);
        TextView tv1 = view.findViewById(R.id.txt_item_libro);
        TextView tv2 = view.findViewById(R.id.txt_item_autor);
        TextView tv3 = view.findViewById(R.id.txt_item_isbn);
        tv1.setText(datos.get(i).titulo);
        tv2.setText(datos.get(i).autor);
        tv3.setText(datos.get(i).isbn);

        return view;
    }
}
