package cubeSim;

import java.awt.Point;
import java.awt.Polygon;
import java.util.Comparator;

/**
 * The person who is looking at the cube
 * @author Alex
 */
public class Viewer implements Comparator<Polygon3d> {

  protected double x; //x loc relative to center of cube
  protected double y; //y loc relative to center of cube
  protected double z; //z loc relative to center of cube
  protected double theta; //Viewing polar angle (=>0 is =><1, 0, 0>
  protected double phi; //Viewing azimuthal angle (=>0 is =><0, 0, 1>)
  
  private double viewingWidth; //The lens width of the viewer's eyes
  private double viewingHeight; //The lens height of the viewer's eyes
  
  private ViewingEnvironment environ; //The place in which the viewer looks around
  
  /**
   * Instantiates basic info about the viewer.
   * http://mathworld.wolfram.com/SphericalCoordinates.html For theta and phi
   * @param x X location in the viewing environment
   * @param y Y location in the viewing environment
   * @param z Z location in the viewing environment
   * @param theta the polar angle of the viewer
   * @param phi the azimuthal angle of the viewer
   */
  public Viewer (double x, double y, double z, double theta, double phi, 
      double viewingWidth, double viewingHeight, ViewingEnvironment environ) {
    this.x = x;
    this.y = y;
    this.z = z;
    this.theta = theta;
    this.phi = phi;
    this.viewingWidth = viewingWidth;
    this.viewingHeight = viewingHeight;
    this.environ = environ;
    //Remove if you want viewer to not be fixed on center
    
  }
  
  /**
   * The fun method! Casts a polygon from 3d space into 2d.
   * @param v The viewer that the polygon will be visible to.
   * @param polygon The 3d polygon that will be transformed.
   * @return The 2d polygon that will be displayed on the screen.
   */
  public Polygon to2d(Polygon3d polygon) {
    //Casts all 3d points into 2d
    Point[] points2d = new Point[polygon.getNumPoints()];
    for (int i = 0; i < polygon.getNumPoints(); i++) {
      Point3d p3d = polygon.getPoints()[i];
      points2d[i] = to2d(p3d);
    }
    
    //Splits the points up because the polygon constructor is weird
    int[] x = new int[points2d.length];
    int[] y = new int[points2d.length];
    for (int i = 0; i < points2d.length; i++) {
      x[i] = points2d[i].x;
      y[i] = points2d[i].y;
    }
    return new Polygon(x, y, points2d.length);
  }
  
  /**
   * Casts a point from 3d space into 2d.
   * @param v The viewer that the point will be visible to/
   * @param point The 3d point that will be transformed
   * @return The 2d point that will be displayed on the screen.
   */
  public Point to2d(Point3d point) {
    
    //Finds the differences between the point and the viewer
    double distanceBetween = Point3d.distanceBetween(point, this.getPoint3d());
    double polarDistanceBetween = Math.sqrt(Math.pow(point.X - x, 2)+ Math.pow(point.Y - y, 2));
    double thetaBetween = (point.Y - y) / polarDistanceBetween;
    double phiBetween = (point.Z - z) / distanceBetween;
    
    //TODO: Figure out how to transform from default coordinate system to coordinate
    //system based on the location of the viewer
    
    //The distance away from the middle of the screen
    double distOffXMiddle = (Math.sin(theta) - thetaBetween);
    double distOffYMiddle = (Math.cos(phi) - phiBetween);
    
    //The middle of the screen
    double middleX = environ.getWidth() / 2;
    double middleY = environ.getHeight() / 2;
    
    //Determining the actual location of the point on the screen
    double screenX = middleX + (distOffXMiddle * environ.getWidth());
    double screenY = middleY + (distOffYMiddle * environ.getHeight());
    System.out.println(distOffXMiddle);
    
    return new Point((int)screenX, (int)screenY);
  }

  /**
   * @return the x
   */
  public double getX() {
    return x;
  }

  /**
   * @return the y
   */
  public double getY() {
    return y;
  }

  /**
   * @return the z
   */
  public double getZ() {
    return z;
  }

  /**
   * @return the theta
   */
  public double getTheta() {
    return theta;
  }
  
  /**
   * @return the viewing width
   */
  public double getViewingWidth() {
    return viewingWidth;
  }
  
  /**
   * @return the viewing height
   */
  public double getViewingHeight() {
    return viewingHeight;
  }

  /**
   * @return the phi
   */
  public double getPhi() {
    return phi;
  }
  
  /**
   * 3d point representation of location.
   * @return The 3d point whose coordinates are that of the viewer
   */
  public Point3d getPoint3d() {
    return new Point3d(x, y, z);
  }

  @Override
  public int compare(Polygon3d o1, Polygon3d o2) {
    Point3d o1center = o1.getOrthocenter();
    Point3d o2center = o2.getOrthocenter();
    Point3d viewerLoc = new Point3d(x, y, z);
    double d1 = Point3d.distanceBetween(viewerLoc, o1center);
    double d2 = Point3d.distanceBetween(viewerLoc, o2center);
    return (int)d2 - (int)d1;
  }
  
}
