package tests;

import cubeSim.Cube;

public class Tests {

  public static void main(String[] args) {
    testCube();
  }
  
  public static boolean testCube() {
    Cube test = new Cube(6);
    System.out.println(test);
    return true;
  }
}
