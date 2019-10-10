package com.example.biblioteca;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Libro extends RealmObject {
    @PrimaryKey
    int id;
    String titulo;
    int num_paginas;
    String isbn;
    String autor;
    RealmList<Ejemplar> ejemplars;
    public Libro(){
        ejemplars = new RealmList<>();
    }

    public Libro(String titulo, int num_paginas, String isbn, String autor) {
        this.id = RealmAplication.cod_libro.incrementAndGet();
        this.titulo = titulo;
        this.num_paginas = num_paginas;
        this.isbn = isbn;
        this.autor = autor;
        ejemplars = new RealmList<>();
    }
}
