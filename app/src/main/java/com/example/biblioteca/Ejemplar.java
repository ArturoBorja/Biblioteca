package com.example.biblioteca;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Ejemplar extends RealmObject {

    @PrimaryKey
    int id;
    String estado;
    String observacion;
    String ubicacion;

    public Ejemplar() {
    }

    public Ejemplar(String estado, String observacion, String ubicacion) {
        this.id = RealmAplication.cod_libro.incrementAndGet();
        this.estado = estado;
        this.observacion = observacion;
        this.ubicacion = ubicacion;
    }
}
