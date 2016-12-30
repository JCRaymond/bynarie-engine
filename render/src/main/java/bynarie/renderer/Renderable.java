package bynarie.renderer;

public interface Renderable {
    double[] getVertexData();
    int[] getIndices();
    int getStride();
    int getPrimitive();
    double[] getColor();
}
