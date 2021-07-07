package by.training.task05.bean;

public interface Notifier {
    void addObserver(Observer obs);
    void removeObserver(Observer obs);
    void notifyAllObservers();
}
