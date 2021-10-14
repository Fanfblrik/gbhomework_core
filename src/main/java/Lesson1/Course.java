package Lesson1;

public class Course {

    private int length;

    public String getType() {
        return type;
    }

    private String type;

    public int getLength() {
        return length;
    }

    Course(int length, String type){
        this.length = length;
        this.type = type;
    }
// Объединил два метода в один
    public static void doIt(Team[] Galileo, Course[] Ivent){

        for (int i = 0; i < Ivent.length; i++){
            for (int j =0; j < Galileo.length; j++){
                if (Ivent[i].length <= Galileo[j].getStamina()){
                    System.out.println(Galileo[j].getMemberName() + " успешно преодолел(а) " + Ivent[i].type + " длинной в " + Ivent[i].length + " м");
                } else {
                    System.out.println(Galileo[j].getMemberName() + " не смог(ла) преодолеть " + Ivent[i].type + " длинной в " + Ivent[i].length + " м");
                }
            }
        }

    }

}
