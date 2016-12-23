package bynarie.engine.forces;

import bynarie.engine.Force;
import bynarie.engine.PhysicsObject;
import bynarie.math.Vector;

public class Gravity extends Force {
    private final double acceleration;

    @Override
    public Vector getForceOn(PhysicsObject po) {
        return new Vector(0,0,acceleration * po.getMass());
    }

    public Gravity(){
        this(-9.8);
    }

    public Gravity(double acceleration){
        this.acceleration = acceleration;
    }
}
