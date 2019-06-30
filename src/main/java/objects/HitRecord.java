package objects;

import geometry.Vec3;

public class HitRecord {
    public double t;
    public Vec3 p;
    public Vec3 normal;

    public HitRecord() {
        t=0;
        p = new Vec3(0,0,0);
        normal = new Vec3(0,0,0);
    }
}
