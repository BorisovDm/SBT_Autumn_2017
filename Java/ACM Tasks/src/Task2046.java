import java.util.Scanner;

public class Task2046 {
    public static void main(String[] args) {
        final int fieldSize = 10;
        Scanner in = new Scanner(System.in);
        final int numberOfFields = in.nextInt();

        for (int i = 0; i < numberOfFields; i++) {
            char[][] field = new char[fieldSize][fieldSize];

            for (int line = 0; line < fieldSize; line++) {
                field[line] = in.next().toCharArray();
            }
            if(i < numberOfFields - 1) in.nextLine();

            if(isFieldCorrect(field) && isCountShipsCorrect(field))
                System.out.println("YES");
            else
                System.out.println("NO");
        }
    }

    public static boolean isFieldCorrect(char field[][]){
        final int height = field.length;
        final int width = field[0].length;

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if(field[i][j] == '*'){
                    if(i > 0){
                        if(j > 0 && field[i - 1][j - 1] == '*') return false;
                        if(j < width - 1 && field[i - 1][j + 1] == '*') return false;
                    }
                    if(i < height - 1){
                        if(j > 0 && field[i + 1][j - 1] == '*') return false;
                        if(j < width - 1 && field[i + 1][j + 1] == '*') return false;
                    }
                }
            }
        }
        return true;
    }

    public static boolean isCountShipsCorrect(char field[][]){
        final int height = field.length;
        final int width = field[0].length;
        int[] shipsArray = {4, 3, 2, 1};

        //ищем однопалубники и горизонтальные корабли
        for (int i = 0; i < height; i++) {
            int j = 0;
            while(j < width){
                //есть ли в данной точке корабль?
                if(field[i][j] == '0') j++;
                else{
                    //продолжение корабля сверху или снизу? если корабль вертикальный
                    if(i > 0 && field[i - 1][j] == '*' || i < height - 1 && field[i + 1][j] == '*') j++;
                    else{
                        //корабль однопалубный?
                        if(j < width - 1 && field[i][j + 1] == '0'){
                            shipsArray[1 - 1]--;
                            field[i][j] = '0';
                            j += 2;
                        }
                        //корабль длиньше одной клетки
                        else{
                            int length = 1;
                            field[i][j] = '0';
                            j++;
                            while(j < width){
                                if(field[i][j] == '0') break;
                                length++;
                                field[i][j] = '0';
                                j++;
                            }
                            if(length > 4) return false;
                            shipsArray[length - 1]--;
                            j++;
                        }
                    }
                }
            }
        }

        //однопалубники и горизонтальные корабли уже удалены из массива
        //ищем вертикальные корабли длинной более одной клетки
        for (int j = 0; j < width; j++) {
            int i = 0;
            while(i < height){
                //есть ли в данной точке корабль?
                if(field[i][j] == '0') i++;
                else{
                    //но мы то уже знаем, что продолжение корабля должно быть ниже
                    //затирать корабли уже не зачем
                    int length = 1;
                    i++;
                    while(i < height){
                        if(field[i][j] == '0') break;
                        length++;
                        i++;
                    }
                    if(length > 4) return false;
                    shipsArray[length - 1]--;
                    i++;
                }
            }
        }

        for (int shipCount: shipsArray)
            if(shipCount != 0) return false;
        return true;
    }
}
