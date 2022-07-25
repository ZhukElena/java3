package com.geekbrains.fruits;

import java.util.ArrayList;

public class Box<E extends Fruit> {

    private ArrayList<E> fruits = new ArrayList<>();

    public float getWeight() {
        return fruits.isEmpty()
                ? 0
                : fruits.size() * fruits.get(0).getWeight();
    }

    public boolean compare(Box that) {
        return that.getWeight() == this.getWeight();
    }

    public void addFruits(Box<E> that, int number) {
        if (number > that.getFruits().size()) {
            System.out.println("Не хватает фруктов для пересыпания");
        } else {
            for (int i = 0; i < number; i++) {
                E fruit = that.getFruits().get(0);
                addFruit(fruit);
                that.getFruits().remove(fruit);
            }
        }
    }

    public void addFruit(E fruit) {
        this.getFruits().add(fruit);
    }

    public ArrayList<E> getFruits() {
        return fruits;
    }

    public void setFruits(ArrayList<E> fruits) {
        this.fruits = fruits;
    }
}
