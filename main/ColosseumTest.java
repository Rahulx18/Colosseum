package main;
import org.junit.Test;
import static org.junit.Assert.*;

public class ColosseumTest {

    private static final int INITIAL_HEALTH = 100;
    private static final int INITIAL_STRENGTH = 10;
    private static final int INITIAL_ATTACK = 5;
    private static final int TEST_ATK = 3;
    private static final int TEST_DEF = 2;

    @Test
    public void testTakeDamage() {
        Player player = new Player("TestPlayer", INITIAL_HEALTH, INITIAL_STRENGTH, INITIAL_ATTACK);
        player.takeDamage(20);
        assertEquals(INITIAL_HEALTH - 20, player.getHealth());

        player.takeDamage(200);
        assertEquals(0, player.getHealth());
    }

    @Test
    public void testIsAlive() {
        Player alivePlayer = new Player("AlivePlayer", 50, INITIAL_STRENGTH, INITIAL_ATTACK);
        Player deadPlayer = new Player("DeadPlayer", 0, INITIAL_STRENGTH, INITIAL_ATTACK);

        assertTrue(alivePlayer.isAlive());
        assertFalse(deadPlayer.isAlive());
    }

    @Test
    public void testCalculateDamage() {
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
        Dice testDefDice = new Dice() {
            @Override
            public int roll() {
                return TEST_DEF;
            }
        };

        Player player = new Player("TestPlayer", INITIAL_HEALTH, INITIAL_STRENGTH, INITIAL_ATTACK);
        assertEquals(TEST_DEF * INITIAL_STRENGTH, player.calculateDefense(testDefDice));
    }


    // new tests
    @Test
    public void testFullBattle() {
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

        // Both players' health should be 0 or less
        assert(playerA.isAlive());
        assertFalse(playerB.isAlive());
    }

    @Test
    public void testBattleWithEqualInitialHealth() {
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

        // A being first, will be victorious
        assert(playerA.isAlive());
        assertFalse(playerB.isAlive());
    }

    private static void simulateBattle(Player firstPlayer, Player secondPlayer, Dice attackDice, Dice defendDice) {
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
        int damage = attacker.calculateDamage(attackDice);
        int defense = defender.calculateDefense(defendDice);

        defender.takeDamage(Math.max(1, damage - defense));

        printTurnDetails(attacker, defender, damage, defense);
    }

    private static void printTurnDetails(Player attacker, Player defender, int damage, int defense) {
        System.out.println(String.format("%s attacks %s. Damage dealt by %s: %d, Defense of %s: %d. %s's health reduced to: %d",
        attacker.getName(), defender.getName(),attacker.getName(), damage,defender.getName(), defense, defender.getName(), defender.getHealth()));

    }
}
