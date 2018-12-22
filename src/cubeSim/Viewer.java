package cubeSim;

/**
 * The person who is looking at the cube
 * @author Alex
 */
public class Viewer {

  private double x; //x loc relative to center of cube
  private double y; //y loc relative to center of cube
  private double z; //z loc relative to center of cube
  private double theta; //Viewing polar angle (=>0 is =><1, 0, 0>
  private double phi; //Viewing azimuthal angle (=>0 is =><0, 0, 1>)
  
  private int viewingAngle; //The lens width of the viewer's eyes
  
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
  public Viewer (double x, double y, double z, double theta, double phi, int viewingAngle, ViewingEnvironment environ) {
    this.x = x;
    this.y = y;
    this.z = z;
    this.theta = theta;
    this.phi = phi;
    this.viewingAngle = viewingAngle;
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
   * @return the viewing angle
   */
  public int getViewingAngle() {
    return viewingAngle;
  }

  /**
   * @return the phi
   */
  public double getPhi() {
    return phi;
  }
  
}
