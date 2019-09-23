package calculations;

import graphics.Main;

import java.awt.*;

public class RayMarcher {

    public static CosSineTable table = new CosSineTable();

    //Camera variables
    private static Vec3 cam = new Vec3(0, 0, -2.5);
    private static double FOV = 60;

    //Marching vars
    private static double EPSILON = 0.0001;
    private static int maxMarches = 255;
    private static double maxViewDistance = 15;
    private static float AOAmount = 0.6f, maxGlow = 1.3f;

    private static double map(double n, double start1, double stop1, double start2, double stop2) {
        return ((n - start1) / (stop1 - start1)) * (stop2 - start2) + start2;
    }

    private static double clamp(double f, double min, double max) {
        if (f < min)
            f = min;
        if (f > max)
            f = max;

        return f;
    }

    private static Vec3 getRayDirection(Vec2 pixelCoord) {
        //Find direction that ray projected from camera marches in

        Vec2 screenSize = new Vec2(Main.renderWidth, Main.renderHeight);

        Vec2 xy = pixelCoord.subtract(screenSize.scale(0.5));
        double z = (screenSize.y * 0.5) / Math.tan(Math.toRadians(FOV) * 0.5);

        return new Vec3(xy.x, xy.y, z).normalize();
    }

    /*** March ray ***/
    private static Info getRayInfo(Vec3 cam, Vec3 marchingDir) {

        double dist = 0;

        Info noData = new Info();
        noData.setSteps(maxMarches);
        noData.collided = false;

        for (int i = 0; i < maxMarches; i++) {
            //March ray
            Vec3 currentRayPos = cam.add(marchingDir.scale(dist));
            noData.setCollisionPoint(currentRayPos);

            //Get sceneSDF from current ray position for next march amount
            double marchDistance = MandelBulb.getSDF(currentRayPos);

            //If SDF is small enough, count it as a ray collision!
            if (marchDistance < EPSILON) {

                Info output = new Info();
                output.setSteps(i + 1);
                output.setCollisionPoint(currentRayPos);
                output.collided = true;

                return output;
            }

            //Update distance
            dist += marchDistance;

            //Check if outside maximum distance
            if (dist > maxViewDistance) {
                noData.setSteps(i);
                return noData;
            }
        }

        return noData;
    }

    private static double doAmbientOcclusion(int steps) {
        double lightness = ((double) steps / ((double) maxMarches * (1.0 - AOAmount)));

        //keep lightness in range
        lightness = clamp(lightness, 0, 1);

        return lightness;
    }

    private static double doGlow(int steps) {
        double lightness =  map(steps, 0, maxMarches, 0, maxGlow);

        //keep lightness in range
        lightness = clamp(lightness, 0, 1);

        return lightness;
    }

    public static Color getPixelColour(int x, int y) {
        Vec3 rayDir = getRayDirection(new Vec2(x, y));

        //Get ray collision
        Info info = getRayInfo(cam, rayDir);

        //Determine colour of the point on fractal
        double h = info.collisionPoint.length()/2.0;
        //float h = (float)info.iterations/(float)MandelBulb.MAX_ITER;

        //Vec3 col = new Vec3(h, 0, 1-h);
        Vec3 col = new Vec3(h, 0, 1-h);

        if (!info.collided) {

            //Create 'outer glow' of fractal
            double glow = doGlow(info.steps);

            col = new Vec3(glow, glow*0.3, glow);
        } else {
            //Get AO
            double ao = doAmbientOcclusion(info.steps);

            // In this case, the ambient occlusion is actually
            // used to create an 'internal glow'.
            col = col.scale(ao);
        }

        //Keep colour in range
        col = col.max(new Vec3(0, 0, 0)).min(new Vec3(1, 1, 1));

        return new Color((int) (col.x * 255), (int) (col.y * 255), (int) (col.z * 255));
    }
}
