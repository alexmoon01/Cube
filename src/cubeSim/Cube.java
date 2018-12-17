package cubeSim;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JComponent;

/**
 * The cube class. This will contain all of the information and the draw method
 * for the cube.
 * @author Alex
 */
public class Cube extends JComponent {
  private final int SIDE_LENGTH; //The length in pixels of each side of the cube
  private final Color[] FACE_COLOR; //An array of the colors of the faces of the cube
  
  /**
   * Location of the viewer in spherical coordinates with the origin
   * at the center of the cube. {radius, polar angle, azimuthal angle}
   */
  private double[] viewerLoc;
  
  /**
   * Instantiates the attributes of the cube
   * @param side The side length in pixels of the cube
   */
  public Cube(int side) {
    SIDE_LENGTH = side;
    
    FACE_COLOR = new Color[6];
    //Assigns six distinct colors to the sides of the cube
    for (int i = 0; i < FACE_COLOR.length; i++) {
      FACE_COLOR[i] = new Color(42 * i, 255 - 42 * i, (int)(Math.random() * 255));
    }
    
    viewerLoc = new double[3];
  }
  
  /**
   * Draws the cube on the graphics environment.
   * @param g The canvas on which we will draw.
   */
  public void draw(Graphics g) {
    
  }
}
