import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Created by AtagaN on 11.04.2018.
 */
public class Controller extends KeyAdapter {

    private View view;
    private Model model;
    public boolean startGame = false;

    public Controller(Model model) {
        View view = new View(this);
        view.addKeyListener(this);
        this.view = view;
        this.model = model;
        view.repaint();
    }

    public View getView() {
        return this.view;
    }

    public int getX() {
        return model.getX();
    }

    public int getY() {
        return model.getY();
    }

    public boolean[] getLeftSpikes() {
        return model.getLeftSpikes();
    }

    public boolean[] getRightSpikes() {
        return model.getRightSpikes();
    }

    public boolean isGameOver() {
        return model.isGameOver();
    }

    public boolean isStartGame() {
        return startGame;
    }

    public boolean isWin() {
        return model.isWin();
    }

    public int getCandyX() {
        return model.getCandyX();
    }

    public int getCandyY() {
        return model.getCandyY();
    }

    public boolean isCandy() {
        return model.isCandy();
    }

    public int getScore() {
        return model.getScore();
    }


    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            if (!model.isGameGoing()) {
                try { //Это фикс бага, чтобы не создалось 2 нити с игрой, когда первая игра не завершилась
                    model.getGameThread().join();
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }    //
                startGame = true;
                model.start();
                model.jump();
            } else {
                model.jump();
            }
        }
    }

    public void paintGraphic() {
        Thread thread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                view.repaint();
            }
        });
        thread.setDaemon(true);
        thread.start();
    }

    public void startGame() {
        this.paintGraphic();
    }


}
