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
        this.preForce();
        this.objects.stream().forEach(this::applyForceOn);
        this.postForce();
    }

    private void applyForceOn(PhysicsObject po){
        if (po.getNullForces().getState(this.getClass()))
            po.applyForce(this.getForceOn(po));
    }

    protected void preForce(){}
    public abstract Vector getForceOn(PhysicsObject po);
    protected void postForce(){}
}

