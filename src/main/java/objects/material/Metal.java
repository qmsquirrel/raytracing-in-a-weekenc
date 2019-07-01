package objects.material;

import geometry.Ray;
import geometry.Vec3;
import objects.HitRecord;

import static geometry.Vec3.dot;
import static geometry.Vec3.randomInUnitSphere;

public class Metal implements Material {

    public Vec3 albedo;
    public double fuzz;

    public Metal(Vec3 albedo, double fuzz) {
        this.albedo = albedo;
        if (fuzz < 1) {
            this.fuzz = fuzz;
        } else {
            this.fuzz = 1;
        }
    }

    @Override
    public boolean scatter(Ray rayIn, HitRecord hitRec, Vec3 attenuation, Ray scattered) {
        Vec3 reflected = Vec3.reflect(Vec3.normalize(rayIn.direction), hitRec.normal);
        scattered.set(new Ray(hitRec.p, reflected.add(randomInUnitSphere()).scale(fuzz)));
        attenuation.set(albedo);
        return (dot(scattered.direction(), hitRec.normal) > 0);
    }
}
