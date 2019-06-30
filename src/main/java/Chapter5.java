import geometry.*;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static geometry.Vec3.normalize;

public class Chapter5 {

    static final String fileName = "results/chapter5p2.ppm";
    static final int width = 800;
    static final int height = 400;


    public static Vec3 color(Ray r, HitableList world) {
        HitRecord hitRec = new HitRecord();
        if(world.hit(r,0f, Double.MAX_VALUE,hitRec)) {
            System.out.println(hitRec);
            return new Vec3(
                    hitRec.normal().x()+1,
                    hitRec.normal().y()+1,
                    hitRec.normal().z()+1)
                    .scale(0.5f);
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

        Vec3 lower_left_corner = new Vec3(-2.0f, -1.0f,  -1.0f);
        Vec3 horizontal = new Vec3(4.0f, 0.0f, 0.0f);
        Vec3 vertical = new Vec3(0.0f, 2.0f, 0.0f);
        Vec3 origin = new Vec3(0.0f, 0.0f, 0.0f);

        List<Hitable> objects = new ArrayList<Hitable>(2);
        objects.add(new Sphere(new Vec3(0,0,-1),0.5f));
        objects.add(new Sphere(new Vec3(0,-100.5f,-1),100f));
        HitableList world = new HitableList(objects);

        for (int j = height-1; j >= 0; j--) {
            for (int i = 0; i < width; i++) {
                double u = (double) i / (double) width;
                double v = (double) j / (double) height;
                Ray ray = new Ray(origin, lower_left_corner
                        .add(horizontal.scale(u))
                        .add(vertical.scale(v)));
                Vec3 col = color(ray, world);
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
