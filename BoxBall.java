import java.awt.*;
import java.awt.geom.*;
import java.util.Random;

/**
 * Class BouncingBall - a graphical ball that observes the effect of gravity. The ball
 * has the ability to move. Details of movement are determined by the ball itself. It
 * will fall downwards, accelerating with time due to the effect of gravity, and bounce
 * upward again when hitting the ground.
 *
 * This movement can be initiated by repeated calls to the "move" method.
 * 
 * @author Michael Kölling (mik)
 * @author David J. Barnes
 * @author Bruce Quig
 *
 * @version 2011.07.31
 */

public class BoxBall
{
    private static int bolasMuertas = 0;
    private static final int diameter = 30;  // effect of gravity

    private Ellipse2D.Double circle;
    private Color color;
    private int xPosition;
    private int yPosition;
    private final int groundPosition;      // y position of ground
    private final int techo;
    private final int paredIz;
    private final int paredDr;
    private Canvas canvas;
    private int ySpeed = 3;   // initial downward speed
    private int xSpeed = 3;
    private int rebotesRestantes;
    private int rojo;
    private int restarColor;
    private Color nuevoColor;
    private boolean escapado;
    private Random random;

    /**
     * Constructor for objects of class BouncingBall
     *
     * @param xPos  the horizontal coordinate of the ball
     * @param yPos  the vertical coordinate of the ball
     * @param ballDiameter  the diameter (in pixels) of the ball
     * @param ballColor  the color of the ball
     * @param groundPos  the position of the ground (where the wall will bounce)
     * @param drawingCanvas  the canvas to draw this ball on
     */
    public BoxBall(int xPos, int yPos, Color ballColor, Canvas drawingCanvas, int idBola)
    {
        Random rebotesGenerador = new Random();
        random = new Random();
        rebotesRestantes = rebotesGenerador.nextInt(5,11);
        xPosition = xPos;
        yPosition = yPos;
        color = ballColor;
        nuevoColor = color;
        canvas = drawingCanvas;
        bolasMuertas = 0;
        groundPosition = 300;
        techo = 100;
        paredIz = 100;
        paredDr = 350;
        rojo = 255;
        restarColor = 255/rebotesRestantes;
        escapado = false;
    }

    /**
     * Draw this ball at its current position onto the canvas.
     **/
    public void draw()
    {
        canvas.setForegroundColor(nuevoColor);
        canvas.fillCircle(xPosition, yPosition, diameter);
        String rebotesString= Integer.toString(rebotesRestantes);
        canvas.drawString(rebotesString, (xPosition + diameter/2),(yPosition + diameter/2));
    }

    /**
     * Erase this ball at its current position.
     **/
    public void erase()
    {
        canvas.eraseCircle(xPosition, yPosition, diameter);
    }    

    /**
     * Move this ball according to its position and speed and redraw.
     **/
    public void move()
    {
        // remove from canvas at the current position
        erase();
        String rebotesString = Integer.toString(rebotesRestantes);
        canvas.eraseString(rebotesString, (xPosition + diameter/2),(yPosition + diameter/2));
        // compute new position
        yPosition += ySpeed;
        xPosition += xSpeed;
        
        //Suelo
        if(yPosition >= (groundPosition - diameter) && ySpeed > 0 && !escapado){ //compruebo que esta en el suelo
            if(xPosition >= 200 && xPosition <= 250){ //compruebo que esta en el agujero
                escapado = true;
            }else{  // si no esta en el agujero rebota
                yPosition = 299 - diameter;
                ySpeed = -ySpeed;
                rebotesRestantes--;
                restarColor();
            }
        }
        else if(yPosition >= groundPosition + 50 && escapado){
            erase();
            resetearPosicion();
            escapado = false;
        }
        
        //Pared Izquierda
        if(xPosition <= paredIz && xSpeed < 0 && !escapado){
            if(yPosition <= 250 && yPosition >= 200){
                escapado = true;
            }else{
                xPosition = 101;
                xSpeed = -xSpeed;
                rebotesRestantes--;
                restarColor();
            }
        }
        else if(xPosition <= paredIz - 50 && escapado){
            erase();
            resetearPosicion();
            escapado = false;
        }
        
        // Techo
        if(yPosition <= techo && ySpeed < 0 && !escapado){
            if(xPosition >= 200 && xPosition <= 250){
                escapado = true;
            }else{
                yPosition = 101;
                ySpeed = -ySpeed;
                rebotesRestantes--;
                restarColor();
            }
        }
        else if(yPosition <= techo - 50 && escapado){
            erase();
            resetearPosicion();
            escapado = false;
        }
        
        // Pared Derecha
        if(xPosition >= (paredDr - diameter) && xSpeed > 0 && !escapado){
            if(yPosition >= 200 && yPosition <= 250){
                escapado = true;
            }else{
                xPosition = 349 - diameter;
                xSpeed = -xSpeed;
                rebotesRestantes--;
                restarColor();
            }
        }
        else if(xPosition >= paredDr + 50 && escapado){
            erase();
            resetearPosicion();
            escapado = false;
        }
       
        // draw again at new position*/
        draw();
        }
    
    public void restarColor(){
        rojo = rojo - restarColor;
        nuevoColor = new Color(rojo, 0, 0); 
    }
    
    public void resetearPosicion(){
        xPosition = random.nextInt(120, 320);
        yPosition = random.nextInt(110,280);
    }

    /**
     * return the horizontal position of this ball
     */
    public int getXPosition()
    {
        return xPosition;
    }

    /**
     * return the vertical position of this ball
     */
    public int getYPosition()
    {
        return yPosition;
    }

    public static int getCantidadDeBolasMuertas(){
        return bolasMuertas;
    }

    public int getRebotesRestantes(){
        return rebotesRestantes;
    }
}
