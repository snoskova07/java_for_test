package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointFailedTests {

  @Test
  public void testPointSuccess() {
    Point c = new Point(2, 2);
    Point d = new Point(2, 5);
    Assert.assertEquals(c.distance(d), 8.0);
  }
}
