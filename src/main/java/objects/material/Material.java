package objects.material;

import geometry.Ray;
import geometry.Vec3;
import objects.HitRecord;

public interface Material {
    boolean scatter(Ray rayIn, HitRecord hitRec, Vec3 attenuation, Ray scattered);
}
