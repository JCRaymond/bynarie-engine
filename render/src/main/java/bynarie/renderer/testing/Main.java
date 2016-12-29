package bynarie.renderer.testing;

import bynarie.engine.Engine;
import bynarie.engine.Force;
import bynarie.engine.PhysicsObject;
import bynarie.engine.forces.NBodyGravity;
import bynarie.math.Vector;
import bynarie.renderer.RenderWindow;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<PhysicsObject> obs = new ArrayList<>();

        double w = 2;
        double n = w * w * 500;

        for (int i = 0; i < n; i++) {
            obs.add(new Ball().setPosition(new Vector((Math.random() * 2 - 1) * w, (Math.random() * 2 - 1) * w, 0)));
        }

        Engine e = new Engine(obs, new NBodyGravity(2, 0.01));
        RenderWindow rw = new RenderWindow(e, 1280, 720);

        rw.start(5000, 1);
    }
}
