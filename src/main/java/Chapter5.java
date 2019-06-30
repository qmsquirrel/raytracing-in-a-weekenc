import geometry.Ray;
import geometry.Vec3;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

import static geometry.Vec3.dot;
import static geometry.Vec3.normalize;
import static java.lang.StrictMath.sqrt;

public class Chapter5 {

    static final String fileName = "results/chapter5.ppm";
    static final int width = 800;
    static final int height = 400;

    public static double hitSphere(Vec3 center, double radius, Ray r) {
        Vec3 oc = r.origin().subtract(center);
        double a = dot(r.direction(), r.direction());
        double b = 2.0f * dot(oc, r.direction());
        double c = dot(oc, oc) - radius*radius;
        double discriminant = b*b - 4*a*c;
        if (discriminant < 0) {
            return -1.0;
        } else {
            return (-b-sqrt(discriminant))/(2.0f*a);
        }
    }

    public static Vec3 color(Ray r) {
        Vec3 sphereOrigin = new Vec3(0,0,-1);
        double t = hitSphere(sphereOrigin, 0.5f, r);
        if (t > 0.0) {
            Vec3 normal =
                    normalize(r.pointAtParameter(t).subtract(sphereOrigin));
            return new Vec3(normal.x()+1, normal.y()+1, normal.z()+1).scale(0.5);
        }
        Vec3 unit_direction = normalize(r.direction());
        double s = 0.5f * (unit_direction.y() + 1.0f);
        return new Vec3(1.0f, 1.0f, 1.0f).scale(1.0f-s)
                .add(new Vec3(0.5, 0.7, 1.0).scale(s));
    }

    public static void main(String[] args) throws IOException {
        File output = new File(fileName);
        FileUtils.writeStringToFile(
                output,"P3\n" + width + " " + height + "\n255\n",
                "UTF-8");

        Vec3 lower_left_corner = new Vec3(-2.0f, -1.0f,  -1.0f);
        Vec3 horizontal = new Vec3(4.0f, 0.0f, 0.0f);
        Vec3 vertical = new Vec3(0.0f, 2.0f, 0.0f);
        Vec3 origin = new Vec3(0.0f, 0.0f, 0.0f);

        for (int j = height-1; j >= 0; j--) {
            for (int i = 0; i < width; i++) {
                double u = (double) i / (double) width;
                double v = (double) j / (double) height;
                Ray ray = new Ray(origin, lower_left_corner
                        .add(horizontal.scale(u))
                        .add(vertical.scale(v)));
                Vec3 col = color(ray);
                int ig = (int) (255.99*col.g());
                int ir = (int) (255.99*col.r());
                int ib = (int) (255.99*col.b());

                FileUtils.writeStringToFile(
                        output,ir + " " + ig + " " + ib + "\n",
                        "UTF-8",true);
            }
        }
    }
}
