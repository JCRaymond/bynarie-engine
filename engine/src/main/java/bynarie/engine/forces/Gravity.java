package bynarie.engine.forces;

import bynarie.engine.Force;
import bynarie.engine.PhysicsObject;
import bynarie.math.Vector;

public class Gravity extends Force {
    private final double acceleration;
    private final Vector Z;

    @Override
    public Vector getForceOn(PhysicsObject po) {
        return Vector.mul(Z, acceleration * po.getMass());
    }

    public Gravity(){
        this(-9.8);
    }

    public Gravity(double acceleration){
        this.acceleration = acceleration;
        this.Z = Vector.UNITZ();
    }
}
