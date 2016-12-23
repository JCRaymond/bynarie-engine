package bynarie.engine;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;

public class Engine {
    private Collection<PhysicsObject> objects;
    private Collection<Force> forces;
    private Cycler cycler;

    //region Constructors

    public Engine() {
        initialize();
    }

    public Engine(Collection<PhysicsObject> objects) {
        this.objects = objects;
        initialize();
    }

    public Engine(Collection<PhysicsObject> objects, Collection<Force> forces) {
        this.objects = objects;
        this.forces = forces;
        initialize();
    }

    public Engine(Force... forces) {
        this();
        addForces(forces);
    }

    public Engine(Collection<PhysicsObject> objects, Force... forces) {
        this(objects);
        addForces(forces);
    }

    public <T extends Force> Engine(Class<T>... forceClasses) {
        this();
        createAndAddForces(forceClasses);
    }

    @SafeVarargs
    public <T extends Force> Engine(Collection<PhysicsObject> objects, Class<T>... forceClasses) {
        this(objects);
        createAndAddForces(forceClasses);
    }

    private void initialize() {
        if (this.objects == null)
            this.objects = new HashSet<>();
        if (this.forces == null)
            this.forces = new HashSet<>();
        this.cycler = new Cycler(this);
    }

    //endregion

    //region Modifiers

    private void addForces(Force... forces) {
        for (Force f : forces){
            if (!f.isLinked()){
                f.link(this);
            }
        }
        Collections.addAll(this.forces, forces);
    }

    private void createAndAddForces() {
        //Do nothing (for SafeVarargs)
    }

    @SafeVarargs
    private final <T extends Force> void createAndAddForces(Class<T>... forceClasses) {
        for (Class<T> c : forceClasses) {
            Force f = null;
            try {
                f = c.getConstructor().newInstance();
            } catch (NoSuchMethodException e) {
                System.err.printf("%s does not have a default constructor, skipping.", c.getName());
            } catch (IllegalAccessException e) {
                System.err.printf("Cannot access the default constructor for class %s, skipping.", c.getName());
            } catch (InstantiationException e) {
                System.err.printf("Unable to create a new instance of %s, skipping.", c.getName());
            } catch (InvocationTargetException e) {
                System.err.printf("Failed to invoke the default constructor of %s, skipping.", c.getName());
            }
            if (f != null)
                f.link(this);
            this.forces.add(f);
        }
    }

    public final Engine setObjects(Collection<PhysicsObject> objects) {
        this.objects = objects;
        return this;
    }

    public final Engine setForces(Collection<Force> forces) {
        this.forces = forces;
        for (Force f : this.forces){
            if (!f.isLinked()){
                f.link(this);
            }
        }
        return this;
    }

    public final <T extends Cycler> Engine generateNewCycler(Class<T> cyclerClass){
        return this.generateNewCycler(cyclerClass, new Class<?>[0]);
    }

    public final <T extends Cycler> Engine generateNewCycler(Class<T> cyclerClass, Class<?>[] paramTypes, Object... params) {
        Cycler newCycler = null;
        try {
            newCycler = cyclerClass.getConstructor(paramTypes).newInstance(params);
        } catch (NoSuchMethodException e) {
            System.err.printf("%s does not have a constructor with those parameter types, doing nothing.", cyclerClass.getName());
        } catch (IllegalAccessException e) {
            System.err.printf("Cannot access the constructor with those parameter types for class %s, doing nothing.", cyclerClass.getName());
        } catch (InstantiationException e) {
            System.err.printf("Unable to create a new instance of %s, skipping.", cyclerClass.getName());
        } catch (InvocationTargetException e) {
            System.err.printf("Failed to invoke the constructor with those parameter types of %s, skipping.", cyclerClass.getName());
        }
        if (newCycler != null && !newCycler.isLinked()) {
            newCycler.link(this);
            this.cycler = newCycler;
        }
        return this;
    }

    //endregion

    //region Accessors

    public final Collection<PhysicsObject> getObjects() {
        return objects;
    }

    final Collection<Force> getForces() {
        return forces;
    }

    //endregion

    //region Cycler Control

    public final void start() {
        cycler.start();
    }

    public final void start(int cyclesPerSecond){
        cycler.start(cyclesPerSecond);
    }

    public final void start(int cyclesPerSecond, double cycleRate){
        cycler.start(cyclesPerSecond,cycleRate);
    }

    public final void stop() {
        cycler.stop();
    }

    public final void begin() {
        cycler.begin();
    }

    public final void begin(int cyclesPerSecond){
        cycler.begin(cyclesPerSecond);
    }

    public final void begin(int cyclesPerSecond, double cycleRate) {
        cycler.begin(cyclesPerSecond,cycleRate);
    }

    public final void pause() {
        cycler.pause();
    }

    public final void play() {
        cycler.play();
    }

    public final void step(double time) {
        cycler.step(time);
    }

    public final void step(){
        cycler.step();
    }

    public final void runNumSteps(int numSteps){
        cycler.runNumSteps(numSteps);
    }

    public final boolean isRunning() {
        return cycler.isRunning();
    }

    public final boolean isPaused() {
        return cycler.isPaused();
    }

    //endregion

}
