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

    public <T extends Force> Engine(Collection<PhysicsObject> objects, Class<T>... forceClasses) {
        this(objects);
        createAndAddForces(forceClasses);
    }

    private void initialize() {
        if (this.objects == null)
            this.objects = new HashSet<>();
        if (this.forces == null)
            this.forces = new HashSet<>();
        this.cycler = new Cycler(this.objects, this.forces);
    }

    //endregion

    //region Modifiers

    //region Adders

    public final void addForces(Force... forces) {
        Collections.addAll(this.forces, forces);
    }

    public final void createAndAddForces() {
        //Do nothing (for SafeVarargs)
    }

    @SafeVarargs
    public final <T extends Force> void createAndAddForces(Class<T>... forceClasses) {
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
                f.setObjects(objects);
            this.forces.add(f);
        }
    }

    //endregion

    //region Removers

    public final void removeForce(Force force) {
        removeForces(force);
    }

    public final void removeForces(Force... forces) {
        for (Force f : forces) this.forces.remove(f);
    }

    public final void removeForces(Collection<Force> forces) {
        Iterator<Force> i = forces.iterator();
        while (i.hasNext()) {
            this.forces.remove(i.next());
            i.remove();
        }
    }

    public final <T extends Force> void removeForce(Class<T> forceClass) {
        removeForces(forceClass);
    }

    @SafeVarargs
    public final <T extends Force> void removeForces(Class<T>... forceClasses) {
        for (Class<T> c : forceClasses) {
            Iterator<Force> i = forces.iterator();
            while (i.hasNext()) {
                Force f = i.next();
                if (f.getClass() == c) {
                    forces.remove(f);
                }
                i.remove();
            }
        }
    }

    //endregion

    //endregion

    //region Cycler Control

    public void start() {
        cycler.start();
    }

    public void stop() {
        cycler.stop();
    }

    public void begin() {
        cycler.begin();
    }

    public void pause() {
        cycler.pause();
    }

    public void play() {
        cycler.play();
    }

    public void step(double time) {
        cycler.step(time);
    }

    public boolean isRunning() {
        return cycler.isRunning();
    }

    public boolean isPaused() {
        return cycler.isPaused();
    }

    //endregion

}
