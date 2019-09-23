package calculations;

public class Info {
    int steps;
    Vec3 collisionPoint;
    boolean collided;

    void setCollisionPoint(Vec3 collisionPoint) {
        this.collisionPoint = collisionPoint;
    }

    void setSteps(int steps) {
        this.steps = steps;
    }

}
