package cubeSim;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

/**
 * The viewing environment and driver
 * @author Alex
 */
public class ViewingEnvironment {

  private ArrayList<Viewable> objects;
  private Viewer v;
  
  private int width;
  private int height;
  
  /**
   * Driver method, displays the viewing environment.
   * @param args
   */
  public static void main(String[] args) {
    ViewingEnvironment environ = new ViewingEnvironment(500, 500);
    environ.addViewable(new Cube(10));
    //Adds a new viewer, pointing directly towards the origin
    Viewer v = new CenterViewer(-20, 60, 40, Math.PI / 4, Math.PI / 4, environ);
    environ.addViewer(v);
    
    JFrame encaps = new JFrame("Cube!");
    JPanel drawer = new JPanel() {
      @Override
      public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, environ.getWidth(), environ.getHeight());
        Polygon[] displayable = environ.getDisplayablePolygons();
        int i = 0;
        for (Polygon p: displayable) {
          //Sets a unique color for each face
          g.setColor(new Color(i * 20, 255 - i * 20, i * 10));
          i++;
          g.fillPolygon(p);
        }
      }
    };
    //Sets up the drawable panel within the jframe
    encaps.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    encaps.add(drawer);
    drawer.setPreferredSize(new Dimension(environ.getWidth(), environ.getHeight()));
    encaps.setSize(drawer.getPreferredSize());
    encaps.setVisible(true);
    drawer.repaint();
    
    //Sets up the mouse listener so the viewer can rotate around the cube
    MouseListener mousey = new MouseAdapter() {
      @Override
      public void mousePressed(MouseEvent e) {
        //TODO
      }
    };
  }
  
  /**
   * Instantiates an empty viewing environment
   */
  public ViewingEnvironment(int width, int height) {
    objects = new ArrayList<Viewable>();
    
    this.width = width;
    this.height = height;
  }
  
  /**
   * Returns a list of polygons in the order in which they should be displayed
   * @return List of all polygons in the environment
   */
  public Polygon[] getDisplayablePolygons() {
    Polygon3d[] p = getPolygonsSortedByDistance();
    Polygon[] polygons = new Polygon[p.length];
    for (int i = 0; i < p.length; i++) {
      polygons[i] = to2d(p[i]);
    }
    return polygons;
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
    //Removes the need for excessive get calls
    double thetaViewer = v.getTheta();
    double phiViewer = v.getPhi();
    
    //Finds the differences between the point and the viewer
    double distanceBetween = Math.sqrt(Math.pow(point.X - v.getX(), 2) + Math.pow(point.Y - v.getY(), 2));
    double thetaBetween = Math.atan2(point.Y - v.getY(), point.X - v.getX());
    double phiBetween = Math.atan2(distanceBetween, (point.Z - v.getZ()));
    
    //The distance away from the middle of the screen
    double angleOffXMiddle = thetaViewer - thetaBetween - 2 * Math.PI;
    double angleOffYMiddle = phiBetween - phiViewer;
    
    //The middle of the screen
    double middleX = this.width / 2;
    double middleY = this.height / 2;
    
    //Determining the actual location of the point
    double screenX = middleX + width * (angleOffXMiddle / v.getViewingWidth());
    double screenY = middleY + height * (angleOffYMiddle / v.getViewingHeight());
    
    return new Point((int)screenX, (int)screenY);
  }
  
  /**
   * Sorts all polygons in the environment by their distance away from the 
   * viewer, farthest to closest.
   * @return The sorted array
   */
  public Polygon3d[] getPolygonsSortedByDistance() {
    ArrayList<Polygon3d> polygons = new ArrayList<Polygon3d>();
    for (Viewable v : objects) {
      for (Polygon3d p : v.getPolygons()) {
        polygons.add(p);
      }
    }
    Polygon3d[] returnable = polygons.toArray(new Polygon3d[polygons.size()]);
    Arrays.sort(returnable, v);
    return returnable;
  }
  
  public void addViewer(Viewer v) {
    this.v = v;
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

  /**
   * @return the width
   */
  public int getWidth() {
    return width;
  }

  /**
   * @return the height
   */
  public int getHeight() {
    return height;
  }
  
  
  
}
