package bynarie.engine;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

public class Engine {
    private Collection<PhysicsObject> objects;
    private Collection<Force> forces;

    private Thread t;
    private boolean running;
    private boolean paused;
    private boolean step;
    private double steptime;

    public Engine() {
        this.objects = new HashSet<>();
        this.forces = new HashSet<>();

        this.t = Thread.currentThread();
        this.running = false;
        this.paused = false;
        this.step = false;
        this.steptime = 1000;
    }

    public Engine(Collection<PhysicsObject> objects){
        this();
        this.objects = objects;
    }

    public Engine(Collection<PhysicsObject> objects, Collection<Force> forces){
        this(objects);
        this.forces = forces;
    }

    public Engine(Collection<PhysicsObject> objects, Force... forces){
        this(objects);
        Collections.addAll(this.forces, forces);
    }

    public Collection<PhysicsObject> getObjects(){
        return this.objects;
    }

    public Collection<Force> getForces(){
        return this.forces;
    }

    public final Engine setObjects(Collection<PhysicsObject> objects){
        if (this.running){
            System.err.println("You can not set the objects while the Engine is running!");
            return this;
        }
        this.objects = objects;
        return this;
    }

    public final Engine setForces(Collection<Force> forces){
        if (this.running){
            System.err.println("You can not set the forces while the Engine is running!");
            return this;
        }
        this.forces = forces;
        return this;
    }

    private void cycle(double cyclePeriod, double cycleLength){
        long cycleStart;
        while (running){
            while(this.paused && !this.step){
                try {
                    Thread.sleep(0);
                } catch (InterruptedException e) {}
            }
            cycleStart = System.nanoTime();

            for (Force f : this.forces){
                f.applyForceOn(this.objects);
            }

            final double cycleTime;
            if (this.step && this.steptime > 0){
                cycleTime = this.steptime;
            }
            else {
                cycleTime = cyclePeriod;
            }

            this.objects.parallelStream().forEach(po -> po.stepPosition(cycleTime));

            try {
                Thread.sleep(0);
            } catch (InterruptedException e) {}

            this.step = false;

            while ((System.nanoTime()-cycleStart)/1000000000. < cycleLength){}
        }
    }

    public final void start(int cyclesPerSimSecond, double simRate) {
        if (this.running) {
            System.err.println("The Engine is already running!");
            return;
        }
        this.running = true;
        final double cyclePeriod = cyclesPerSimSecond>0 ? 1./cyclesPerSimSecond : 0;
        final double cycleLength = simRate > 0 ? cyclePeriod/simRate : 0;
        t = new Thread(() -> this.cycle(cyclePeriod, cycleLength));
        t.start();
    }

    public final void start(int cyclesPerSimSecond) {
        start(cyclesPerSimSecond, 1);
    }

    public final void start(){
        start(0);
    }

    public final void stop() {
        if (!this.running) {
            System.err.println("The Engine is not running!");
            return;
        }
        this.running = false;
        this.step = true;
        this.paused = false;
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public final void begin(int cyclesPerSimSecond, double simRate) {
        if (this.running) {
            System.err.println("The Engine is already running!");
            return;
        }
        this.paused = true;
        this.start(cyclesPerSimSecond, simRate);
    }

    public final void begin(int cyclesPerSimSecond){
        begin(cyclesPerSimSecond, 1);
    }

    public final void begin(){
        begin(0);
    }

    public final void pause() {
        if (!this.running){
            System.err.println("The Engine is not running!");
            return;
        }
        this.paused = true;
    }

    public final void play() {
        if (!this.running){
            System.err.println("The Engine is not running!");
            return;
        }
        if (!this.paused){
            System.err.println("The Engine is not paused!");
            return;
        }
        this.paused = false;
    }

    public final void step(double cyclePeriod) {
        if (!this.running){
            System.err.println("The Engine is not running!");
            return;
        }
        if (!this.paused) {
            System.err.println("The Engine is not paused!");
            return;
        }
        this.step = true;
        this.steptime = cyclePeriod;
        while (this.step) {
            try {
                Thread.sleep(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public final void step(){
        step(0);
    }

    public final void runNumSteps(int numSteps){
        for (int i=0; i<numSteps; i++){
            this.step();
        }
    }

    public final boolean isRunning() {
        return this.running;
    }

    public final boolean isPaused() {
        return this.paused;
    }
}
