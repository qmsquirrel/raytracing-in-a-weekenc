package geometry;

public class Ray {

    public Vec3 origin;
    public Vec3 direction;

    @Override
    public String toString() {
        return "Ray{" +
                "origin=" + origin +
                ", direction=" + direction +
                '}';
    }

    public Ray(Vec3 origin, Vec3 direction) {
        this.origin = origin;
        this.direction = direction;
    }

    public Vec3 getOrigin() {
        return origin;
    }

    public void setOrigin(Vec3 origin) {
        this.origin = origin;
    }

    public Vec3 getDirection() {
        return direction;
    }

    public void setDirection(Vec3 direction) {
        this.direction = direction;
    }

    Vec3 pointAtParameter(double t) {
        return origin.add(direction.multiply(t));
    }
}
