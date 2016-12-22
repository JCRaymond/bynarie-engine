package bynarie.testing;

import bynarie.engine.Engine;
import bynarie.engine.PhysicsObject;
import bynarie.engine.forces.Gravity;
import bynarie.math.Vector;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<PhysicsObject> obs = new ArrayList<>();
        for (int i=0; i<1000000; i++) {
            obs.add(new Ball().setPosition(new Vector(0, 0, i)));
        }
        Engine e = new Engine(obs, Gravity.class);
        int steps = 41;
        e.start();
        while(obs.get(0).getVelocity().z > -9.7){
            try {
                Thread.sleep(0);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        }
        e.stop();
        System.out.println(obs.get(0).getPosition().z + " " + obs.get(0).getVelocity().z);
    }
}
