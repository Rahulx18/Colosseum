package main;


//class for player attributes
class Player {
    private String name;
    private int health;
    private int strength;
    private int attack;

    //constructor 
    public Player(String name,int health, int strength, int attack) {
        this.name = name;
        this.health = health;
        this.strength = strength;
        this.attack = attack;
    }

    //getter methods
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


    //method to decrease health
    public void takeDamage(int damage) {
        health -= damage;
        if (health < 0) {
            health = 0;
        }
    }

    // check if player alive
    public boolean isAlive() {
        return health > 0;
    }

     // Method to calculate damage
     public int calculateDamage(Dice attackDice) {
        int attackRoll = attackDice.roll();
        return attackRoll * attack;
    }

    // Method to calculate defense
    public int calculateDefense(Dice defendDice) {
        int defendRoll = defendDice.roll();
        return defendRoll * strength;
    }

}