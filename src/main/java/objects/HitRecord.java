package objects;

import geometry.Vec3;
import objects.material.Material;

public class HitRecord {
    public double t;
    public Vec3 p;
    public Vec3 normal;
    public Material mat;

    public HitRecord() {
        t=0;
        p = new Vec3(0,0,0);
        normal = new Vec3(0,0,0);
        mat = null;
    }

    public void setMat(Material mat) {
        this.mat = mat;
    }
}
