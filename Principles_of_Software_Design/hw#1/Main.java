public class Main {
    public static void main(String[] args) {

        Rectangle rectangle = new Rectangle(3, 4);
        System.out.println("Площадь прямоугольника = " + rectangle.CalculateArea());

        Square square = new Square(5);
        System.out.println("Площадь квадрат = " + square.CalculateArea());

        Circle circle = new Circle(1.34);
        System.out.println("Площадь круга = " + circle.CalculateArea());
    }
}