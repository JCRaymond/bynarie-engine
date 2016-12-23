package bynarie.testing;

import bynarie.engine.Force;
import bynarie.engine.PhysicsObject;
import bynarie.math.Vector;

public class RandomForce extends Force {
    private double maxstrength;

    @Override
    public Vector getForceOn(PhysicsObject po) {
        return new Vector(Math.random()*2-1, Math.random()*2-1, Math.random()*2-1).mul(maxstrength);
    }

    public RandomForce(double maxstrength){
        this.maxstrength = maxstrength;
    }

    public RandomForce(){
        this(0.5);
    }
}
