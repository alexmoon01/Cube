package cubeSim;

import java.awt.Color;

/**
 * A colored polygon in 3d space
 * @author Alex
 */
public class Polygon3d implements Viewable {

  private Point3d[] points;
  private Color color;
  
  /**
   * Constructs the information of the Polygon.
   * @param points The list of points (in order around the perim of the polygon)
   * @param color The color of the polygon
   * @throws IllegalArgumentException If there are fewer than three
   * points or the points don't all lie in the same plane.
   */
  public Polygon3d (Point3d[] points, Color color) {
    if (points.length < 3) {
      throw new IllegalArgumentException("ERROR: "
          + "Fewer than three points passed in points[] parameter.");
    }
    if (!pointsLieInPlane(points)) {
      throw new IllegalArgumentException("ERROR: "
          + "Points don't all lie in the same plane.");
    }
    this.points = new Point3d[points.length];
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
  public static boolean pointsLieInPlane(Point3d[] points) {
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
      if (points[i] == null) break;
      Point3d testPoint = points[i];
      //If the point does not lie within the plane, return false
      if (orthVector.X * 
          (testPoint.X - p0.X) +
          orthVector.Y * (testPoint.Y - p0.Y) +
          orthVector.Z * (testPoint.Z - p0.Z) != 0) {
        return false;
      }
    }
    return true;
  }
  
  /**
   * Getter for the amount of points in the polygon.
   * @return The number of points in the polygon.
   */
  public int getNumPoints() {
    return points.length;
  }
  
  /**
   * Calculates the orthocenter of the polygon
   * @return The orthocenter of the polygon
   */
  public Point3d getOrthocenter() {
    //The average of each dimension
    double xBar = 0;
    double yBar = 0;
    double zBar = 0; 
    for (int i = 0; i < points.length; i++) {
      xBar += points[i].X;
      yBar += points[i].Y;
      zBar += points[i].Z;
    }
    xBar /= points.length;
    yBar /= points.length;
    zBar /= points.length;
    return new Point3d(xBar, yBar, zBar);
  }

  @Override
  public Polygon3d[] getPolygons() {
    Polygon3d returnable[] = new Polygon3d[1];
    returnable[0] = this;
    return returnable;
  }
  
  @Override
  public String toString() {
    String asString = "{";
    for (Point3d p : points) {
      asString += p.toString() + ", ";
    }
    asString = asString.substring(0, asString.length() - 2);
    asString += "}";
    return asString;
  }
  
}
