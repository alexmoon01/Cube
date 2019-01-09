package cubeSim;

import java.util.Comparator;

/**
 * The person who is looking at the cube
 * @author Alex
 */
public class Viewer implements Comparator<Polygon3d> {

  private double x; //x loc relative to center of cube
  private double y; //y loc relative to center of cube
  private double z; //z loc relative to center of cube
  private double theta; //Viewing polar angle (=>0 is =><1, 0, 0>
  private double phi; //Viewing azimuthal angle (=>0 is =><0, 0, 1>)
  
  private double viewingWidth; //The lens width of the viewer's eyes
  private double viewingHeight; //The lens height of the viewer's eyes
  
  private ViewingEnvironment environ; //The place in which the viewer looks around
  
  /**
   * Instantiates basic info about the viewer.
   * http://mathworld.wolfram.com/SphericalCoordinates.html For theta and phi
   * @param x X location in the viewing environment
   * @param y Y location in the viewing environment
   * @param z Z location in the viewing environment
   * @param theta Polar looking angle
   * @param phi Azimuthal looking angle
   */
  public Viewer (double x, double y, double z, double theta, double phi, double viewingWidth, double viewingHeight, ViewingEnvironment environ) {
    this.x = x;
    this.y = y;
    this.z = z;
    this.theta = theta;
    this.phi = phi;
    this.viewingWidth = viewingWidth;
    this.viewingHeight = viewingHeight;
    this.environ = environ;
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
