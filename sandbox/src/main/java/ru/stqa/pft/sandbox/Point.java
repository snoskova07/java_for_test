package ru.stqa.pft.sandbox;

public class Point {

  public double x;
  public double y;

  public Point(double x, double y) {
    this.x = x;
    this.y = y;
  }

// Метод вычисления расстояния
  public double distance(Point b) {
    return Math.sqrt(((b.x - this.x)*(b.x - this.x)) + ((b.y - this.y)*(b.y - this.y)));
  }

}
