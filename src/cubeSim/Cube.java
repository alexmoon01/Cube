package cubeSim;

import java.awt.Color;
import javax.swing.JComponent;

/**
 * The cube class. This will contain all of the information and the draw method
 * for a cube centered on zero.
 * @author Alex
 */
public class Cube extends JComponent implements Viewable {
  private final Polygon[] polygons;
  
  /**
   * Instantiates the attributes of the cube centered on zero
   * @param sideLength The length of a side of the cube
   */
  public Cube(int sideLength) {
    polygons = new Polygon[6];
    for (int i = 0; i < polygons.length; i++) {
      Color color = new Color(252 - 42 * i, 42 * i, (int)(Math.random() * 256));
      int constantPlane; //The corner that is held constant while the others vary to make a square
      int varPlane1; //variable corners
      int varPlane2; //variable corners
      int alternator = (i % 2) * 2 - 1; //Alternates the fixed point's sign
      Point3d[] points = new Point3d[4]; //The set of points that will be passed into the sides
      //Assigning the correct corners to hold still
      if (i <= 2) {
        constantPlane = 0;
        varPlane1 = 1;
        varPlane2 = 2;
      } else if (i <= 4) {
        constantPlane = 1;
        varPlane1 = 0;
        varPlane2 = 2;
      } else {
        constantPlane = 2;
        varPlane1 = 0;
        varPlane2 = 1;
      }
      //Going through the corners in order around the perimeter
      for (int j = -1; j <= 1; j += 2) {
        for (int k = j; k <= 1 && k >= -1; k += -2 * j) {
          int[] coordinates = new int[3];
          coordinates[constantPlane] = alternator * sideLength;
          coordinates[varPlane1] = j * sideLength;
          coordinates[varPlane2] = k * sideLength;
          points[((j + 1) / 2) + (Math.abs(k - j) / 2)] =
              new Point3d(coordinates[0], coordinates[1], coordinates[2]);
        }
      }
      //Adding the square to the set of polygons
      polygons[i] = new Polygon(points, color);
      
    }
  }

  @Override
  public Polygon[] getPolygons() {
    return polygons;
  }
  
}
