package bynarie.engine;

import bynarie.math.Vector;

import java.util.Collection;

public abstract class Force {
    private Engine engine;
    private boolean linked;

    public Force(){
        this.linked = false;
    }

    final void link(Engine e) {
        this.engine = e;
        this.linked = true;
    }

    protected final Collection<PhysicsObject> getObjects(){
        if (this.linked)
            return this.engine.getObjects();
        return null;
    }

    public final boolean isLinked(){
        return this.linked;
    }

    public abstract Vector getForceOn(PhysicsObject po);
}

