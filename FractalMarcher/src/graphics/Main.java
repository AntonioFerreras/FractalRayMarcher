package graphics;

import calculations.MandelBulb;

import javax.swing.*;
import java.awt.image.BufferedImage;

public class Main extends JFrame {

    //Main constants
    static final int WINDOW_WIDTH = 768, WINDOW_HEIGHT = 768;
    private static final int PIXEL_SIZE = 1;

    //Viewport
    private static View view;
    public static int renderWidth = Main.WINDOW_WIDTH/Main.PIXEL_SIZE;
    public static int renderHeight = Main.WINDOW_HEIGHT/Main.PIXEL_SIZE;
    static BufferedImage renderImage = new BufferedImage(renderWidth, renderHeight, BufferedImage.TYPE_INT_RGB);

    public Main() {

        //Create window and viewport
        setTitle("Fractal Marcher");
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setUndecorated(true);

        view = new View();
        add(view);

        setVisible(true);

    }

    public static void main(String[] args) {
        new Main();

        //Initialize MandelBulb values
        MandelBulb.rot = 40;
        MandelBulb.power = 10.0*0.3;
    }
}
