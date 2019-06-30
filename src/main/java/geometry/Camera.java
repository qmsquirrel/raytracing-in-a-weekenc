package geometry;

public class Camera {

    final Vec3 lower_left_corner = new Vec3(-2.0f, -1.0f,  -1.0f);
    final Vec3 horizontal = new Vec3(4.0f, 0.0f, 0.0f);
    final Vec3 vertical = new Vec3(0.0f, 2.0f, 0.0f);
    final Vec3 origin = new Vec3(0.0f, 0.0f, 0.0f);

    public Ray getRay(double u, double v) {
        return new Ray(origin, lower_left_corner
                .add(horizontal.scale(u))
                .add(vertical.scale(v)));
    }
}
