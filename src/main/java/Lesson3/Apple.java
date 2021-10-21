package Lesson3;

public class Apple extends Fruit {

    private float weight;
    private int num;
    private int x = 1;

    Apple(){
        this.num = x;
        this.weight = 1.0f;
        x++;
    }

    public int getNum() {
        return num;
    }

    public float getWeight() {
        return weight;
    }
}
