package objects;

import geometry.Ray;

public interface Hitable {
    public boolean hit(Ray r, double tMin, double tMax, HitRecord hit);
}
