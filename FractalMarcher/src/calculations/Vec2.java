package calculations;

public class Vec2 {

    public double x, y;

    public Vec2(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vec2 add(Vec2 secondVec) {
        return new Vec2(x + secondVec.x, y + secondVec.y);
    }

    public Vec2 subtract(Vec2 secondVec) {
        return new Vec2(x - secondVec.x, y - secondVec.y);
    }

    public Vec2 multiply(Vec2 secondVec) {
        return new Vec2(x * secondVec.x, y * secondVec.y);
    }

    public Vec2 divide(Vec2 secondVec) {
        return new Vec2(x / secondVec.x, y / secondVec.y);
    }

    public Vec2 scale(double scale) {
        return new Vec2(x * scale, y * scale);
    }

    public Vec2 normalize() {
        double length = Math.sqrt(x*x + y*y);

        return new Vec2(x/length, y/length);
    }

    public double length() {
        double length = Math.sqrt(x*x + y*y);

        return length;
    }

    public Vec2 min(Vec2 other) {
        return new Vec2(Math.min(x, other.x), Math.min(y, other.y));
    }

    public Vec2 max(Vec2 other) {
        return new Vec2(Math.max(x, other.x), Math.max(y, other.y));
    }

    public Vec2 abs() {
        return new Vec2(Math.abs(x), Math.abs(y));
    }

    public double dotProduct(Vec2 secondVec) {
        return x * secondVec.x + y * secondVec.y;
    }

    public String toString() {
        return "x: " + x + " y: " + y;
    }
}
