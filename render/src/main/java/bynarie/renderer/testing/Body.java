package bynarie.renderer.testing;

import bynarie.engine.PhysicsObject;
import bynarie.math.Vector;
import bynarie.renderer.Renderable;

import static org.lwjgl.opengl.GL11.GL_LINE;
import static org.lwjgl.opengl.GL11.GL_LINES;
import static org.lwjgl.opengl.GL11.GL_LINE_LOOP;

public class Body extends PhysicsObject implements Renderable{
    private Vector p;
    private static int numPoints = 15;

    public Body(double mass, Vector pos, Vector vel){
        super();
        setMass(mass);
        setPosition(pos);
        setVelocity(vel);
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
        double radius = Math.sqrt(this.getMass())*0.01;

        double[] vertexData = new double[numPoints*3];
        double angle = 2*Math.PI/numPoints;
        double r = 0;
        for (int i=0; i<vertexData.length; r+=angle){
            vertexData[i++] = Math.cos(r);
            vertexData[i++] = Math.sin(r);
            vertexData[i++] = 0;
        }
        for (int i=0; i<vertexData.length; i++){
            vertexData[i] *= radius;
        }
        for (int i=0; i<vertexData.length;){
            vertexData[i++] += p.getX();
            vertexData[i++] += p.getY();
            vertexData[i++] += p.getZ();
        }
        return vertexData;

//        p = this.getPosition();
//        Vector b = new Vector(0.01, 0.01, 0.01).mul(Math.sqrt(this.getMass()));
//
//        return new double[]{
//                p.x + b.x, p.y + b.y, p.z + b.z,
//                p.x + b.x, p.y + b.y, p.z - b.z,
//
//                p.x + b.x, p.y - b.y, p.z + b.z,
//                p.x + b.x, p.y - b.y, p.z - b.z,
//
//                p.x - b.x, p.y + b.y, p.z + b.z,
//                p.x - b.x, p.y + b.y, p.z - b.z,
//
//                p.x - b.x, p.y - b.y, p.z + b.z,
//                p.x - b.x, p.y - b.y, p.z - b.z,
//        };
    }

    @Override
    public int[] getIndices() {
        int[] indeces = new int[numPoints];
        for (int i=0; i<indeces.length-1; i++){
            indeces[i] = i+1;
        }
        return indeces;

//        return new int[]{
//                0, 1, 2, 3, 4, 5, 6, 7,
//
//                0, 2, 1, 3, 4, 6, 5, 7,
//
//                0, 4, 1, 5, 2, 6, 3, 7,
//        };
    }

    @Override
    public int getStride() {
        return 3;
    }

    @Override
    public int getPrimitive() {
        return GL_LINE_LOOP;
    }
}
