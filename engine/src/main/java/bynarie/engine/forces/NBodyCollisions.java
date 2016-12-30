package bynarie.engine.forces;

import bynarie.engine.Force;
import bynarie.engine.PhysicsObject;
import bynarie.math.Vector;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class NBodyCollisions extends Force {
    private Set<PhysicsObject> objectsToRemove;

    public NBodyCollisions() {
        objectsToRemove = Collections.synchronizedSet(new HashSet<>());
    }

    private Vector temp1 = Vector.zero();
    @Override
    public Vector getForceOn(PhysicsObject po) {
        if (!objectsToRemove.contains(po)) {
            for (PhysicsObject po1 : this.getObjects()) {
                if (!objectsToRemove.contains(po1) && po1 != po && isColliding(po1, po)) {
                    objectsToRemove.add(po1);
                    po.getPosition().mul(po.getMass()).add(temp1.set(0,0,0).add(po1.getPosition()).mul(po1.getMass())).mul(1. / (po.getMass()+po1.getMass()));
                    po.getVelocity().mul(po.getMass()).add(temp1.set(0,0,0).add(po1.getVelocity()).mul(po1.getMass())).mul(1. / (po.getMass()+po1.getMass()));
                    po.setMass(po.getMass() + po1.getMass());
                }
            }
        }
        return temp1.set(0,0,0);
    }

    @Override
    protected void postForce() {
        this.getObjects().removeAll(objectsToRemove);
        objectsToRemove.clear();
    }

    private Vector temp3 = Vector.zero();
    private boolean isColliding(PhysicsObject po1, PhysicsObject po2) {
        return temp3.set(0,0,0).add(po1.getPosition()).sub(po2.getPosition()).length() < (Math.sqrt(po1.getMass()) + Math.sqrt(po2.getMass()))*0.01;
    }
}
