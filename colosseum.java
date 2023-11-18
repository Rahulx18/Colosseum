
public class Colosseum {

    public static void main(String[] args) {
        // Creating two players (health, strength, attack) and dice objects
        Player playerA = new Player("Player A", 50, 5, 10);
        Player playerB = new Player("Player B", 100, 10, 5);

        Dice attackDice = new Dice();
        Dice defendDice = new Dice();


        // Determining the first player based on their initial health
        Player firstPlayer = determineFirstPlayer(playerA, playerB);

        // Initiating and simulating the battle
        simulateBattle(firstPlayer, getOtherPlayer(firstPlayer, playerA, playerB), attackDice, defendDice);
    }

    private static Player determineFirstPlayer(Player playerA, Player playerB) {
        return (playerA.getHealth() <= playerB.getHealth()) ? playerA : playerB;
    }

    private static Player getOtherPlayer(Player currentPlayer, Player playerA, Player playerB) {
        return (currentPlayer == playerA) ? playerB : playerA;
    }

    // Method to simulate the battle between two players
    private static void simulateBattle(Player firstPlayer, Player secondPlayer, Dice attackDice, Dice defendDice) {
        while (firstPlayer.isAlive() && secondPlayer.isAlive()) {
            playerTurn(firstPlayer, secondPlayer, attackDice, defendDice);
            if (!secondPlayer.isAlive()) {
                System.out.println(firstPlayer.getName() + " is victorious!");
                break;
            }
            swapPlayers(firstPlayer, secondPlayer);
        }
    }

    // Method to simulate a player's turn in the battle
    private static void playerTurn(Player attacker, Player defender, Dice attackDice, Dice defendDice) {
        int damage = attacker.calculateDamage(attackDice);
        int defense = defender.calculateDefense(defendDice);

        defender.takeDamage(Math.max(0, damage - defense));

        printTurnDetails(attacker, defender, damage, defense);
    }

    private static void swapPlayers(Player firstPlayer, Player secondPlayer) {
        // Swapping players for the next turn
        Player temp = firstPlayer;
        firstPlayer = secondPlayer;
        secondPlayer = temp;
    }

    //print function to show the flow of battle
    private static void printTurnDetails(Player attacker, Player defender, int damage, int defense) {
        System.out.println(attacker.getName() + " attacks, " + defender.getName() + " defends.");
        System.out.println(attacker.getName() + "'s damage: " + damage + ", " +
                defender.getName() + "'s defense: " + defense);
        System.out.println(defender.getName() + "'s health reduced to: " + defender.getHealth());
    }
}