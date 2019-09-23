package calculations;

public class Vec3 {

    double x, y, z;

    public Vec3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vec3 add(Vec3 secondVec) {
        return new Vec3(x + secondVec.x, y + secondVec.y, z + secondVec.z);
    }

    public Vec3 subtract(Vec3 secondVec) {
        return new Vec3(x - secondVec.x, y - secondVec.y, z - secondVec.z);
    }

    public Vec3 multiply(Vec3 secondVec) {
        return new Vec3(x * secondVec.x, y * secondVec.y, z * secondVec.z);
    }

    public Vec3 divide(Vec3 secondVec) {
        return new Vec3(x / secondVec.x, y / secondVec.y, z / secondVec.z);
    }

    public Vec3 scale(double scale) {
        return new Vec3(x * scale, y * scale, z * scale);
    }

    public Vec3 normalize() {
        double length = Math.sqrt(x*x + y*y + z*z);

        return new Vec3(x/length, y/length, z/length);
    }

    public double length() {
        return Math.sqrt(x*x + y*y + z*z);
    }

    public Vec3 min(Vec3 other) {
        return new Vec3(Math.min(x, other.x), Math.min(y, other.y), Math.min(z, other.z));
    }

    public Vec3 max(Vec3 other) {
        return new Vec3(Math.max(x, other.x), Math.max(y, other.y), Math.max(z, other.z));
    }

    public Vec3 abs() {
        return new Vec3(Math.abs(x), Math.abs(y), Math.abs(z));
    }

    public Vec3 pow(double exp) {
        return new Vec3(Math.pow(x, exp), Math.pow(y, exp), Math.pow(z, exp));
    }

    public double dotProduct(Vec3 secondVec) {
        return x * secondVec.x + y * secondVec.y + z * secondVec.z;
    }

    public static Vec3 crossProduct(Vec3 a, Vec3 b) {
        double cx = a.y*b.z - a.z*b.y;
        double cy = a.z*b.x - a.x*b.z;
        double cz = a.x*b.y - a.y*b.x;

        return new Vec3(cx, cy, cz);
    }

    public String toString() {
        return "x: " + x + " y: " + y + " z: " + z;
    }
}
