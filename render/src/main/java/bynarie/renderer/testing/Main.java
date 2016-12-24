package bynarie.renderer.testing;

import bynarie.engine.Engine;
import bynarie.engine.PhysicsObject;
import bynarie.engine.forces.NBodyGravity;
import bynarie.math.Vector;
import bynarie.renderer.RenderWindow;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<PhysicsObject> obs = new ArrayList<>();
        obs.add(new Ball().setPosition(new Vector(0, 0, 0)).setMass(2));
        obs.add(new Ball().setPosition(new Vector(1, 1, 0)).setMass(1));
        obs.add(new Ball().setPosition(new Vector(0, 1, 0)).setMass(1.5));

        Engine e=new Engine(obs, new NBodyGravity(1,.2));
        RenderWindow rw = new RenderWindow(e, 1280, 720);

        rw.start(200);
    }
}
