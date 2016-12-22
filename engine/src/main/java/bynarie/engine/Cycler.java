package bynarie.engine;

import java.util.Collection;

public class Cycler {
    protected Collection<PhysicsObject> objects;
    protected Collection<Force> forces;
    private Thread t;
    private boolean running;
    private boolean paused;
    private boolean step;
    private double steptime;

    public Cycler(Collection<PhysicsObject> objects, Collection<Force> forces) {
        this.objects = objects;
        this.forces = forces;
        this.t = Thread.currentThread();
        this.running = false;
        this.paused = false;
        this.step = false;
    }

    public final void start() {
        if (this.running) {
            return;
        }
        this.running = true;
        t = new Thread(() -> {
            long startTime = System.nanoTime(), endTime;
            double cycleTime;
            while (this.running) {
                //Sleep to allow other threads to run
                try {
                    Thread.sleep(0);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                //Skip cycle if paused
                if (this.paused && !this.step) {
                    continue;
                }

                //Apply Forces
                for (PhysicsObject po : this.objects){
                    doCycle(po);
                }
                endTime = System.nanoTime();

                //Update Positions
                if (this.step){
                    cycleTime = this.steptime;
                }
                else{
                    cycleTime = (endTime - startTime)/1000000000.;
                }
                for (PhysicsObject po : this.objects){
                    po.stepPosition(cycleTime);
                }
                startTime = System.nanoTime();

                this.step = false;
            }
        });
        t.start();
    }

    public void doCycle(PhysicsObject po){
        for (Force f : forces) po.applyForce(f);
    }

    public final void stop() {
        if (!this.running) {
            return;
        }
        this.running = false;
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.paused = false;
        this.step = false;
    }

    public final void begin() {
        if (this.running) {
            return;
        }
        this.paused = true;
        this.start();
    }

    public final void pause() {
        this.paused = true;
    }

    public final void play() {
        this.paused = false;
    }

    public final void step(double time) {
        if (!this.paused) {
            return;
        }
        this.step = true;
        this.steptime = time/1000.;
        while (this.step) {
            try {
                Thread.sleep(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public final boolean isRunning() {
        return this.running;
    }

    public final boolean isPaused() {
        return this.paused;
    }
}
