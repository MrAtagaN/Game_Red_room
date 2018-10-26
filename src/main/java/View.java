import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;

/**
 * Created by AtagaN on 11.04.2018.
 */
public class View extends JPanel {
    private static final Color BG_COLOR = new Color(0x000000);
    private static final Color RED = new Color(145, 5, 0);
    Controller controller;
    int t = 0;


    public View(Controller controller) {

        addKeyListener(controller);
        setFocusable(true);
        this.controller = controller;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;

        //paint background
        g2.setColor(BG_COLOR);
        Rectangle rectangle = new Rectangle(0, 0, Model.getWeigh(), Model.getHeight());
        g2.fill(rectangle);
        g2.draw(rectangle);

        //paint Press Space
        if (!controller.isStartGame()) {
            g2.setColor(new Color(160, 165, 164));
            g2.drawString("Press Space", 215, 220);
        }


        //paint triangles
        for (int i = 0; i < 500; i += 20) {
            Path2D triangelTop = new Path2D.Double();
            double firstX = i;
            double firstY = 0;

            triangelTop.moveTo(firstX, firstY);
            triangelTop.lineTo(20 + firstX, 0 + firstY);
            triangelTop.lineTo(10 + firstX, 20 + firstY);
            triangelTop.closePath();

            g2.setColor(RED);
            g2.fill(triangelTop);
            g2.draw(triangelTop);

            Path2D triangleBottom = new Path2D.Double();
            double firstX2 = i;
            double firstY2 = 571;

            triangleBottom.moveTo(firstX2, firstY2);
            triangleBottom.lineTo(20 + firstX2, 0 + firstY2);
            triangleBottom.lineTo(10 + firstX2, firstY2 - 20);
            triangleBottom.closePath();

            g2.fill(triangleBottom);
            g2.draw(triangleBottom);

        }

        //paint left spikes
        boolean[] leftspikes = controller.getLeftSpikes();
        for (int i = 0; i < leftspikes.length; i++) {
            if (leftspikes[i]) {
                Path2D spike = new Path2D.Double();
                spike.moveTo(0, 50 + i * 45);
                spike.lineTo(0, 70 + i * 45);
                spike.lineTo(20, 60 + i * 45);
                spike.closePath();
                g2.fill(spike);
                g2.draw(spike);
            }
        }

        //paint right spikes
        boolean[] rightspikes = controller.getRightSpikes();
        for (int i = 0; i < rightspikes.length; i++) {
            if (rightspikes[i]) {
                if (rightspikes[i]) {
                    Path2D spike = new Path2D.Double();
                    spike.moveTo(500, 50 + i * 45);
                    spike.lineTo(500, 70 + i * 45);
                    spike.lineTo(480, 60 + i * 45);
                    spike.closePath();
                    g2.fill(spike);
                    g2.draw(spike);
                }
            }
        }

        //paint Ball
        Ellipse2D ball = new Ellipse2D.Double(controller.getX(), controller.getY(), 40, 40);
        g2.setColor(new Color(0, 145, 20));
        g2.fill(ball);
        g2.draw(ball);

        //paint Candy
        if (controller.isCandy()) {
            Ellipse2D candy = new Ellipse2D.Double(controller.getCandyX(), controller.getCandyY(), 6, 6);
            g2.setColor(new Color(159, 4, 165));
            g2.fill(candy);
            g2.draw(candy);
        }

        //paint Game Over
        if (controller.isGameOver()) {
            g2.setColor(RED);
            g2.drawString("Game Over", 215, 250 - t);
            if (t <= 100) {
                t += 1;
            }
        } else {
            t = 0;
        }
        //paint Win
        if (controller.isWin()) {
            g2.setColor(RED);
            g2.drawString("You Win !!!", 215, 250);
        }

        //paint Score
        g2.setColor(RED);
        g2.drawString("Score " + controller.getScore(), 5, 545);


    }
}
