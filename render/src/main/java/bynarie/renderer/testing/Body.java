package bynarie.renderer.testing;

import bynarie.engine.PhysicsObject;
import bynarie.math.Vector;
import bynarie.renderer.Renderable;

import static org.lwjgl.opengl.GL11.GL_LINES;

public class Body extends PhysicsObject implements Renderable{
    private Vector b;
    private Vector p;

    public Body(double mass, Vector pos, Vector vel){
        super();
        setMass(mass);
        setPosition(pos);
        setVelocity(vel);
        this.b = new Vector();
    }

    public Body(Vector pos, Vector vel){
        this(1, pos, vel);
    }

    public Body(double mass, Vector pos){
        this(mass, pos, Vector.zero());
    }

    public Body(Vector pos){
        this(1, pos, Vector.zero());
    }

    @Override
    public double[] getVertexData() {
        p = this.getPosition();
        b = b.set(0.01,0.01,0.01).mul(Math.sqrt(this.getMass()));

        return new double[]{
                p.x + b.x, p.y + b.y, p.z + b.z,
                p.x + b.x, p.y + b.y, p.z - b.z,

                p.x + b.x, p.y - b.y, p.z + b.z,
                p.x + b.x, p.y - b.y, p.z - b.z,

                p.x - b.x, p.y + b.y, p.z + b.z,
                p.x - b.x, p.y + b.y, p.z - b.z,

                p.x - b.x, p.y - b.y, p.z + b.z,
                p.x - b.x, p.y - b.y, p.z - b.z,
        };
    }

    @Override
    public int[] getIndices() {
        return new int[]{
                0, 1, 2, 3, 4, 5, 6, 7,

                0, 2, 1, 3, 4, 6, 5, 7,

                0, 4, 1, 5, 2, 6, 3, 7,
        };    }

    @Override
    public int getStride() {
        return 3;
    }

    @Override
    public int getPrimitive() {
        return GL_LINES;
    }
}
