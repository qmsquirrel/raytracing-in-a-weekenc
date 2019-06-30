import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class Chapter1 {

    static final String fileName = "resources/chapter1.ppm";

    public static void main(String[] args) throws IOException {
        int nx = 200;
        int ny = 100;

        String ppmFile;

        ppmFile = "P3\n" + nx + " " + ny + "\n255\n";
        for (int j = ny-1; j >= 0; j--) {
            double g = (double) j / nx;
            int ig = (int) (255.99*g);
            for (int i = 0; i < nx; i++) {
                double r = (double) i / nx;
                double b = 0.2;
                int ir = (int) (255.99*r);
                int ib = (int) (255.99*b);
                ppmFile += ir + " " + ig + " " + ib + "\n" ;
            }
        }
        FileUtils.writeStringToFile(new File("results/chapter1.ppm"),ppmFile,"UTF-8");
    }
}
