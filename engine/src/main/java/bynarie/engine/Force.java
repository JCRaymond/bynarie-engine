package bynarie.engine;

import bynarie.math.Vector;

import java.util.Collection;

public abstract class Force {
    protected Collection<PhysicsObject> objects;

    final void setObjects(Collection<PhysicsObject> objects) {
        this.objects = objects;
    }

    public abstract Vector getForceOn(PhysicsObject po);
}

