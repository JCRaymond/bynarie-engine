package bynarie.engine.forces;

import bynarie.engine.Force;
import bynarie.engine.PhysicsObject;
import bynarie.math.Vector;

public class NBodyGravity extends Force{
    private double G;
    private double damp;

    @Override
    public Vector getForceOn(PhysicsObject po) {
        Vector f = Vector.ZERO();
        Vector t;
        double len2;
        for (PhysicsObject other : this.getObjects()){
            if (po != other && other.getNullForces().getState(this.getClass())) {
                t = Vector.sub(other.getPosition(), po.getPosition());
                len2 = t.length2();
                f.add(t.normalize().mul((G*po.getMass()*other.getMass())/(len2+damp)));
            }
        }
        return f;
    }

    public NBodyGravity(double G, double damp){
        this.G = G;
        this.damp = damp;
    }

    public NBodyGravity(double G){
        this(G, 0);
    }

    public NBodyGravity(){
        this(1./667/1000000000);
    }
}
