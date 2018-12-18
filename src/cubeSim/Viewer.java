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
  
  private ViewingEnvironment environ; //The place in which the viewer looks around
  
  /**
   * Instantiates basic info about the viewer. If I were to generalize
   * @param x
   * @param y
   * @param z
   * @param theta
   * @param phi
   */
  public Viewer (double x, double y, double z, double theta, double phi, ViewingEnvironment environ) {
    this.x = x;
    this.y = y;
    this.z = z;
    this.theta = theta;
    this.phi = phi;
    this.environ = environ;
  }
  
}
