package bynarie.math;

public class Vector {
    //region Vector Units
    public static Vector zero() {
        return new Vector();
    }

    public static Vector zero(Vector v) {
        return v.set(0, 0, 0);
    }

    public static Vector one() {
        return new Vector().set(1, 1, 1);
    }

    public static Vector one(Vector v) {
        return v.set(1, 1, 1);
    }

    public static Vector unitx() {
        return new Vector().set(1, 0, 0);
    }

    public static Vector unitx(Vector v) {
        return v.set(1, 0, 0);
    }

    public static Vector unity() {
        return new Vector().set(0, 1, 0);
    }

    public static Vector unity(Vector v) {
        return v.set(0, 1, 0);
    }

    public static Vector unitz() {
        return new Vector().set(0, 0, 1);
    }

    public static Vector unitz(Vector v) {
        return v.set(0, 0, 1);
    }
    //endregion

    public double x;
    public double y;
    public double z;

    //region Constructors
    public Vector() {
    }

    public Vector(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector(Vector v) {
        x = v.x;
        y = v.y;
        z = v.z;
    }
    //endregion

    //region Mutators
    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public Vector set(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
        return this;
    }
    //endregion

    //region Operations
    public Vector add(Vector v) {
        return set(x + v.x, y + v.y, z + v.z);
    }

    public Vector sub(Vector v) {
        return set(x - v.x, y - v.y, z - v.z);
    }

    public Vector mul(Vector v) {
        return set(x * v.x, y * v.y, z * v.z);
    }

    public Vector div(Vector v) {
        return set(x / v.x, y / v.y, z / v.z);
    }

    public Vector add(double c) {
        return set(x + c, y + c, z + c);
    }

    public Vector sub(double c) {
        return set(x - c, y - c, z - c);
    }

    public Vector mul(double c) {
        return set(x * c, y * c, z * c);
    }

    public Vector div(double c) {
        return set(x / c, y / c, z / c);
    }

    public Vector normalize() {
        return div(length());
    }
    //endregion

    public double length() {
        return Math.sqrt(length2());
    }

    public double length2() {
        return x * x + y * y + z * z;
    }

    public Vector copy() {
        return new Vector(x, y, z);
    }

    public Vector copy(Vector v) {
        return v.set(this.x, this.y, this.z);
    }

    public void copy(double[] arr) {
        copy(arr, 0);
    }

    public void copy(double[] arr, int index) {
        arr[index] = x;
        arr[index + 1] = y;
        arr[index + 2] = z;
    }

    @Override
    public String toString() {
        return String.format("<%.2f, %.2f, %.2f>", x, y, z);
    }
}

