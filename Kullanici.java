package com.furkanergun;

import java.sql.SQLOutput;

public class Kullanici implements IObserver{
    String username;
    String password;

    public Kullanici(String username, String password){
        this.username = username;
        this.password = password;
    }

    //Gözlemci uyarıldığında çalışan fonksiyon.
    @Override
    public void update(boolean sogutucuDurumu) {
        System.out.println("Soğutucu " + (sogutucuDurumu ? "Açıldı" : "Kapatıldı") );
    }
}
