package tests;

import cubeSim.*;

public class Tests {

  /**
   * Runs test methods
   */
  public static void main(String[] args) {
    boolean passed = true;
//    passed &= testCube();
    passed &= testPointsLieInPlane();
    if (passed) {
      System.out.println("All tests passed!");
    } else {
      System.out.println("Not all tests passed!");
    }
  }
  
  /**
   * Prints out the coordinates of a cube
   * @return True, tester must examine coordinates
   */
  public static boolean testCube() {
    Cube test = new Cube(6);
    System.out.println(test);
    return true;
  }
  
  /**
   * Tests {@link Polygon#pointsLieInPlane(cubeSim.Point3d[])}
   * @return True if test passed
   */
  public static boolean testPointsLieInPlane() {
    //Not coplanar
    Point3d[] test1 = new Point3d[4];
    test1[0] = new Point3d(0, 0, 0);
    test1[1] = new Point3d(1, 1, 1);
    test1[2] = new Point3d(1, 0, 1);
    test1[3] = new Point3d(1, 0, 0);
    System.out.println(Polygon.pointsLieInPlane(test1));
    //Coplanar
    Point3d[] test2 = new Point3d[4];
    test2[0] = new Point3d(0, 0, 0);
    test2[1] = new Point3d(1, 1, 1);
    test2[2] = new Point3d(1, 0, 1);
    test2[3] = new Point3d(2, 2, 2);
    System.out.println(Polygon.pointsLieInPlane(test2));
    return !Polygon.pointsLieInPlane(test1) &&
        Polygon.pointsLieInPlane(test2);
  }
}
