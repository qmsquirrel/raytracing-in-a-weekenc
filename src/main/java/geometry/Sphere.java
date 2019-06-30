package geometry;

import static geometry.Vec3.dot;
import static java.lang.StrictMath.sqrt;

public class Sphere implements Hitable {

    Vec3 center;
    double radius;

    public Sphere(Vec3 center, double radius) {
        this.center = center;
        this.radius = radius;
    }

    public Sphere() {
    }


    @Override
    public boolean hit(Ray r, double tMin, double tMax, HitRecord hitRec) {
        Vec3 oc = r.origin().subtract(center);
        double a = dot(r.direction(), r.direction());
        double b = 2.0f * dot(oc, r.direction());
        double c = dot(oc, oc) - radius*radius;
        double discriminant = b*b - 4*a*c;
        if (discriminant > 0) {
            double tempT = (-b-sqrt(discriminant))/(2.0f*a);
            if (tempT < tMax && tMin < tempT) {
                hitRec.t = tempT;
                hitRec.p = r.pointAtParameter(tempT);
                hitRec.normal = hitRec.p.subtract(center).scale(1/radius);
                return true;
            }
        }
        return false;
    }
}
