package bynarie.engine;

import bynarie.math.Vector;

public class Mesh {
    // TODO: Mesh class stub
    private Vector bounds;

    public Vector getBounds() {
        return bounds;
    }

    public Mesh() {
        this(Vector.ONE().mul(0.5));
    }

    public Mesh(Vector bounds) {
        this.bounds = bounds;
    }
}
