package bynarie.math;

public class Vector3 {
    public static final Vector3 ZERO  = new Vector3(0,0,0);
    public static final Vector3 ONE   = new Vector3(1,1,1);
    public static final Vector3 UNITX = new Vector3(1,0,0);
    public static final Vector3 UNITY = new Vector3(0,1,0);
    public static final Vector3 UNITZ = new Vector3(0,0,1);

    public double x;
    public double y;
    public double z;

    public static Vector3 add(Vector3 a, Vector3 b) {
        return new Vector3(a.x + b.x, a.y + b.y, a.z + b.z);
    }
    public static Vector3 mul(Vector3 v, double c) {
        return new Vector3(v.x * c, v.y * c, v.z * c);
    }

    public Vector3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public String toString() {
        return String.format("<%.2f,%.2f,%.2f>", x, y, z);
    }

    public Vector3 add(Vector3 b) {
        x += b.x;
        y += b.y;
        z += b.z;
        return this;
    }

    public Vector3 mul(double c) {
        x *= c;
        y *= c;
        z *= c;
        return this;
    }

    public Vector3 nullify(){
        this.mul(0);
        return this;
    }


}

