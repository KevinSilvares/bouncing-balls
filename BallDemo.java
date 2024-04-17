import java.awt.Color;
import java.util.Random;
import java.util.HashMap;
import java.awt.Rectangle;
import java.util.Iterator;

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

    public void boxBounce (int numeroBolas){
        int bolasDibujadas = 0;
        HashMap<Integer, BoxBall> bolas = new HashMap();
        dibujarLineasRectangulo();
        Random random = new Random();
        int red = 255;
        int green = 0;
        int blue = 0;
        Color color = new Color(red, green, blue);

        while(bolasDibujadas < numeroBolas){
            BoxBall ball = new BoxBall(random.nextInt(120, 320), random.nextInt(110,280), color, myCanvas, bolasDibujadas);
            ball.draw();
            bolasDibujadas++;
            bolas.put(bolasDibujadas, ball);
        }

        boolean finish = false;
        while(finish == false){
            myCanvas.wait(50);// small delay

            for(int numeroBall : bolas.keySet()){
                BoxBall ball = bolas.get(numeroBall);
                ball.move();
                if(ball.getRebotesRestantes() <= 0){
                    ball.erase();
                    finish = true;
                    myCanvas.drawString("GAME OVER", 280, 225);                    
                }
            }
            /*if(bolas.get(bolaABorrar) != null && bolas.get(bolaABorrar).getBorrado()){
            bolas.get(bolaABorrar).erase();
            eliminarBola(bolas, bolaABorrar);
            }*/
        }
    }

    /*public void anadirBola(HashMap<Integer, BoxBall> bolasColeccion, int id){
        Iterator<BoxBall> it = bolasColeccion.values().iterator();

        while(it.hasNext()){
            if(id == it.next().getId()){
                it.remove();
            }
        }
    }*/

    public void dibujarLineasRectangulo(){
        myCanvas.drawLine(100, 100, 200,100); //techo izquierdo
        myCanvas.drawLine(250, 100, 350,100); //techo derecho
        myCanvas.drawLine(350, 100, 350, 200); //p.derecha arriba
        myCanvas.drawLine(350, 250, 350, 300); //p.derecha abajo
        myCanvas.drawLine(350, 300, 250, 300); //suelo derecho
        myCanvas.drawLine(200, 300, 100, 300); //suelo izquierdo
        myCanvas.drawLine(100, 300, 100, 250); //p.izqu abajo
        myCanvas.drawLine(100, 200, 100, 100); //p.izq arriba
    }
}

