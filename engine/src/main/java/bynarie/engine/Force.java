package bynarie.engine;

import bynarie.math.Vector3;

public abstract class Force {
    public abstract Vector3 GetForce(PhysicsObject po);

    void ApplyForce(PhysicsObject po) {
        po.addForce(GetForce(po));
    }
}
