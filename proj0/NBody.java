public class NBody {
    private static int bodyCount = 0;
    private static String filePath;
    private static double endTime;
    private static double dt;
    private static double currentTime = 0;
    private static Body[] bodies;

    public static double readRadius(String filePath) {
        In in = new In(filePath);
        in.readDouble();

        return in.readDouble();
    }

    //This is not checking for a bunch of conditions
    public static Body readBodyLine(In in) {
        double xxPos = in.readDouble();
        double yyPos = in.readDouble();
        double xVel = in.readDouble();
        double yVel = in.readDouble();
        double mass = in.readDouble();
        String filePath = in.readString();

        return new Body(xxPos, yyPos, xVel, yVel, mass, filePath);
    }

    public static Body[] readBodies(String filePath) {
        In in = new In(filePath);
        if(bodyCount == 0) {
            bodyCount = in.readInt();
        }
        double radius = in.readDouble();

        for(int i=0; i < bodyCount; i++) {
           bodies[i] = readBodyLine(in);
        }

        return bodies;
    }

    public static void updateLoop(String filePath) {
        if(bodyCount == 0) {
            In in = new In(filePath);
            bodyCount = in.readInt();
        }

        double[] xForces = new double[bodyCount];
        double[] yForces = new double[bodyCount];
    }

    public static void main(String[] args) {
        endTime = Double.parseDouble((args[0]));
        dt = Double.parseDouble((args[1]));
        double currentTime = 0;
        filePath = args[2];
        StdDraw.enableDoubleBuffering();

        double radius = readRadius(filePath);
        StdDraw.setXscale(-radius, radius);
        StdDraw.setYscale(-radius, radius);

        StdDraw.picture(0,0,"images/starfield.jpg");

        bodies = readBodies(filePath);

        for(Body body : bodies) {
            body.draw();
        }
    }
}