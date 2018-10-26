
import javax.swing.*;
import java.awt.*;


/**
 * Created by AtagaN on 11.04.2018.
 */
public class Game  {
    public static void main(String[] args) {

        Model model = new Model();
        Controller controller = new Controller(model);


        JFrame jFrame = new JFrame();

        jFrame.setSize(507,600);
        jFrame.setTitle("Red room");
        jFrame.setResizable(false);
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.add(controller.getView());

        controller.startGame();


    }

}
