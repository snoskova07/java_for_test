package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointSuccessTests {

  @Test
  public void testPointSuccess() {
    Point a = new Point (2,2);
    Point b = new Point (2,5);
    Assert.assertEquals(a.distance(b), 3.0);
  }
}

