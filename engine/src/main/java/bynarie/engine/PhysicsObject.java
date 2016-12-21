package bynarie.engine;

import bynarie.math.Vector3;

public abstract class PhysicsObject {
    // todo: flags class for forces

    private Mesh collisionMesh;

    private double mass;
    private Vector3 centerOfMass;

    private Vector3 position;
    private Vector3 velocity;
    private Vector3 netForce;

    public final Vector3 getPosition() {
        return position;
    }

    public final Mesh getCollisionMesh() {
        return collisionMesh;
    }

    public final void setCollisionMesh(Mesh collisionMesh) {
        this.collisionMesh = collisionMesh;
    }

    final void stepPosition(double time) {
        velocity.add(Vector3.mul(netForce, time / mass));
        position.add(Vector3.mul(velocity, time));
        netForce.mul(0);
    }

    public final void addForce(Vector3 force) {
        netForce.add(force);
    }
}
