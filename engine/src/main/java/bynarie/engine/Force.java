package bynarie.engine;

import bynarie.math.Vector3;

public abstract class Force {
    public abstract Vector3 GetForce(PhysicsObject po);

    void ApplyForce(PhysicsObject po) {
        if (po.getNullForces().getState(this.getClass())){
            po.applyForce(GetForce(po));
        }
    }
}

