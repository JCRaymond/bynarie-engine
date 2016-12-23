package bynarie.engine;

import java.util.Collection;

public class Cycler {
    private Engine engine;
    private boolean linked;
    private Thread t;
    private boolean running;
    private boolean paused;
    private boolean step;
    private double steptime;
    private double targetcycletime;
    private double cycleframetime;

    Cycler(Engine e) {
        this.engine = e;
        this.linked = true;
        this.t = Thread.currentThread();
        this.running = false;
        this.paused = false;
        this.step = false;
        this.steptime = 1000;
        this.targetcycletime = 0;
        this.cycleframetime = 0;
    }

    protected Cycler(){
        this(null);
        this.linked = false;
    }

    final Cycler link(Engine e){
        if (!this.linked){
            this.engine = e;
            this.linked = true;
        }
        return this;
    }

    protected final Collection<PhysicsObject> getObjects(){
        if (this.linked)
            return engine.getObjects();
        return null;
    }

    protected final Collection<Force> getForces(){
        if (this.linked)
            return engine.getForces();
        return null;
    }

    private void cycle(){
        long startTime = System.nanoTime(), endTime;
        long cycleStart;
        double cycleTime;
        while (running){
            while(this.paused && !this.step){
                try {
                    Thread.sleep(0);
                } catch (InterruptedException e) {}
            }
            cycleStart = System.nanoTime();

            for (PhysicsObject po : this.getObjects()){
                doCycle(po);
            }

            endTime = startTime;
            startTime = System.nanoTime();

            if (this.step){
                cycleTime = this.steptime;
            }
            else if (this.targetcycletime > 0){
                cycleTime = this.targetcycletime;
            }
            else{
                cycleTime = (endTime - startTime)/1000000000.;
            }

            for (PhysicsObject po : this.getObjects()){
                po.stepPosition(cycleTime);
            }

            try {
                Thread.sleep(0);
            } catch (InterruptedException e) {}

            this.step = false;

            while ((System.nanoTime()-cycleStart)/1000000000. < this.cycleframetime){}
        }
    }

    protected void doCycle(PhysicsObject po){
        for (Force f : this.engine.getForces()) {
            po.applyForce(f);
        }
    }

    final void start(int cyclesPerSecond, double cycleRate) {
        if (!this.linked) {
            System.err.println("There is no Engine linked!");
            return;
        }
        if (this.running) {
            System.err.println("The engine is already running!");
            return;
        }
        this.running = true;
        this.targetcycletime = cyclesPerSecond>0 ? 1./cyclesPerSecond : 0;
        this.cycleframetime = cycleRate > 0 ? this.targetcycletime/cycleRate : 0;
        t = new Thread(this::cycle);
        t.start();
    }

    final void start(int cyclesPerSecond) {
        start(cyclesPerSecond, 1);
    }

    final void start(){
        start(0);
    }

    final void stop() {
        if (!this.running) {
            System.err.println("The Engine is not running!");
            return;
        }
        this.running = false;
        this.step = true;
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    final void begin(int cyclesPerSecond, double cycleRate) {
        if (!this.linked){
            System.err.println("There is no Engine linked!");
            return;
        }
        if (this.running) {
            System.err.println("The Engine is already running!");
            return;
        }
        this.paused = true;
        this.start(cyclesPerSecond, cycleRate);
    }

    final void begin(int cyclesPerSecond){
        begin(cyclesPerSecond, 1);
    }

    final void begin(){
        begin(0);
    }

    final void pause() {
        if (!this.running){
            System.err.println("The Engine is not running!");
            return;
        }
        this.paused = true;
    }

    final void play() {
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

    final void step(double time) {
        if (!this.running){
            System.err.println("The Engine is not running!");
            return;
        }
        if (!this.paused) {
            System.err.println("The Engine is not paused!");
            return;
        }
        this.step = true;
        this.steptime = time;
        while (this.step) {
            try {
                Thread.sleep(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    final void step(){
        step(this.targetcycletime);
    }

    final void runNumSteps(int numSteps){
        for (int i=0; i<numSteps; i++){
            this.step();
        }
    }

    protected final boolean isRunning() {
        return this.running;
    }

    protected final boolean isPaused() {
        return this.paused;
    }

    final boolean isLinked() {
        return this.linked;
    }
}
