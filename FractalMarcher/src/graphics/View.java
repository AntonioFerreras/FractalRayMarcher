package graphics;

import calculations.Vec2;
import calculations.MandelBulb;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.concurrent.CountDownLatch;

public class View extends JPanel {

    private int frame = 0;

    //Used for naming rendered frames
    private DecimalFormat df = new DecimalFormat("0000");

    View() {
        setSize(Main.WINDOW_WIDTH, Main.WINDOW_HEIGHT);
        setBackground(Color.black);

    }

    public void paintComponent(Graphics g) {

        /*** Threading ***/

        int threadCount = 32;

        //Record start time
        long start = System.currentTimeMillis();

        CountDownLatch latch = new CountDownLatch(threadCount);

        //Spawn threads
        for(int t = 0; t < threadCount; t++) {
            int startRender = t * (Main.renderWidth / threadCount);
            int endRender = (t + 1) * (Main.renderWidth / threadCount);

            new Thread(new RenderTask(startRender, endRender, latch)).start();
        }
        //Wait for all render threads to finish
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        /*** Upscaling and drawing ***/

        //Apply Up-scaling with bicubic interpolation
        BufferedImage scaledRender = upscale(Main.renderImage, new Vec2(2.0, 2.0));

        //Draw rendered image scaled to window size
        g.drawImage(scaledRender, 0, 0, Main.WINDOW_WIDTH, Main.WINDOW_HEIGHT,
                0, 0, scaledRender.getWidth(null), scaledRender.getHeight(null), null);

        /*** Export frame ***/

        //Save rendered image
        try {
            ImageIO.write(scaledRender, "png", new File(df.format(frame)+".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        /*** Print out frame time ***/

        long elapsedTime = System.currentTimeMillis() - start;

        System.out.println("Render Time: " + elapsedTime/1000.0 + "s");

        /*** Update values for next frame ***/

        if(MandelBulb.power < 10.0*0.9) {
            MandelBulb.power += 0.008;
            frame++;

            repaint();
        }
    }

    private BufferedImage upscale(BufferedImage in, Vec2 factor) {
        //Output image
        BufferedImage scaled = new BufferedImage((int) (in.getWidth(null)*factor.x),
                                                 (int) (in.getHeight(null)*factor.y),
                                                 BufferedImage.TYPE_INT_RGB);

        // Create AffineTransform
        AffineTransform at = new AffineTransform();

        //Scale
        at.scale(factor.x, factor.y);
        AffineTransformOp scaleOp = new AffineTransformOp(at, AffineTransformOp.TYPE_BICUBIC);
        scaled = scaleOp.filter(in, scaled);

        return scaled;
    }
}
