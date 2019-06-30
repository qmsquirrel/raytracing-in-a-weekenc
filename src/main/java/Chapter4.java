import geometry.Ray;
import geometry.Vec3;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

import static geometry.Vec3.dot;
import static geometry.Vec3.unit_vector;

public class Chapter4 {

    static final String fileName = "results/chapter4.ppm";

    public static boolean hitSphere(Vec3 center, double radius, Ray r) {
        Vec3 oc = r.getOrigin().subtract(center);
        double a = dot(r.getDirection(), r.getDirection());
        double b = 2.0 * dot(oc, r.getDirection());
        double c = dot(oc, oc) - radius*radius;
        double discriminant = b*b - 4*a*c;
        return (discriminant > 0);
    }

    public static Vec3 color(Ray r) {
        if (hitSphere(new Vec3(0,0,-1), 0.5, r)) {
            return new Vec3(1,0,0);
        }
        Vec3 unit_direction = unit_vector(r.getDirection());
        double t = 0.5 * (unit_direction.y() + 1.0);
        return (new Vec3(1.0, 1.0, 1.0)).multiply(1.0 -t)
                .add((new Vec3(0.5, 0.7, 1.0)).multiply(t));
    }

    public static void main(String[] args) throws IOException {
        int nx = 200;
        int ny = 100;

        String ppmFile;

        ppmFile = "P3\n" + nx + " " + ny + "\n255\n";
        Vec3 lower_left_corner = new Vec3(-2.0, -1.0,  -1.0);
        Vec3 horizontal = new Vec3(4.0, 0.0, 0.0);
        Vec3 vertical = new Vec3(0.0, 2.0, 0.0);
        Vec3 origin = new Vec3(0.0, 0.0, 0.0);
        for (int j = ny-1; j >= 0; j--) {
            for (int i = 0; i < nx; i++) {
                double u = (double) i / nx;
                double v = (double) j / nx;
                Ray ray = new Ray(origin, lower_left_corner
                        .add(horizontal.multiply(u))
                        .add(vertical.multiply(v)));
                Vec3 col = color(ray);
                int ig = (int) (255.99*col.g());
                int ir = (int) (255.99*col.r());
                int ib = (int) (255.99*col.b());
                ppmFile += ir + " " + ig + " " + ib + "\n" ;
            }
        }
        FileUtils.writeStringToFile(new File(fileName),ppmFile,"UTF-8");
    }
}
