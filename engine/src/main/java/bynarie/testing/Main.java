package bynarie.testing;

import bynarie.engine.Engine;
import bynarie.engine.PhysicsObject;
import bynarie.engine.forces.NBodyGravity;
import bynarie.math.Vector;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        ArrayList<PhysicsObject> obs = new ArrayList<>();
        obs.add(new Ball().setPosition(new Vector(0, 0, 0)).setMass(2));
        obs.add(new Ball().setPosition(new Vector(1, 1, 0)).setMass(1));
        obs.add(new Ball().setPosition(new Vector(0, 1, 0)).setMass(1.5));
        System.out.println("Generated!");
        Engine e = new Engine(obs, new NBodyGravity(5, 0.2));
        int cps = 1000;
        int secs = 30;
        e.begin(cps, 0);
        e.runNumSteps(cps*secs);
        e.stop();
        System.out.println();
        for (PhysicsObject po : obs) {
            System.out.println(po.getPosition() + " " + po.getVelocity());
        }
    }
}
