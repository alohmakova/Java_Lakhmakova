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


		Point p1 = new Point(5,5);
		Point p2 = new Point(0,5);
		System.out.println("Расстояние между точками А(" + p1.x + "," + p1.y + ") и В(" + p2.x + "," + p2.y + ") = " + p1.distance(p2));

		Equation e = new Equation(1,5,6);
		if (e.rootNumber() == 0) {

			System.out.println("Уравнение имеет " + e.rootNumber() +  " решений");
		} else {
			if (e.rootNumber() == 1) {
				System.out.println("Уравнение имеет " + e.rootNumber() +  " решение");
			} else {
				System.out.println("Уравнение имеет " + e.rootNumber() +  " решения");
			}
		}


	}

	public static void hello(String somebody) {
		System.out.println("Hello, " + somebody + "!");
	}

}