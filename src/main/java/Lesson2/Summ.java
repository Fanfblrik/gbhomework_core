package Lesson2;

public class Summ {

    public static void main(String[] args) {

        String[][] arr = new String[4][4];
        arr[0][0] = "5";
        arr[0][1] = "5";
        arr[0][2] = "5";
        arr[0][3] = "x";
        arr[1][0] = "five";

        try {
            arrSum(arr);
        }
        catch (MyArraySizeException | ExceptionInInitializerError e){
            System.out.println("Недопустимый размер массива");
        }
    }

    public static void arrSum (String[][] arr){
        int s = 0;

            for (int y = 0; y < arr.length; y++){
                for (int x = 0; x < arr.length; x++){
                    try {
                        s = s + Integer.parseInt(arr[y][x]);
                        throw new MyArrayDataException();
                    } catch (MyArrayDataException | NumberFormatException e){
                        System.out.println("Ошибка при попытке преобразования ячейки ");
                       e.printStackTrace();
                    }
                }
            }
        System.out.println("Сумма - " + s);
    }

}
