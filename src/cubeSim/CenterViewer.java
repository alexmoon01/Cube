package cubeSim;

import java.awt.MouseInfo;
import java.awt.event.MouseEvent;

/**
 * Like a normal viewer, but automatically points
 * vision towards center and only rotates around it.
 * @author Alex
 */
public class CenterViewer extends Viewer {

  private final double DIST_FROM_ORIGIN;
  private boolean mousePressed;
  private double mouseX, mouseY;
  
  /**
   * Defines the basic parameters of the viewer, with
   * viewing angles automatically defined
   * @param x the 3 space x
   * @param y the 3 space y
   * @param z the 3 space z
   * @param viewingWidth the focal width of the viewer's eye
   * @param viewingHeight the focal height of the viewer's eye
   * @param environ the viewing environment
   */
  public CenterViewer(double x, double y, double z, double viewingWidth, double viewingHeight, ViewingEnvironment environ) {
    //0's are placeholders for angles which will be set in the setAngles method
    super(x, y, z, 0, 0, viewingWidth, viewingHeight, environ);
    setAnglesTowardsCenter();
    DIST_FROM_ORIGIN = Point3d.distanceBetween(this.getPoint3d(), new Point3d(0, 0, 0));
  }
  
  public void move() {
    if (mousePressed) {
      double newMouseX = MouseInfo.getPointerInfo().getLocation().getX();
      double newMouseY = MouseInfo.getPointerInfo().getLocation().getY();
      double direc = Math.atan2(mouseY - newMouseY, mouseX - newMouseX);
      //Approx because of the adjustment in resetToDistance
      double approxDistanceMoved = Math.sqrt(Math.pow(mouseY - newMouseY, 2) + 
          Math.pow(mouseX - newMouseX, 2));
      //Reasoning:
      //<x, y, z> is a vector which is orthogonal to our movement (sphere property) aka
      //<x, y, z> dot <delx, dely, delz> = 0
      //||delx, dely, delz|| = VELOCITY
      //delx = <trig> * VELOCTIY
      //dely = <trig> * VELOCITY
      //delz = <trig> * VELOCITY
      //delz<trig> = cos(phi) * sin(direc)
      //delx<trig> = sin(phi) * sin(theta) * cos(direc)
      //dely<trig> = sin(phi) * cos(theta) * cos(direc)
      x += Math.sin(phi) * Math.sin(theta) * Math.cos(direc) * approxDistanceMoved;
      y += Math.sin(phi) * Math.cos(theta) * Math.cos(direc) * approxDistanceMoved;
      z += Math.cos(phi) * Math.sin(direc) * approxDistanceMoved;
      mouseX = newMouseX;
      mouseY = newMouseY;
      System.out.println(this.getPoint3d());
      setAnglesTowardsCenter();
      resetToDistance();
    }
    
  }
  
  /**
   * Resets the viewer's distance from the center to what it initially was
   * (preserves angles)
   */
  public void resetToDistance() {
    x = -Math.cos(theta) * Math.sin(phi) * DIST_FROM_ORIGIN;
    y = -Math.sin(theta) * Math.sin(phi) * DIST_FROM_ORIGIN;
    z = -Math.cos(phi) * DIST_FROM_ORIGIN;
  }
  
  /**
   * Sets the mousepressed boolean to the parameter
   * @param b what mousePressed will be
   */
  public void toggleMousePressed(boolean b) {
    mousePressed = b;
    if (mousePressed) {
      mouseX = MouseInfo.getPointerInfo().getLocation().getX();
      mouseY = MouseInfo.getPointerInfo().getLocation().getY();
    }
  }
  
  /**
   * Sets the angles of the viewer such that they point directly
   * towards the center of the environment.
   */
  public void setAnglesTowardsCenter() {
    theta = Math.atan2(-y, -x);
    //Distance from origin
    double r = Point3d.distanceBetween(this.getPoint3d(), new Point3d(0, 0, 0));
    phi = Math.PI - Math.acos(z / r);
  }
  
}
