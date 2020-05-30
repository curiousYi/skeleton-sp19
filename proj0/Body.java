public class Body {
    double xxPos;
    double yyPos;
    double xxVel;
    double yyVel;
    double mass;
    String imgFileName;
    public Body(double xP, double yP, double xV,
                double yV, double m, String img) {
        this.xxPos = xP;
        this.yyPos = yP;
        this.xxVel = xV;
        this.mass = yV;
        this.mass = m;
        this.imgFileName = img;
    }

    public Body(Body b) {

    }
}