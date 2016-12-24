package bynarie.renderer;

import bynarie.engine.PhysicsObject;
import bynarie.math.Vector;

import static org.lwjgl.opengl.GL11.GL_LINES;

public class RenderablePhysicsObject implements Renderable {
    private final PhysicsObject object;

    public RenderablePhysicsObject(PhysicsObject object) {
        this.object = object;
    }

    @Override
    public double[] getVertexData() {
        Vector p = object.getPosition();
        Vector b = new Vector(0.5, 0.5, 0.5);
        if (object.getCollisionMesh() != null) {
            b = object.getCollisionMesh().getBounds();
        }

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
                0, 2, 2, 4, 4, 6, 6, 0,
                0, 4, 1, 5, 2, 6, 3, 7,
        };
    }

    @Override
    public int getStride() {
        return 3;
    }

    @Override
    public int getPrimitive() {
        return GL_LINES;
    }
}
