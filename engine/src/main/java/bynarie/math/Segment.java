package bynarie.math;

public class Segment {
    private Vector a;
    private Vector b;

    public Segment(Vector a, Vector b) {
        this.a = a;
        this.b = b;
    }

    public Vector getA() {
        return a;
    }

    public void setA(Vector a) {
        this.a = a;
    }

    public Vector getB() {
        return b;
    }

    public void setB(Vector b) {
        this.b = b;
    }
}
