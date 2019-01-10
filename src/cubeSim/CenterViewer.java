package cubeSim;

import java.awt.MouseInfo;
import java.awt.event.MouseEvent;

/**
 * Like a normal viewer, but automatically points
 * vision towards center and only rotates around it.
 * @author Alex
 */
public class CenterViewer extends Viewer {

  private final int VELOCITY;
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
    VELOCITY = 3;
  }
  
  public void move() {
    if (mousePressed) {
      double newMouseX = MouseInfo.getPointerInfo().getLocation().getX();
      double newMouseY = MouseInfo.getPointerInfo().getLocation().getY();
      double direc = Math.atan2(newMouseY - mouseY, newMouseX - mouseX);
      x += Math.cos(direc) * VELOCITY;
      y += Math.sin(direc) * VELOCITY;
      mouseX = newMouseX;
      mouseY = newMouseY;
    }
    setAnglesTowardsCenter();
  }
  
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
    theta = Math.atan2(-y, -x) + 2 * Math.PI;
    //Polar distance from z axis
    double r = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    phi = Math.PI - Math.atan2(r, z);
  }
  
}
