import java.awt.Color;
import java.util.Random;
import java.util.HashMap;

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
        int ballsCreadas = 0;
        HashMap<Integer, BouncingBall> bolas = new HashMap();
        Random random = new Random();
        
        while (ballsCreadas < numeroBalls){
            int red = random.nextInt(256);
            int green = random.nextInt(256);
            int blue = random.nextInt(256);
            Color color = new Color(red, green, blue);
            BouncingBall ball = new BouncingBall(50, 50, random.nextInt(50-10)+10, color, ground, myCanvas);
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
}
