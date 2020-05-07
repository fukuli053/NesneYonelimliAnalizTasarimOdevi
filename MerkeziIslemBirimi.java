package com.furkanergun;

public class MerkeziIslemBirimi {

    private static MerkeziIslemBirimi instance;
    private final SicaklikAlgilayici sensor;
    private final Eyleyici sogutucu;

    //Sensör ve soğutucu için instance oluşturuluyor.
    private MerkeziIslemBirimi(){
        sensor = SicaklikAlgilayici.getInstance();
        sogutucu = Eyleyici.getInstance();
    }

    //Singleton tasarım deseni - instance oluşturan fonksiyon.
    public static MerkeziIslemBirimi getInstance(IObserver observer) {
        if (instance == null){
            instance = new MerkeziIslemBirimi();
            instance.sogutucu.addObserve(observer);
        }
        return instance;
    }

    public int sicaklikGonder(){
        return sensor.sicaklikOku();
    }

    public void sogutucuAc(){
        this.sogutucu.sogutucuAc();
    }

    public void sogutucuKapat(){
        this.sogutucu.sogutucuKapat();
    }
}
