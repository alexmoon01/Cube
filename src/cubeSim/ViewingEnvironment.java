package cubeSim;

import java.util.ArrayList;

/**
 * The viewing environment and driver
 * @author Alex
 */
public class ViewingEnvironment {

  private ArrayList<Viewable> objects;
  
  /**
   * Instantiates an empty viewing environment
   */
  public ViewingEnvironment() {
    objects = new ArrayList<Viewable>();
  }
  
  /**
   * Adds a viewable object to the environment
   * @param v The viewable object
   */
  public void addViewable(Viewable v) {
    objects.add(v);
  }
  
  /**
   * Getter for the list of viewable objects in the environment
   * @return The list of viewable objects in the environment
   */
  public ArrayList<Viewable> getViewableObjects() {
    return objects;
  }
  
  public static void main(String[] args) {
    
  }
  
}
