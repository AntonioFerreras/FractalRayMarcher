package graphics;

import calculations.RayMarcher;

import java.awt.*;
import java.util.concurrent.CountDownLatch;

public class RenderTask implements Runnable {
    private int startColumn, endColumn;
    private CountDownLatch latch;


    RenderTask(int start, int end, CountDownLatch latch) {
        startColumn = start;
        endColumn = end;
        this.latch = latch;
    }

    @Override
    public void run() {

        for(int x = startColumn; x < endColumn; x += 1) {
            for(int y = 0; y < Main.renderHeight; y += 1) {

                //Find pixel color
                Color color = RayMarcher.getPixelColour(x, Main.renderHeight - y);

                //Add to final render image
                Main.renderImage.setRGB(x, y, color.getRGB());
            }
        }

        //Tell countdown that this thread is finished rendering
        latch.countDown();
    }
}
