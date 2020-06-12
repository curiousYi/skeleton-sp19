public class NBody {
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
        int countOfBodies = in.readInt();
        double radius = in.readDouble();
        Body[] bodies = new Body[countOfBodies];

        for(int i=0; i < countOfBodies; i++) {
           bodies[i] = readBodyLine(in);
        }

        return bodies;
    }
}