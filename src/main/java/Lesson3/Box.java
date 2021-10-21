package Lesson3;

import java.util.ArrayList;

public class Box <T extends Fruit> implements Comparable<Box>{

    private ArrayList<Fruit> T;
    private int countFruits = 0;

    public Box(ArrayList<Fruit> array){
    this.T = array;
    countFruits++;
    }

    public int getCountFruits() {
        return countFruits;
    }

    public float getWeightOfBox(Box<?> another){
        float w = 0f;
        Fruit[] fruitArray = T.toArray(new Fruit[0]);
        if (fruitArray[0] instanceof Apple){
            w = countFruits * ((Apple) fruitArray[0]).getWeight();
        } else if (fruitArray[0] instanceof Orange){
            w = countFruits * ((Orange) fruitArray[0]).getWeight();
        }
        return w;
    }

    public void pere(Box o){
        Fruit[] fruitArray = T.toArray(new Fruit[0]);
        if (fruitArray[0] instanceof Apple){
            for (int i = 0; i < o.getCountFruits(); i++){
                this.T.add(new Apple());
            }
            o.T.clear();
        } else{
            System.out.println("Нельзя смешивать фрукты!");
        }

    }

    @Override
    public int compareTo(Box o) {
        if (getWeightOfBox(this) > getWeightOfBox(o)){
            System.out.println(this + " больше чем " + o);
        } else {
            System.out.println(o + " больше чем " + this);
        }
        return 0;
    }

    public boolean compare(Box o) {
            return getWeightOfBox(this) == getWeightOfBox(o);
    }
}
