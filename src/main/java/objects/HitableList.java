package objects;

import geometry.Ray;

import java.util.List;

public class HitableList {
    List<Hitable> list;

    public HitableList(List<Hitable> list) {
        this.list = list;
    }

    public boolean hit(Ray r, double tMin, double tMax, HitRecord hitRec) {
        HitRecord tempRec = new HitRecord();
        boolean hitAnything = false;
        double closestSoFar = tMax;
        for (Hitable h: list) {
            if (h.hit(r, tMin, closestSoFar, tempRec)) {
                hitAnything = true;
                closestSoFar = tempRec.t;
                hitRec.t = tempRec.t;
                hitRec.p = tempRec.p;
                hitRec.normal = tempRec.normal;
            }
        }
        return hitAnything;
    }

}
