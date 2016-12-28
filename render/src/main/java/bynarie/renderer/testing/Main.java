package bynarie.renderer.testing;

import bynarie.engine.Engine;
import bynarie.engine.Force;
import bynarie.engine.PhysicsObject;
import bynarie.engine.forces.NBodyGravity;
import bynarie.math.Vector;
import bynarie.renderer.RenderWindow;

import java.util.ArrayList;

class ShittyNBodyGravity extends Force {

    private final double g;
    private final double d;

    public ShittyNBodyGravity(double g, double d) {
        this.g = g;
        this.d = d;
    }

    @Override
    public Vector getForceOn(PhysicsObject po) {
        Vector s = Vector.ZERO();
        Vector n = Vector.ZERO();
        Vector m = Vector.ZERO();

        for (PhysicsObject o : getObjects()) {
            if (o == po){
                continue;
            }
            n.nullify();
            m.nullify();
            s.add(m.add(o.getPosition()).sub(po.getPosition()).mul(1d/(m.length2()*m.length() + d)));
        }
        s.mul(1d / getObjects().size());
        double l1 = s.length();

        return s.mul(g);
//        return Vector.ZERO();
    }
}

public class Main {
    public static void main(String[] args) {
        ArrayList<PhysicsObject> obs = new ArrayList<>();
//        obs.add(new Ball().setPosition(new Vector(1, 0, 0)).setMass(1).setVelocity(new Vector(0, 0, 0)));
//        obs.add(new Ball().setPosition(new Vector(-1, 0, 0)).setMass(1).setVelocity(new Vector(0, -2, 0)));
//        obs.add(new Ball().setPosition(new Vector(3, 0, 0)).setMass(1).setVelocity(new Vector(0, -1, 0)));
        double w = 1;
        double n = w * w * 1000;
        for (int i = 0; i < n; i++) {
            obs.add(new Ball().setPosition(new Vector((Math.random() * 2 - 1) * w, (Math.random() * 2 - 1) * w, 0)));
        }

        Engine e = new Engine(obs, new NBodyGravity(2, 0.01));
//        Engine e = new Engine(obs, new NBodyGravity(5, 0.01));
        RenderWindow rw = new RenderWindow(e, 1280, 720);

        rw.start(10000, 1);
    }
}
