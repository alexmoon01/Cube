package cubeSim;

import java.util.ArrayList;

/**
 * The viewing environment and driver
 * @author Alex
 */
public class ViewingEnvironment {

  private ArrayList<Viewable> objects;
  
  public ViewingEnvironment() {
    objects = new ArrayList<Viewable>();
  }
  
  public void addViewable(Viewable v) {
    objects.add(v);
  }
  
  public static void main(String[] args) {
    
  }
  
}
