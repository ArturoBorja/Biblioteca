package com.example.biblioteca;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

public class MainActivity extends AppCompatActivity {
    Realm realm;
    ListView lv_main_libros;
    RealmResults<Libro> libros;
    LibrosAdapter adapter;
    FloatingActionButton fab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv_main_libros = findViewById(R.id.lv_main_libros);
        fab = findViewById(R.id.floatingActionButton);
        realm = Realm.getDefaultInstance();
        //.sort("id", Sort.DESCENDING) permite ordenar la lista de datos para mostrarla de manera mas adecuada
        libros = realm.where(Libro.class).findAll().sort("id", Sort.DESCENDING);

        adapter = new LibrosAdapter(MainActivity.this, R.layout.libro_item,libros);
        lv_main_libros.setAdapter(adapter);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CrearLibro();
                adapter.notifyDataSetChanged();
            }
        });

    }
    void CrearLibro(){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("CREAR LIBRO");
        LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
        View v = inflater.inflate(R.layout.form_libro,null);
        final EditText titulo= v.findViewById(R.id.ext_form_libro);
        final EditText paginas = v.findViewById(R.id.ext_form_paginas);
        final EditText isbn = v.findViewById(R.id.ext_form_isbn);
        final EditText autor = v.findViewById(R.id.ext_form_autor);

        builder.setView(v);
        builder.setPositiveButton("CREAR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                realm.beginTransaction();
                Libro l = new Libro(titulo.getText().toString(),
                        Integer.parseInt(paginas.getText().toString()),
                        isbn.getText().toString(),autor.getText().toString());
                realm.copyToRealm(l);
                realm.commitTransaction();
            }
        });
        builder.setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.show();
    }
}
