package cubeSim;

/**
 * Static methods provide useful transformations for vectors,
 * object is a point or a vector in 3-space
 * @author Alex
 */
public class Point3d {

  /**
   * X coordinate relative to the origin of the environment
   */
  public final double X;
  /**
   * Y coordinate relative to the origin of the environment
   */
  public final double Y;
  /**
   * Z coordinate relative to the origin of the environment
   */
  public final double Z;
  
  /**
   * Sets up the information of the point
   * @param X The X coordinate relative to the origin of the environment
   * @param Y The Y coordinate relative to the origin of the environment
   * @param Z The Z coordinate relative to the origin of the environment
   */
  public Point3d(double X, double Y, double Z) {
    this.X = X;
    this.Y = Y;
    this.Z = Z;
  }
  
  /**
   * Finds the distance between two points
   * @param p1 The first point
   * @param p2 The second point
   * @return The distance between them
   */
  public static double distanceBetween(Point3d p1, Point3d p2) {
    return Math.sqrt(Math.pow(p1.X - p2.X, 2) + 
        Math.pow(p1.Y - p2.Y, 2) + Math.pow(p1.Z - p2.Z, 2));
  }
  
  /**
   * Returns the vector (represented as a Point3d)
   * which is the difference between the two points.
   * @param p1 The first point to be calculated.
   * @param p2 The second point to be calculated.
   * @return The vector that goes between the points
   */
  public static Point3d deltaVector(Point3d p1, Point3d p2) {
    return new Point3d(p1.X - p2.X, p1.Y - p2.Y, p1.Z - p2.Z);
  }
  
  /**
   * Treats the points passed as vectors and calculates
   * their cross product
   * @param v1 The first vector
   * @param v2 The second vector
   * @return The cross product of the two vectors
   */
  public static Point3d crossProduct(Point3d v1, Point3d v2) {
    return new Point3d(v1.Y * v2.Z - v1.Z * v2.Y,
        v1.Z * v2.X - v1.X * v2.Z, v1.X * v2.Y - v1.Y * v2.X);
  }
  
  @Override
  public String toString() {
    return "(" + X + ", " + Y + ", " + Z + ")";
  }
  
}
