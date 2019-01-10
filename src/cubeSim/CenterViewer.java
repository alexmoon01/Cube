package cubeSim;

/**
 * Like a normal viewer, but automatically points
 * vision towards center and only rotates around it.
 * @author Alex
 */
public class CenterViewer extends Viewer {

  /**
   * Defines the basic parameters of the viewer, with
   * viewing angles automatically defined
   * @param x
   * @param y
   * @param z
   * @param viewingWidth
   * @param viewingHeight
   * @param environ
   */
  public CenterViewer(double x, double y, double z, double viewingWidth, double viewingHeight, ViewingEnvironment environ) {
    //0's are placeholders
    super(x, y, z, 0, 0, viewingWidth, viewingHeight, environ);
    setAnglesTowardsCenter();
  }
  
  /**
   * Sets the angles of the viewer such that they point directly
   * towards the center of the environment.
   */
  public void setAnglesTowardsCenter() {
    theta = Math.atan2(-y, -x) + 2 * Math.PI;
    //Polar distance from z axis
    double r = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    phi = Math.PI - Math.atan2(r, z);
  }
  
}
