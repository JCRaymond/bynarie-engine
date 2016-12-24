package bynarie.math;

public class Vector {
    //region Unit Vectors
    public static Vector ZERO() {
        return new Vector(0, 0, 0);
    }

    public static Vector ONE() {
        return new Vector(1, 1, 1);
    }

    public static Vector UNITX() {
        return new Vector(1, 0, 0);
    }

    public static Vector UNITY() {
        return new Vector(0, 1, 0);
    }

    public static Vector UNITZ() {
        return new Vector(0, 0, 1);
    }
    //endregion

    private double[] vals = new double[3];

    public double[] getVals() {
        return vals;
    }


    //region Component Access
    public double x() {
        return vals[0];
    }

    public void x(double x) {
        vals[0] = x;
    }

    public double y() {
        return vals[1];
    }

    public void y(double y) {
        vals[1] = y;
    }

    public double z() {
        return vals[2];
    }

    public void z(double z) {
        vals[2] = z;
    }
    //endregion

    public static Vector add(Vector a, Vector b) {
        return new Vector(a).add(b);
    }

    public static Vector sub(Vector a, Vector b) {
        return new Vector(a).sub(b);
    }

    public static Vector mul(Vector v, double c) {
        return new Vector(v).mul(c);
    }

    //region Constructors
    public Vector(double x, double y, double z) {
        this.x(x);
        this.y(y);
        this.z(z);
    }

    public Vector(Vector v) {
        this(v.x(), v.y(), v.z());
    }
    //endregion

    @Override
    public String toString() {
        return String.format("<%.2f,%.2f,%.2f>", x(), y(), z());
    }

    public Vector add(Vector b) {
        x(x() + b.x());
        y(y() + b.y());
        z(z() + b.z());
        return this;
    }

    public Vector sub(Vector b) {
        x(x() - b.x());
        y(y() - b.y());
        z(z() - b.z());
        return this;
    }

    public Vector mul(double c) {
        x(x() * c);
        y(y() * c);
        z(z() * c);
        return this;
    }

    public Vector nullify() {
        this.mul(0);
        return this;
    }

    public double length() {
        return Math.sqrt(length2());
    }

    public double length2() {
        return x() * x() + y() * y() + z() * z();
    }

    public Vector normalize() {
        mul(1 / length());
        return this;
    }

    public static Vector normalized(Vector v) {
        return new Vector(v).normalize();
    }
}

