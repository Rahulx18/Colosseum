package main;
import java.util.Random;

//6 sided die with values ranging from 1- 6
class Dice {
    private Random random = new Random();

    public int roll() {
        return random.nextInt(6) + 1;
    }
}