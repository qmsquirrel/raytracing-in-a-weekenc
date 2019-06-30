import org.apache.commons.io.FileUtils;
import vector.Vec3;

import java.io.File;
import java.io.IOException;

public class Chapter2 {

    static final String fileName = "results/chapter2.ppm";

    public static void main(String[] args) throws IOException {
        int nx = 200;
        int ny = 100;

        String ppmFile;

        ppmFile = "P3\n" + nx + " " + ny + "\n255\n";
        for (int j = ny-1; j >= 0; j--) {
            for (int i = 0; i < nx; i++) {
                Vec3 col = new Vec3(
                        (double) i / nx, (double) j / nx, 0.2);
                int ig = (int) (255.99*col.g());
                int ir = (int) (255.99*col.r());
                int ib = (int) (255.99*col.b());
                ppmFile += ir + " " + ig + " " + ib + "\n" ;
            }
        }
        FileUtils.writeStringToFile(new File(fileName),ppmFile,"UTF-8");
    }
}
