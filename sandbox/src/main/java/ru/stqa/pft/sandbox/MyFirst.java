package ru.stqa.pft.sandbox;

public class MyFirst {

  public static void main(String[] args) {

// Примеры из лекций
    Square s = new Square(5);
    System.out.println("Площадь квадрата со стороной " + s.l + " = " + s.area());

    Rectangle r = new Rectangle(4, 6);
    System.out.println("Площадь прямоугольника со сторонами " + r.a + " и " + r.b + " = " + r.area());

// Задание 2. Вычисление расстояния между двумя точками
    Point a = new Point (2,2);
    Point b = new Point (2,5);
    System.out.println("Расстояние между двумя точками: " + Point.distance(a, b));

  }

// Функция вычисления расстояния
//  public static double distance(Point a, Point b) {
//    return Math.sqrt(((b.x - a.x)*(b.x - a.x)) + ((b.y - a.y)*(b.y - a.y)));
//  }
}