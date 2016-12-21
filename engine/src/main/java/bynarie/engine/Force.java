package bynarie.engine;

import bynarie.math.Vector;

public abstract class Force {
    //TODO initialize with access to all of the objects in the Engine
    public abstract Vector GetForce(PhysicsObject po);

    void ApplyForce(PhysicsObject po) {
        if (po.getNullForces().getState(this.getClass())){
            po.applyForce(GetForce(po));
        }
    }
}

