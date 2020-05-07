package com.furkanergun;

import java.util.ArrayList;
import java.util.List;

public class Eyleyici implements IObservable{

    private static Eyleyici instance;
    private boolean sogutucuDurumu;

    private Eyleyici(){
        sogutucuDurumu = false;
    }

    //Singleton tasarım deseni - instance oluşturan fonksiyon.
    public static synchronized Eyleyici getInstance() {
        if (instance == null)
            instance = new Eyleyici();
        return instance;
    }

    //Gözlemcilerimizi tutan ArrayList.
    private List<IObserver> observers = new ArrayList<>();

    @Override
    public void addObserve(IObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(IObserver observer) {
        observers.remove(observer);
    }

    @Override
    //Gözlemcileri uyaran metot.
    public void notifyObserver() {
        for (IObserver observer :
                observers) {
            observer.update(sogutucuDurumu);
        }
    }

    public void sogutucuAc(){
        if (!sogutucuDurumu){
            sogutucuDurumu = true;
            notifyObserver();
        } else{
            System.out.println("Soğutucu zaten açık !");
        }
    }

    public void sogutucuKapat(){
        if (sogutucuDurumu){
            sogutucuDurumu = false;
            notifyObserver();
        } else{
            System.out.println("Soğutucu zaten kapalı !");
        }
    }
}
