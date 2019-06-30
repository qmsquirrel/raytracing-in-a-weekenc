import geometry.Camera;
import geometry.Ray;
import geometry.Vec3;
import objects.HitRecord;
import objects.Hitable;
import objects.HitableList;
import objects.Sphere;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static geometry.Vec3.normalize;
import static geometry.Vec3.randomInUnitSphere;
import static java.lang.StrictMath.sqrt;

public class Chapter7 {

    static final String fileName = "results/chapter7.ppm";
    static final int width = 500;
    static final int height = 250;
    static final int trials = 25;


    public static Vec3 color(Ray r, HitableList world) {
        HitRecord hitRec = new HitRecord();
        if(world.hit(r,0.001f, Double.MAX_VALUE,hitRec)) {
            Vec3 target = hitRec.p.add(hitRec.normal).add(randomInUnitSphere());
            return color(new Ray(hitRec.p, target.subtract(hitRec.p)), world).scale(0.5);
        } else {
            Vec3 unit_direction = normalize(r.direction());
            double s = 0.5f * (unit_direction.y() + 1.0f);
            return new Vec3(1.0f, 1.0f, 1.0f).scale(1.0f-s)
                    .add(new Vec3(0.5, 0.7, 1.0).scale(s));

        }
    }

    public static void main(String[] args) throws IOException {
        File output = new File(fileName);
        FileUtils.writeStringToFile(
                output,"P3\n" + width + " " + height + "\n255\n",
                "UTF-8");

        List<Hitable> objects = new ArrayList<Hitable>(2);
        objects.add(new Sphere(new Vec3(0,0,-1),0.5f));
        objects.add(new Sphere(new Vec3(0,-100.5f,-1),100f));
        HitableList world = new HitableList(objects);

        Camera cam = new Camera();

        for (int j = height-1; j >= 0; j--) {
            for (int i = 0; i < width; i++) {
                Vec3 col = new Vec3(0f,0f,0f);
                for (int trial = 0; trial < trials; trial++) {
                    double u = (i+Math.random()) / (double) width;
                    double v = (j+Math.random()) / (double) height;
                    Ray r = cam.getRay(u,v);
                    col = col.add(color(r,world));
                }
                col = col.scale(1.0/(float)trials);
                col = new Vec3(sqrt(col.r()), sqrt(col.g()), sqrt(col.b()));
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
