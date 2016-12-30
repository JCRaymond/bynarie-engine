package bynarie.renderer.testing;

import bynarie.engine.Engine;
import bynarie.engine.PhysicsObject;
import bynarie.engine.forces.NBodyCollisions;
import bynarie.engine.forces.NBodyGravity;
import bynarie.math.Vector;
import bynarie.renderer.RenderWindow;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        ArrayList<PhysicsObject> obs = new ArrayList<>();

        double w = 2;
        double n = 100;

        for (int i = 0; i < n; i++) {
            obs.add(new Body(10, new Vector((Math.random() * 2 - 1) * w, (Math.random() * 2 - 1) * w, 0)));
        }
//        obs.add(new Body(1, Vector.unitx()));
//        obs.add(new Body(1, Vector.zero(), Vector.unitx()));

        Engine e = new Engine(obs, new NBodyCollisions(), new NBodyGravity(5, 0.01));
//        Engine e = new Engine(obs, new NBodyGravity(5, 0.01));
        RenderWindow rw = new RenderWindow(e, 1280, 720);

        rw.start(5000, 0.01);
    }
}
