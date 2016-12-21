package bynarie.engine;

import bynarie.math.Vector;

public abstract class Force {
    public abstract Vector GetForce(PhysicsObject po);

    void ApplyForce(PhysicsObject po) {
        if (po.getNullForces().getState(this.getClass())){
            po.applyForce(GetForce(po));
        }
    }
}

