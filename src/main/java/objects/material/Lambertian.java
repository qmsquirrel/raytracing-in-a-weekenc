package objects.material;

import geometry.Ray;
import geometry.Vec3;
import objects.HitRecord;

public class Lambertian implements Material {

    public Vec3 albedo;

    public Lambertian(Vec3 albedo) {
        this.albedo = albedo;
    }

    @Override
    public boolean scatter(Ray rayIn, HitRecord hitRec, Vec3 attenuation, Ray scattered) {
        Vec3 target = hitRec.p.add(hitRec.normal).add(Vec3.randomInUnitSphere());
        scattered.set(new Ray(hitRec.p, target.subtract(hitRec.p)));
        attenuation.set(albedo);
        return true;
    }
}
