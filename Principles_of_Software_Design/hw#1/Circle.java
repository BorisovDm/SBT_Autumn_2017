public class Circle implements Figure {
    private final double radius;

    Circle(double radius){
        this.radius = radius;
        System.out.println("Создан объект класса Круг с радиусом = " + radius);
    }

    @Override
    public double CalculateArea(){
        return Math.PI * radius * radius;
    }
}