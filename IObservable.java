package com.furkanergun;

public interface IObservable {
    void addObserve(IObserver observer);
    void removeObserver(IObserver observer);
    void notifyObserver();
}
