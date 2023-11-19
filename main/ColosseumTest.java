package main;

import org.junit.Test;
import static org.junit.Assert.*;

public class ColosseumTest {

    private static final int INITIAL_HEALTH = 50;
    private static final int INITIAL_STRENGTH = 10;
    private static final int INITIAL_ATTACK = 5;
    private static final int TEST_ATK = 3;
    private static final int TEST_DEF = 2;

    @Test
    public void testTakeDamage() {
        // Test the takeDamage method
        Player player = new Player("TestPlayer", INITIAL_HEALTH, INITIAL_STRENGTH, INITIAL_ATTACK);
        player.takeDamage(20);
        assertEquals(INITIAL_HEALTH - 20, player.getHealth());

        player.takeDamage(200);
        assertEquals(0, player.getHealth());
    }

    @Test
    public void testIsAlive() {
        // Test the isAlive method
        Player alivePlayer = new Player("AlivePlayer", 50, INITIAL_STRENGTH, INITIAL_ATTACK);
        Player deadPlayer = new Player("DeadPlayer", 0, INITIAL_STRENGTH, INITIAL_ATTACK);

        assertTrue(alivePlayer.isAlive());
        assertFalse(deadPlayer.isAlive());
    }

    @Test
    public void testCalculateDamage() {
        // Test the calculateDamage method
        Dice testAtkDice = new Dice() {
            @Override
            public int roll() {
                return TEST_ATK;
            }
        };

        Player player = new Player("TestPlayer", INITIAL_HEALTH, INITIAL_STRENGTH, INITIAL_ATTACK);
        assertEquals(TEST_ATK * INITIAL_ATTACK, player.calculateDamage(testAtkDice));
    }

    @Test
    public void testCalculateDefense() {
        // Test the calculateDefense method
        Dice testDefDice = new Dice() {
            @Override
            public int roll() {
                return TEST_DEF;
            }
        };

        Player player = new Player("TestPlayer", INITIAL_HEALTH, INITIAL_STRENGTH, INITIAL_ATTACK);
        assertEquals(TEST_DEF * INITIAL_STRENGTH, player.calculateDefense(testDefDice));
    }

    // New tests

    @Test
    public void testFullBattle() {
        // Test a full battle scenario where the first attacker wins
        // Test can be edited to fit requirements. Based on initial health, strength and attack values, assestion will change.
        Player playerA = new Player("Player A", INITIAL_HEALTH, INITIAL_STRENGTH, INITIAL_ATTACK);
        Player playerB = new Player("Player B", INITIAL_HEALTH, INITIAL_STRENGTH, INITIAL_ATTACK);

        Dice attackDice = new Dice() {
            @Override
            public int roll() {
                return TEST_ATK;
            }
        };

        Dice defendDice = new Dice() {
            @Override
            public int roll() {
                return TEST_DEF;
            }
        };

        simulateBattle(playerA, playerB, attackDice, defendDice);

        assert(playerA.isAlive());
        assertFalse(playerB.isAlive());
    }

    @Test
    public void testBattleWithEqualInitialHealth() {
        // Test a battle scenario with equal initial health, A being the first attacker and winning
        Player playerA = new Player("Player A", INITIAL_HEALTH, INITIAL_STRENGTH, INITIAL_ATTACK);
        Player playerB = new Player("Player B", INITIAL_HEALTH, INITIAL_STRENGTH, INITIAL_ATTACK);

        Dice attackDice = new Dice() {
            @Override
            public int roll() {
                return TEST_ATK;
            }
        };

        Dice defendDice = new Dice() {
            @Override
            public int roll() {
                return TEST_DEF;
            }
        };

        simulateBattle(playerA, playerB, attackDice, defendDice);

        assert(playerA.isAlive());
        assertFalse(playerB.isAlive());
    }

    private static void simulateBattle(Player firstPlayer, Player secondPlayer, Dice attackDice, Dice defendDice) {
        // Simulate a battle between two players
        while (firstPlayer.isAlive() && secondPlayer.isAlive()) {
            playerTurn(firstPlayer, secondPlayer, attackDice, defendDice);
            if (!secondPlayer.isAlive()) {
                System.out.println(firstPlayer.getName() + " is victorious!");
                break;
            }
            Player temp = firstPlayer;
            firstPlayer = secondPlayer;
            secondPlayer = temp;
        }
    }

    private static void playerTurn(Player attacker, Player defender, Dice attackDice, Dice defendDice) {
        // Simulate a player's turn in the battle
        int damage = attacker.calculateDamage(attackDice);
        int defense = defender.calculateDefense(defendDice);

        defender.takeDamage(Math.max(1, damage - defense));

        printTurnDetails(attacker, defender, damage, defense);
    }

    private static void printTurnDetails(Player attacker, Player defender, int damage, int defense) {
        // Print turn details
        System.out.println(String.format("%s attacks %s. Damage dealt by %s: %d, Defense of %s: %d. %s's health reduced to: %d",
            attacker.getName(), defender.getName(), attacker.getName(), damage, defender.getName(), defense, defender.getName(), defender.getHealth()));
    }
}
