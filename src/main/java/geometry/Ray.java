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

    public Ray() {
    }

    public Ray(Vec3 origin, Vec3 direction) {
        this.origin = origin;
        this.direction = direction;
    }

    public Vec3 origin() {
        return origin;
    }

    public Vec3 direction() {
        return direction;
    }

    public Vec3 pointAtParameter(double t) {
        return origin.add(direction.scale(t));
    }
}
