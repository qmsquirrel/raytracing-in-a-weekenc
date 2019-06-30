package geometry;

public interface Hitable {
    boolean hit(Ray r, double tMin, double tMax, HitRecord hit);
}
