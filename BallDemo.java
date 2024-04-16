import java.awt.Color;
import java.util.Random;
import java.util.HashMap;
import java.awt.Rectangle;

/**
 * Class BallDemo - a short demonstration showing animation with the 
 * Canvas class. 
 *
 * @author Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */

public class BallDemo   
{
    private Canvas myCanvas;

    /**
     * Create a BallDemo object. Creates a fresh canvas and makes it visible.
     */
    public BallDemo()
    {
        myCanvas = new Canvas("Ball Demo", 600, 500);
    }

    /**
     * Simulate two bouncing balls
     */
    public void bounce(int numeroBalls)
    {
        int ground = 400;   // position of the ground line

        myCanvas.setVisible(true);

        // draw the ground
        myCanvas.drawLine(50, ground, 550, ground);

        // crate and show the balls
        int ballsCreadas = 1;
        HashMap<Integer, BouncingBall> bolas = new HashMap();
        Random random = new Random();

        while (ballsCreadas <= numeroBalls){
            int red = random.nextInt(256);
            int green = random.nextInt(256);
            int blue = random.nextInt(256);
            Color color = new Color(red, green, blue);
            BouncingBall ball = new BouncingBall(random.nextInt(50-25)+25, random.nextInt(50-10)+10, random.nextInt(50-10)+10, color, ground, myCanvas);
            ball.draw();
            bolas.put(ballsCreadas, ball);
            ballsCreadas ++;
        }

        // make them bounce
        boolean finished =  false;
        while(!finished) {
            myCanvas.wait(50);// small delay

            for(int numeroBola : bolas.keySet()){
                BouncingBall bola = bolas.get(numeroBola);
                bola.move();
                // stop once ball has travelled a certain distance on x axis
                if(bola.getXPosition() >= 550){
                    finished = true;
                }
            }
        }
    }

    public void boxBounce (){
        myCanvas.draw(new Rectangle(100, 100, 400, 250));
        Random random = new Random();
        int red = 255;
        int green = 0;
        int blue = 0;
        Color color = new Color(red, green, blue);
        BoxBall ball = new BoxBall(random.nextInt(500-100)+100, random.nextInt(350-100)+100, color, myCanvas);
        ball.draw();
        boolean finish = false;
        while(finish == false){
            myCanvas.wait(50);// small delay
            ball.move();
            if(ball.getRebotesRestantes() <= 0){
                ball.erase();
                finish = true;
                myCanvas.drawString("GAME OVER", 280, 225);
                
            }
        }
    }
}

