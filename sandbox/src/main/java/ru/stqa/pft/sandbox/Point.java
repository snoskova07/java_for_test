package ru.stqa.pft.sandbox;

public class Point {

  public double x;
  public double y;

  public Point(double x, double y) {
    this.x = x;
    this.y = y;
  }

// Метод вычисления расстояния
  public static double distance(Point a, Point b) {
    return Math.sqrt(((b.x - a.x)*(b.x - a.x)) + ((b.y - a.y)*(b.y - a.y)));
  }

}
