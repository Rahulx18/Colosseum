import java.util.Random;

public class Colosseum 
{
    private static Random random = new Random();

    public static void main(String[] args) 
    {
        // Creating two players (health, strength, attack)
        Player playerA = new Player(50, 5, 10);
        Player playerB = new Player(100, 10, 5);
        

        // Creating dice objects for attacking and defending
        Dice attackDice = new Dice();
        Dice defendDice = new Dice();


        // Determining the first and second player based on their initial health
        Player firstPlayer = determineFirstPlayer(playerA, playerB);


        // Initiating and simulating the battle
        simulateBattle();
    }


    private static Player determineFirstPlayer(Player playerA, Player playerB) {
        return (playerA.getHealth() <= playerB.getHealth()) ? playerA : playerB;
    } // Determining the first and second player based on their initial health

    



    // Method to simulate the battle between two players
    private static void simulateBattle() 
    {
        
    }

    // Method to simulate a player's turn in the battle
    private static void playerTurn()
    {

    }

    // Method to print turn details
    private static void printTurnDetails(){
        
    }
}