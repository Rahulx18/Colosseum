package main;
import java.util.Random;

//6 sided die with values ranging from 1- 6
class Dice {
    private Random random;

    //each instance of die has its own seed for randomness 
    public Dice() {
        this.random = new Random();
    }

    public int roll() {
        return random.nextInt(6) + 1;
    }
}