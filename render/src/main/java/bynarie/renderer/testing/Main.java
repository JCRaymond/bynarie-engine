package bynarie.renderer.testing;

import bynarie.engine.Engine;
import bynarie.engine.PhysicsObject;
import bynarie.engine.forces.NBodyCollisions;
import bynarie.engine.forces.NBodyGravity;
import bynarie.math.Vector;
import bynarie.renderer.RenderWindow;

import java.util.ArrayList;
import java.util.Collections;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        ArrayList<PhysicsObject> obs = new ArrayList<>();
        Collections.synchronizedList(obs);

        double w = 2.5;
        double n = 500;

        for (int i = 0; i < n; i++) {
            obs.add(new Body(1, new Vector((Math.random() * 2 - 1) * w, (Math.random() * 2 - 1) * w, 0), new Vector((Math.random() * 2 - 1) * w*w*w, (Math.random() * 2 - 1) * w*w*w, 0)));
        }

//        obs.add(new Body(100, Vector.unitx()).setColor(new double[]{1, 0,0}));
//        obs.add(new Body(100, Vector.zero()).setColor(new double[]{0,1,0}));

//        obs.add(new Body(5000, Vector.zero(), new Vector(0, 0.1875, 0)));
//        obs.add(new Body(20, new Vector(-2.35, 0, 0), new Vector(0, -46, 0)));
//        obs.add(new Body(0.5, new Vector(-2.5, 0, 0), new Vector(0, -33.5, 0)));

        Engine e = new Engine(obs, new NBodyCollisions(), new NBodyGravity(1));
//        Engine e = new Engine(obs, new NBodyGravity(1, 0.01));
        RenderWindow rw = new RenderWindow(e, 1280, 720);

        rw.start(200000, 0.1);
    }
}
