package my.first.packadge;

public class MyFirstProgram {

	public static void main(String[] args) {

		hello("Anastasiya");
		hello("user");
		hello("Sweden");

		double n = 5;
		System.out.println("Площадь квадрата со стороной " + n + " = " + area(n));

		double a = 9;
		double b = 3;
		System.out.println("Площадь прямоугольника со сторонами " + a + " и " + b + " = " + area(a, b));
	}

	public static void hello(String somebody) {
		System.out.println("Hello, " + somebody + "!");
	}

	public static double area(double len) {
		return len * len;
	}

	public static double area(double a, double b) {
		return a * b;
	}
}