package bynarie.math;

public class Vector {
    public static Vector ZERO()  {return new Vector(0,0,0);}
    public static Vector ONE()   {return new Vector(1,1,1);}
    public static Vector UNITX() {return new Vector(1,0,0);}
    public static Vector UNITY() {return new Vector(0,1,0);}
    public static Vector UNITZ() {return new Vector(0,0,1);}

    public double x;
    public double y;
    public double z;

    public static Vector add(Vector a, Vector b) {
        return new Vector(a.x + b.x, a.y + b.y, a.z + b.z);
    }
    public static Vector sub(Vector a, Vector b) {
        return new Vector(a.x - b.x, a.y - b.y, a.z - b.z);
    }
    public static Vector mul(Vector v, double c) {
        return new Vector(v.x * c, v.y * c, v.z * c);
    }

    public Vector(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector(Vector v){
        this.x = v.x;
        this.y = v.y;
        this.z = v.z;
    }

    @Override
    public String toString() {
        return String.format("<%.2f,%.2f,%.2f>", x, y, z);
    }

    public Vector add(Vector b) {
        x += b.x;
        y += b.y;
        z += b.z;
        return this;
    }

    public Vector sub(Vector b){
        x -= b.x;
        y -= b.y;
        z -= b.z;
        return this;
    }

    public Vector mul(double c) {
        x *= c;
        y *= c;
        z *= c;
        return this;
    }

    public Vector nullify(){
        this.mul(0);
        return this;
    }

    public double length(){
        return Math.sqrt(x*x + y*y + z*z);
    }

    public double length2(){
        return x*x + y*y + z*z;
    }

    public Vector normalize(){
        double len = length();
        x /= len;
        y /= len;
        z /= len;
        return this;
    }
}

