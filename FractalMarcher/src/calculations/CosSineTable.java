package calculations;

public class CosSineTable {
    private double[] cos = new double[361];
    private double[] sin = new double[361];

    public CosSineTable() {
        for (int i = 0; i <= 360; i++) {
            cos[i] = Math.cos(Math.toRadians(i));
            sin[i] = Math.sin(Math.toRadians(i));
        }
    }

    public double getSine(int angle) {
        int angleCircle = angle % 360;
        if(angleCircle >= 0)
            return sin[angleCircle];
        else
            return -sin[-angleCircle];
    }

    public double getCos(int angle) {
        int angleCircle = angle % 360;
        if(angleCircle >= 0)
            return cos[angleCircle];
        else
            return cos[-angleCircle];
    }

}