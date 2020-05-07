package com.furkanergun;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SicaklikAlgilayici {

    private static SicaklikAlgilayici instance;
    private int sicaklik;

    private SicaklikAlgilayici(){
        sicaklik = 0;
    }

    //Singleton tasarım deseni - instance oluşturan fonksiyon.
    public static SicaklikAlgilayici getInstance() {
        if (instance == null)
            instance = new SicaklikAlgilayici();
        return instance;
    }

    //Random sıcaklık değeri getiriyor.
    public int sicaklikOku() {
        Random random = new Random();
        this.sicaklik = random.nextInt(40);
        return this.sicaklik;
    }
}
