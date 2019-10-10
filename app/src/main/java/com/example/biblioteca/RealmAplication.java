package com.example.biblioteca;

import android.app.Application;

import java.util.concurrent.atomic.AtomicInteger;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;
import io.realm.RealmResults;

public class RealmAplication extends Application {
    public static AtomicInteger cod_libro;
    public static AtomicInteger cod_ejemplar;

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        RealmConfiguration configuration =new  RealmConfiguration
                .Builder().deleteRealmIfMigrationNeeded().build();
        Realm.setDefaultConfiguration(configuration);
        Realm realm = Realm.getDefaultInstance();
        cod_libro=ObtenerId(realm, Libro.class);
        cod_ejemplar=ObtenerId(realm,Ejemplar.class);
        realm.close();

    }
    <T extends RealmObject>AtomicInteger ObtenerId(Realm realm, Class<T> object){
        RealmResults<T> results = realm.where(object).findAll();
        if(results.size()>0){
            return new AtomicInteger(results.max("id").intValue());
        }else{
            return new AtomicInteger();
        }

    }
}
