package my.first.packadge;

public class MyFirstProgram {

	public static void main(String[] args) {

		hello("Anastasiya");
		hello("user");
		hello("Sweden");

		Square s = new Square(6);
		System.out.println("Площадь квадрата со стороной " + s.l + " = " + s.area());

		Rectangle r = new Rectangle(3,4);
		System.out.println("Площадь прямоугольника со сторонами " + r.a + " и " + r.b + " = " + r.area());


		Point p1 = new Point(7,12);
		Point p2 = new Point(18,25);
		System.out.println("Расстояние между точками А(" + p1.x + "," + p1.y + ") и В(" + p2.x + "," + p2.y + ") = " + Point.distance(p1, p2));


	}

	public static void hello(String somebody) {
		System.out.println("Hello, " + somebody + "!");
	}

}