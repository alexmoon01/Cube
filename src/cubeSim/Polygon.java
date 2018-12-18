package cubeSim;

import java.awt.Color;

/**
 * A colored polygon in 3d space
 * @author Alex
 */
public class Polygon implements Viewable {

  private Point3d[] points;
  private Color color;
  
  /**
   * Constructs the information of the Polygon.
   * @param points The list of points (in order around the perim of the polygon)
   * @param color The color of the polygon
   * @throws IllegalArgumentException If there are fewer than three
   * points or the points don't all lie in the same plane.
   */
  public Polygon (Point3d[] points, Color color) {
    if (points.length < 3) {
      throw new IllegalArgumentException("ERROR: "
          + "Fewer than three points passed in points[] parameter.");
    }
    if (!pointsLieInPlane(points)) {
      throw new IllegalArgumentException("ERROR: "
          + "Points don't all lie in the same plane.");
    }
    for (int i = 0; i < points.length; i++) {
      this.points[i] = points[i];
    }
    this.color = color;
  }
  
  /**
   * Getter for the list of points
   * @return The list of points
   */
  public Point3d[] getPoints() {
    return points;
  }

  /**
   * Getter for the color of the polygon
   * @return The polygon
   */
  public Color getColor() {
    return color;
  }

  /**
   * Checks if the set of points given lies in the same plane
   * @param points The set of points to be tested
   * @return Whether the points all lie in the same plane
   */
  private boolean pointsLieInPlane(Point3d[] points) {
    if (points.length <= 3) {
      return true; //Three points are the definition of a plane
    }
    Point3d v1 = Point3d.deltaVector(points[0], points[1]);
    Point3d v2 = Point3d.deltaVector(points[0], points[2]);
    //Finding a vector orthogonal to the plane
    Point3d orthVector = Point3d.crossProduct(v1, v2);
    //The starting point for the plane
    Point3d p0 = points[0];
    
    for (int i = 3; i < points.length; i++) {
      Point3d testPoint = points[i];
      //If the point does not lie within the plane, return false
      if (orthVector.X * (testPoint.X - p0.X) +
          orthVector.Y * (testPoint.Y - p0.Y) +
          orthVector.Z * (testPoint.Z - p0.Z) != 0) {
        return false;
      }
    }
    return true;
  }

  @Override
  public Polygon[] getPolygons() {
    Polygon returnable[] = new Polygon[1];
    returnable[0] = this;
    return returnable;
  }
  

  
}
