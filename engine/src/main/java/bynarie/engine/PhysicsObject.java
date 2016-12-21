package bynarie.engine;

import bynarie.math.Vector3;

public abstract class PhysicsObject {

    protected Mesh collisionMesh;

    protected Flags nullForces;

    protected double mass;
    protected Vector3 centerOfMass;

    protected Vector3 position;
    protected Vector3 velocity;
    private Vector3 netForce;

    //region Constructors

    public PhysicsObject(){
        this(null, new Flags(), 1.0, Vector3.ZERO, Vector3.ZERO, Vector3.ZERO);
    }

    public PhysicsObject(Mesh collisionMesh, Flags nullForces, double mass, Vector3 centerOfMass, Vector3 position, Vector3 velocity) {
        this.collisionMesh = collisionMesh;
        this.nullForces = nullForces;
        this.mass = mass;
        this.centerOfMass = centerOfMass;
        this.position = position;
        this.velocity = velocity;
        this.netForce = Vector3.ZERO;
    }

    //endregion

    //region Getters

    public final Vector3 getPosition() {
        return position;
    }

    public final Mesh getCollisionMesh() {
        return collisionMesh;
    }

    public final double getMass() {
        return mass;
    }

    public final Vector3 getCenterOfMass() {
        return centerOfMass;
    }

    public final Flags getNullForces() {
        return nullForces;
    }

    //endregion

    //region Setters

    public final void setCollisionMesh(Mesh collisionMesh) {
        this.collisionMesh = collisionMesh;
    }

    public final void setMass(double mass) {
        this.mass = mass;
    }

    public final void setCenterOfMass(Vector3 centerOfMass) {
        this.centerOfMass = centerOfMass;
    }

    public final void setPosition(Vector3 position) {
        this.position = position;
    }

    public final void setVelocity(Vector3 velocity) {
        this.velocity = velocity;
    }

    //endregion

    final void stepPosition(double time) {
        velocity.add(Vector3.mul(netForce, time / mass));
        position.add(Vector3.mul(velocity, time));
        netForce.nullify();
    }

    public final void applyForce(Vector3 force) {
        netForce.add(force);
    }
}
