package bynarie.engine;

import bynarie.math.Vector;

import java.util.Collection;

public abstract class Force {
    private Collection<PhysicsObject> objects;

    public final Collection<PhysicsObject> getObjects(){
        return this.objects;
    }

    final void applyForceOn(Collection<PhysicsObject> objects){
        this.objects = objects;
        this.objects.parallelStream().forEach(this::applyForceOn);
    }

    private void applyForceOn(PhysicsObject po){
        if (po.getNullForces().getState(this.getClass()))
            po.applyForce(this.getForceOn(po));
    }

    public abstract Vector getForceOn(PhysicsObject po);
}

