import java.lang.Math;

public class Body {
    double xxPos;
    double yyPos;
    double xxVel;
    double yyVel;
    double mass;
    String imgFileName;
    static final double G = 6.67e-11;

    public Body(double xP, double yP, double xV,
                double yV, double m, String img) {
        this.xxPos = xP;
        this.yyPos = yP;
        this.xxVel = xV;
        this.yyVel = yV;
        this.mass = m;
        this.imgFileName = img;
    }

    public Body(Body b) {
        this.xxPos = b.xxPos;
        this.yyPos = b.yyPos;
        this.xxVel = b.xxVel;
        this.yyVel = b.yyVel;
        this.mass = b.mass;
        this.imgFileName = b.imgFileName;
    }

    private double calcDistanceSq(double x, double y) {
        double dist = Math.abs(x - y);

        return dist * dist;
    }

    public double calcDistance(Body b) {
        double xSquared = this.calcDistanceSq(this.xxPos, b.xxPos);
        double ySquared = this.calcDistanceSq(this.yyPos, b.yyPos);

        return Math.sqrt(xSquared + ySquared);
    }

    public double calcForceExertedBy(Body b) {
        double numerator = this.G * this.mass * b.mass;
        double distance = this.calcDistance(b);

        return numerator / Math.pow(distance, 2);
    }


    public double calcForceExertedByX(Body b) {
        double force = this.calcForceExertedBy(b);
        double distance = this.calcDistance(b);

        return (force / distance) * (b.xxPos - this.xxPos);
    }

    public double calcForceExertedByY(Body b) {
        double force = this.calcForceExertedBy(b);
        double distance = this.calcDistance(b);

        return (force / distance) * (b.yyPos - this.yyPos);
    }

    public double calcNetForceExertedByX(Body[] bodies) {
        double output = 0;
        Body currentBody = this;
        for(int i = 0; i < bodies.length; i++) {
            currentBody = bodies[i];
            if(!currentBody.equals(this)) {
                output += this.calcForceExertedByX(currentBody);
            } else {
                output += 0;
            }
        }
        return output;
    }

    public double calcNetForceExertedByY(Body[] bodies) {
        double output = 0;
        Body currentBody = this;
        for(int i = 0; i < bodies.length; i++) {
            currentBody = bodies[i];
            if(!currentBody.equals(this)) {
                output += this.calcForceExertedByY(currentBody);
            } else {
                output += 0;
            }
        }
        return output;
    }

    public void update(double time, double xForce, double yForce) {
        double xAccel = xForce / this.mass;
        double yAccel = yForce / this.mass;

        this.xxVel += xAccel * time;
        this.yyVel += yAccel * time;

        this.xxPos += this.xxVel * time;
        this.yyPos += this.yyVel * time;
    }

    public void draw() {
        StdDraw.picture(this.xxPos, this.yyPos, "images/" + this.imgFileName);
    }
}