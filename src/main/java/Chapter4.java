import geometry.Ray;
import geometry.Vec3;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

import static geometry.Vec3.dot;
import static geometry.Vec3.normalize;

public class Chapter4 {

    static final String fileName = "results/chapter4.ppm";

    public static boolean hitSphere(Vec3 center, double radius, Ray r) {
        Vec3 oc = r.origin().subtract(center);
        double a = dot(r.direction(), r.direction());
        double b = 2.0f * dot(oc, r.direction());
        double c = dot(oc, oc) - radius*radius;
        double discriminant = b*b - 4*a*c;
        if (discriminant < 0) {
            return false;
        } else {
            return true;
        }
    }

    public static Vec3 color(Ray r) {
        if (hitSphere(new Vec3(0,0,-1), 0.5f, r)) {
            return new Vec3(1,0,0);
        }
        Vec3 unit_direction = normalize(r.direction());
        double t = 0.5f * (unit_direction.y() + 1.0f);
        return new Vec3(1.0f, 1.0f, 1.0f).scale(1.0f-t)
                .add(new Vec3(0.5, 0.7, 1.0).scale(t));
    }

    public static void main(String[] args) throws IOException {
        int nx = 200;
        int ny = 100;

        String ppmFile;

        ppmFile = "P3\n" + nx + " " + ny + "\n255\n";
        Vec3 lower_left_corner = new Vec3(-2.0f, -1.0f,  -1.0f);
        Vec3 horizontal = new Vec3(4.0f, 0.0f, 0.0f);
        Vec3 vertical = new Vec3(0.0f, 2.0f, 0.0f);
        Vec3 origin = new Vec3(0.0f, 0.0f, 0.0f);

        for (int j = ny-1; j >= 0; j--) {
            for (int i = 0; i < nx; i++) {
                double u = (double) i / (double) nx;
                double v = (double) j / (double) ny;
                Ray ray = new Ray(origin, lower_left_corner
                        .add(horizontal.scale(u))
                        .add(vertical.scale(v)));
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
