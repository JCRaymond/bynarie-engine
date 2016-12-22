package bynarie.engine;

import bynarie.math.Vector;

public abstract class PhysicsObject {

    protected Mesh collisionMesh;

    protected Flags nullForces;

    protected double mass;
    protected Vector centerOfMass;

    protected Vector position;
    protected Vector velocity;
    private Vector netForce;

    //region Constructors

    public PhysicsObject(){
        this(null, new Flags(), 1.0, Vector.ZERO(), Vector.ZERO(), Vector.ZERO());
    }

    public PhysicsObject(Mesh collisionMesh, Flags nullForces, double mass, Vector centerOfMass, Vector position, Vector velocity) {
        this.collisionMesh = collisionMesh;
        this.nullForces = nullForces;
        this.mass = mass;
        this.centerOfMass = centerOfMass;
        this.position = position;
        this.velocity = velocity;
        this.netForce = Vector.ZERO();
    }

    //endregion

    //region Getters

    public final Mesh getCollisionMesh() {
        return collisionMesh;
    }

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

    public final PhysicsObject setCollisionMesh(Mesh collisionMesh) {
        this.collisionMesh = collisionMesh;
        return this;
    }

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
        velocity.add(Vector.mul(netForce, time / mass));
        position.add(Vector.mul(velocity, time));
        netForce.nullify();
    }

    public final void applyForce(Vector force) {
        netForce.add(force);
    }

    public final void applyForce(Force force){
        if (this.nullForces.getState(force.getClass()))
            this.applyForce(force.getForceOn(this));
    }
}
