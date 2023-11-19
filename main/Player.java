package main;
// Represents a player in the Colosseum with health, strength, and attack attributes
class Player {
    private String name;
    private int health, strength, attack;

    // Constructor to initialize player attributes
    public Player(String name, int health, int strength, int attack) {
        this.name = name;
        this.health = health;
        this.strength = strength;
        this.attack = attack;
    }

    // Getter method for the player's name
    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getStrength() {
        return strength;
    }

    public int getAttack() {
        return attack;
    }

    // Reduces the player's health by the given damage, ensuring it doesn't go below 0
    public void takeDamage(int damage) {
        health = Math.max(0, health - damage);
    }

    // Checks if the player is alive based on their health
    public boolean isAlive() {
        return health > 0;
    }

    // Calculates the damage dealt by the player using the provided attack dice
    public int calculateDamage(Dice attackDice) {
        return attackDice.roll() * attack;
    }

    // Calculates the defense of the player using the provided defend dice
    public int calculateDefense(Dice defendDice) {
        return defendDice.roll() * strength;
    }
}
