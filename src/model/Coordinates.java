package model;

public class Coordinates<Koor> {
    private Koor first;
    private Koor second;

    public Coordinates() {
        this.first = null;
        this.second = null;
    }

    public Coordinates(Koor first, Koor second) {
        this.first = first;
        this.second = second;
    }

    public Koor getFirst() {
        return first;
    }

    public void setFirst(Koor first) {
        this.first = first;
    }

    public Koor getSecond() {
        return second;
    }

    public void setSecond(Koor second) {
        this.second = second;
    }

    public void put(Koor first, Koor second) {
        this.first = first;
        this.second = second;
    }

    public void clear() {
        this.first = null;
        this.second = null;
    }
}
