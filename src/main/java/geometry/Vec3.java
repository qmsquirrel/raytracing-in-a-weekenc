package geometry;

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
        return sqrt(e[0]* e[0] + e[1]* e[1]+ e[2]*e[2]);
    }

    double squared_length() {
        return e[0]* e[0] + e[1]* e[1]+ e[2]*e[2];
    }

    @Override
    public String toString() {
        return e[0] + " " + e[1] + " " +e[2];
    }

    public void make_unit_vector() {
        double k = 1 / this.squared_length();
        e[0]*= k;
        e[1]*= k;
        e[2]*= k;
    }

    public Vec3 add(Vec3 other) {
        return  new Vec3(e[0] + other.e[0], e[1] + other.e[1], e[2] + other.e[2]);
    }

    public Vec3 substract(Vec3 other) {
        return  new Vec3(e[0] - other.e[0], e[1] - other.e[1], e[2] - other.e[2]);
    }

    public Vec3 multiply(Vec3 other) {
        return  new Vec3(e[0] * other.e[0], e[1] * other.e[1], e[2] * other.e[2]);
    }

    public Vec3 multiply(double scalar) {
        return  new Vec3(e[0] * scalar, e[1] * scalar, e[2] * scalar);
    }

    public Vec3 devide(Vec3 other) {
        return  new Vec3(e[0] / other.e[0], e[1] / other.e[1], e[2] / other.e[2]);
    }

    public Vec3 devide(double scalar) {
        return  new Vec3(e[0] / scalar, e[1] / scalar, e[2] / scalar);
    }

    public double dot(Vec3 other) {
        return e[0] * other.e[0]+ e[1] * other.e[1]+ e[2] * other.e[2];
    }

    public Vec3 cross(Vec3 other) {
        return new Vec3(
                e[1]*other.e[2] - other.e[1]*e[2],
                e[2]*other.e[0] - other.e[2]*e[0],
                e[0]*other.e[1] - other.e[0]*e[1]
        );
    }

    public static Vec3 unit_vector(Vec3 v) {
        return v.devide(v.length());
    }
}
