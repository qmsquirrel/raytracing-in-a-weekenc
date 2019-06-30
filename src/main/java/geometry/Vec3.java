package geometry;

import static java.lang.StrictMath.pow;
import static java.lang.StrictMath.sqrt;

public class Vec3 {

    public double[] e = new double[3];

    public Vec3() {
    }

    public Vec3(double e0, double e1, double e2) {
        e[0] = e0;
        e[1] = e1;
        e[2] = e2;
    }

    public double x() {
        return e[0];
    }

    public double y() {
        return e[1];
    }

    public double z() {
        return e[2];
    }

    public double r() {
        return e[0];
    }

    public double g() {
        return e[1];
    }

    public double b() {
        return e[2];
    }

    double length() {
        return sqrt(pow(e[0],2)+
                pow(e[1],2)+
                pow(e[2],2));
    }

    double squared_length() {
        return pow(e[0],2)+
                pow(e[1],2)+
                pow(e[2],2);
    }

    @Override
    public String toString() {
        return e[0] + " " + e[1] + " " +e[2];
    }

    public static Vec3 normalize(Vec3 v) {
        double length = v.length();
        return new Vec3(v.x()/length, v.y()/length, v.z()/length);
    }

    public Vec3 normalize() {
        double length = this.length();
        return new Vec3(e[0]/length, e[1]/length, e[2]/length);
    }


    public Vec3 add(Vec3 other) {
        return  new Vec3(e[0] + other.e[0], e[1] + other.e[1], e[2] + other.e[2]);
    }

    public Vec3 subtract(Vec3 other) {
        return  new Vec3(e[0] - other.e[0], e[1] - other.e[1], e[2] - other.e[2]);
    }

    public Vec3 scale(Vec3 other) {
        return  new Vec3(e[0] * other.e[0], e[1] * other.e[1], e[2] * other.e[2]);
    }

    public Vec3 scale(double scalar) {
        return  new Vec3(e[0] * scalar, e[1] * scalar, e[2] * scalar);
    }

    public Vec3 divide(Vec3 other) {
        return  new Vec3(e[0] / other.e[0], e[1] / other.e[1], e[2] / other.e[2]);
    }

    public Vec3 divide(double scalar) {
        return  new Vec3(e[0] / scalar, e[1] / scalar, e[2] / scalar);
    }

    public static double dot(Vec3 one, Vec3 other) {
        return one.e[0] * other.e[0]+ one.e[1] * other.e[1]+ one.e[2] * other.e[2];
    }

    public static Vec3 cross(Vec3 one, Vec3 two) {
        return new Vec3(
                one.e[1]*two.e[2] - two.e[1]*one.e[2],
                one.e[2]*two.e[0] - two.e[2]*one.e[0],
                one.e[0]*two.e[1] - two.e[0]*one.e[1]
        );
    }

    public static Vec3 randomInUnitSphere() {
        Vec3 p;
        do {
            p = new Vec3(Math.random(),Math.random(), Math.random()).scale(2.0f).subtract(new Vec3(1,1,1));
        } while (p.squared_length() >= 1.0f);
        return p;
    }

}
