package main;
import org.junit.Test;
import static org.junit.Assert.*;

public class ColosseumTest {

    private static final int INITIAL_HEALTH = 100;
    private static final int INITIAL_STRENGTH = 10;
    private static final int INITIAL_ATTACK = 5;

    @Test
    public void testTakeDamage() {  //Damage taken calculations and edge cases

        Player player = new Player("TestPlayer", INITIAL_HEALTH, INITIAL_STRENGTH, INITIAL_ATTACK); //setup
        player.takeDamage(20); // call
        assertEquals(INITIAL_HEALTH - 20, player.getHealth()); //verify 
        
        
        player.takeDamage(200);
        assertEquals(0, player.getHealth());
    }

    @Test
    public void testIsAlive() { //Testing isAlive function


        Player alivePlayer = new Player("AlivePlayer", 50, INITIAL_STRENGTH, INITIAL_ATTACK);
        Player deadPlayer = new Player("DeadPlayer", 0, INITIAL_STRENGTH, INITIAL_ATTACK);

        assertTrue(alivePlayer.isAlive());
        assertFalse(deadPlayer.isAlive());
    }

    @Test
    public void testCalculateDamage() {     // Damage calculation
        Dice testAtkDice = new Dice() {
            @Override
            public int roll() {
                return 3;   //test value
            }
        };

        Player player = new Player("TestPlayer", INITIAL_HEALTH, INITIAL_STRENGTH, INITIAL_ATTACK);
        assertEquals(testAtkDice.roll() * INITIAL_ATTACK, player.calculateDamage(testAtkDice));
    }

    @Test
    public void testCalculateDefense() {        // Defense calculation
        Dice testDefDice = new Dice() {
            @Override
            public int roll() {
                return 2; //test value
            }
        };

        Player player = new Player("TestPlayer", INITIAL_HEALTH, INITIAL_STRENGTH, INITIAL_ATTACK);
        assertEquals(testDefDice.roll() * INITIAL_STRENGTH, player.calculateDefense(testDefDice));
    }
}
