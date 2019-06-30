package geometry;

public class HitRecord {
    double t;
    Vec3 p;
    Vec3 normal;

    public HitRecord() {
        t=0;
        p = new Vec3(0,0,0);
        normal = new Vec3(0,0,0);
    }

    public double t() {
        return t;
    }

    public void setT(double t) {
        this.t = t;
    }

    public Vec3 p() {
        return p;
    }

    public void setP(Vec3 p) {
        this.p = p;
    }

    @Override
    public String toString() {
        return "HitRecord{" +
                "t=" + t +
                ", p=" + p +
                ", normal=" + normal +
                '}';
    }

    public Vec3 normal() {
        return normal;
    }

    public void setNormal(Vec3 normal) {
        this.normal = normal;
    }
}
