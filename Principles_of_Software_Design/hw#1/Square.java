public class Square implements Figure {
    private final double length;

    Square(double length){
        this.length = length;
        System.out.println("Создан объект класса Квадрат с длиной = " + length);
    }

    @Override
    public double CalculateArea(){
        return length * length;
    }
}