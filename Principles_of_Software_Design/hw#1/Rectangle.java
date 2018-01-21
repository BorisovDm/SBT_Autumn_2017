public class Rectangle implements Figure {
    private final double length;
    private final double width;

    Rectangle(double length, double width){
        this.length = length;
        this.width = width;
        System.out.println("Создан объект класса Прямоугольник с длиной = " + length + " и шириной = " + width);
    }

    @Override
    public double CalculateArea(){
        return length * width;
    }
}