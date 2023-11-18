package main;
import org.junit.Test;
import static org.junit.Assert.*;

public class ColosseumTest {

    @Test
    public void testTakeDamage() {
        Player player = new Player("TestPlayer", 100, 10, 5);
        player.takeDamage(20);
        assertEquals(80, player.getHealth());
        
        // Ensure health doesn't go below 0
        player.takeDamage(200);
        assertEquals(0, player.getHealth());
    }

    @Test
    public void testIsAlive() {
        Player alivePlayer = new Player("AlivePlayer", 50, 5, 10);
        Player deadPlayer = new Player("DeadPlayer", 0, 5, 10);

        assertTrue(alivePlayer.isAlive());
        assertFalse(deadPlayer.isAlive());
    }

    @Test
    public void testCalculateDamage() {
        Dice TestAtkDice = new Dice() {
            @Override
            public int roll() {
                return 3; 
            }
        };

        Player player = new Player("TestPlayer", 100, 10, 5);
        assertEquals(15, player.calculateDamage(TestAtkDice));
    }

    @Test
    public void testCalculateDefense() {
        Dice TestDefDice = new Dice() {
            @Override
            public int roll() {
                return 2;
            }
        };

        Player player = new Player("TestPlayer", 100, 10, 5);
        assertEquals(20, player.calculateDefense(TestDefDice));
    }
}