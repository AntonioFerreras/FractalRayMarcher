package calculations;

public class MandelBulb {
    public static double rot = 0;
    public static double power = 10.0*0.5;;
    private static final int MAX_ITER = 15;

    static Vec3 rotate_point_y(double cx,double cz,double angle,Vec3 p)
    {
        double s = RayMarcher.table.getSine((int)angle);
        double c = RayMarcher.table.getCos((int)angle);

        // translate point back to origin:
        p.x -= cx;
        p.z -= cz;

        // rotate point
        double xnew = p.x * c - p.z * s;
        double znew = p.x * s + p.z * c;

        // translate point back:
        p.x = xnew + cx;
        p.z = znew + cz;
        return p;
    }

    private static double sin(double rads) {
        return RayMarcher.table.getSine((int)Math.toDegrees(rads));
    }

    private static double cos(double rads) {
        return RayMarcher.table.getCos((int)Math.toDegrees(rads));
    }

    public static double getSDF(Vec3 cam) {
        //Rotate on y
        cam = rotate_point_y(0, 0, rot, cam);

        Vec3 z = cam;
        double dr = 1.0;
        double r = 0.0;
        int i;
        for (i = 0; i < MAX_ITER; i++) {

            r = z.length();
            if (r > 2) break;

            // convert to polar coordinates
            double theta = Math.acos(z.z / r);
            double phi = FastMath.atan2((float)z.y, (float)z.x);
            dr = Math.pow(r, power - 1.0) * power * dr + 1.0;

            // scale and rotate the point
            double zr = Math.pow(r, power);
            theta = theta * power;
            phi = phi * power;

            // convert back to cartesian coordinates
            z = new Vec3(sin(theta) * cos(phi), sin(phi) * sin(theta), cos(theta)).scale(zr);
            z = z.add(cam);

        }
        return 0.5 * Math.log(r) * r / dr;
    }
}
