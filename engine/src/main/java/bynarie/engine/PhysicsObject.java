package bynarie.engine;

import bynarie.math.Vector;

public abstract class PhysicsObject {
    protected Flags nullForces;

    protected double mass;
    protected Vector centerOfMass;

    protected Vector position;
    protected Vector velocity;
    private Vector netForce;

    //region Constructors

    public PhysicsObject() {
        this(new Flags(), 1.0, Vector.zero(), Vector.zero(), Vector.zero());
    }

    public PhysicsObject(Flags nullForces, double mass, Vector centerOfMass, Vector position, Vector velocity) {
        this.nullForces = nullForces;
        this.mass = mass;
        this.centerOfMass = centerOfMass;
        this.position = position;
        this.velocity = velocity;
        this.netForce = Vector.zero();
    }

    //endregion

    //region Getters

    public final double getMass() {
        return mass;
    }

    public final Vector getCenterOfMass() {
        return centerOfMass;
    }

    public final Vector getPosition() {
        return position;
    }

    public final Vector getVelocity() {
        return velocity;
    }

    public final Flags getNullForces() {
        return nullForces;
    }

    //endregion

    //region Setters

    public final PhysicsObject setMass(double mass) {
        this.mass = mass;
        return this;
    }

    public final PhysicsObject setCenterOfMass(Vector centerOfMass) {
        this.centerOfMass = centerOfMass;
        return this;
    }

    public final PhysicsObject setPosition(Vector position) {
        this.position = position;
        return this;
    }

    public final PhysicsObject setVelocity(Vector velocity) {
        this.velocity = velocity;
        return this;
    }

    //endregion

    final void stepPosition(double time) {
        velocity.add(netForce.copy().mul(time / mass));
        position.add(velocity.copy().mul(time));
        Vector.zero(netForce);
    }

    public final void applyForce(Vector force) {
        netForce.add(force);
    }

    public final void applyForce(Force force) {
        if (this.nullForces.getState(force.getClass()))
            this.applyForce(force.getForceOn(this));
    }
}
