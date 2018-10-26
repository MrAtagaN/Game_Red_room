/**
 * Created by AtagaN on 11.04.2018.
 */
public class Model {

    private static final int height = 600;
    private static final int weigh = 500;
    private int gravity = 650;
    private int speedY = 0;
    private int speedX = 5;
    private volatile boolean[] leftSpikes = new boolean[11];
    private volatile boolean[] rightSpikes = new boolean[11];
    private int countOfRightSpikes ;
    private int countOfLeftSpikes ;
    private boolean win = false;
    private int candyX ;
    private int candyY;
    private volatile boolean candy=false;
    private int score = 0;

    private volatile boolean gameGoing = false;
    private volatile boolean gameOver = false;
    private volatile Thread gameThread = new Thread();

    private int x = weigh/2 - 20;
    private int y = height /2 - 40;

    public static int getHeight() {
        return height;
    }

    public static int getWeigh() {
        return weigh;
    }

    public  int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void jump(){
        speedY=-570 ;
    }

    private void move(){

        gameThread = new Thread( ()->{
           while (gameGoing){
               x+=speedX;
               y+= speedY /50 + gravity/100;
               speedY +=gravity/50;

               if(x>=460 || x<=0){
                   if(speedX>0){
                       speedX=-speedX;
                       addLeftSpike();
                       score++;
                       countOfLeftSpikes+=1;
                   }
                   else {
                       speedX=-speedX;
                       addRightSpike();
                       score++;
                       countOfRightSpikes+=1;
                   }
               }
               checkCandy();
               chekCrush();
               //заменить методом isCrash
               if(y<=20 || y>=510){
                   gameGoing=false;
                   gameOver=true;
               }

               try {
                   Thread.sleep(20);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }

           }
       });
        gameThread.setDaemon(true);
        gameThread.start();
    }


    public void start(){
        this.gameGoing = true;
        this.gameOver=false;
        leftSpikes= new boolean[11];
        rightSpikes = new boolean[11];
        countOfLeftSpikes = 2;
        countOfRightSpikes = 2;
        win=false;
        candy=false;
        candyY=0;
        candyX=0;
        score=0;

        x = weigh/2 - 20;
        y = height /2 - 40;
        speedY=0;
        speedX=5;
        this.move();

    }

    private void checkCandy(){
        if( (Math.abs(candyX-(x+20 )) <=19) &&(Math.abs(candyY-(y+20 )) <=19   ) ){
            candy=false;
            candyX=0;
            candyY=0;
            removeRightSpikes();
            removeLeftSpikes();
        }
    }

    private void removeLeftSpikes(){
        //перебор массива со случайного элемента
        int t = (int) (Math.random() * 11);
        for(int i=t; ; i++ ){
            if(leftSpikes[i]==true){
                leftSpikes[i]=false;
                break;
            }
            if(i>=10){
                i=0;
            }
        }
    }

    private void removeRightSpikes(){
        //перебор массива со случайного элемента
        int t = (int) (Math.random() * 11);
        for(int i=t; ; i++ ){
            if(rightSpikes[i]==true){
                rightSpikes[i]=false;
                break;
            }
            if(i>=10){
                i=0;
            }
        }
    }

    private void chekCrush(){
        for(int i=0; i<leftSpikes.length ; i++){
            if(leftSpikes[i]){
                if( (Math.abs(20-(x+20 )) <=19) &&(Math.abs(60+i*45-(y+20 )) <=19  ) ){
                    gameGoing=false;
                    gameOver=true;
                }
            }
        }

        for(int i=0; i<rightSpikes.length ; i++){
            if(rightSpikes[i]){
                if( (Math.abs(480-(x+20 )) <=19) &&(Math.abs(60+i*45-(y+20 )) <=19 ) ){
                    gameGoing=false;
                    gameOver=true;
                }
            }
        }

        if( (Math.abs(candyX-(x+20 )) <=20) &&(Math.abs(candyY-(y+20 )) <=20   ) ){

        }
    }





    private void addCandy(){
        candy=true;
        candyX=((int)(Math.random()*300))+100;
        candyY=((int)(Math.random()*400))+100;
    }

    private void addLeftSpike(){
        if (countOfLeftSpikes%2==0){

            int x = (int)(Math.random()*11);
            if(this.leftSpikes[x]==true){
                addLeftSpike();

            }
            else {
                this.leftSpikes[x]=true;
            }
        }


        //появление конфеты
        if(countOfLeftSpikes%7==0){
           addCandy();
        }
        //проверка победы
        boolean flag = true;
        for(boolean b : leftSpikes){
            if(b==false){
                flag=false;
            }
        }
        if(flag==true){
            win=true;
            gameGoing=false;
        }
    }

    private void addRightSpike(){
        if (countOfRightSpikes%2==0) {
            int x = (int) (Math.random() * 11);
            if (this.rightSpikes[x] == true) {
                addRightSpike();

            } else {
                this.rightSpikes[x] = true;
            }
        }


        //проверка победы
        boolean flag = true;
        for(boolean b : rightSpikes){
            if(b==false){
                flag=false;
            }
        }
        if(flag==true){
            win=true;
            gameGoing=false;
        }
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public boolean isGameGoing() {
        return gameGoing;
    }

    public boolean[] getLeftSpikes() {
        return leftSpikes;
    }

    public boolean[] getRightSpikes() {
        return rightSpikes;
    }

    public Thread getGameThread() {
        return gameThread;
    }

    public boolean isWin() {
        return win;
    }

    public int getCandyX() {
        return candyX;
    }

    public int getCandyY() {
        return candyY;
    }

    public boolean isCandy() {
        return candy;
    }

    public int getScore() {
        return score;
    }
}
