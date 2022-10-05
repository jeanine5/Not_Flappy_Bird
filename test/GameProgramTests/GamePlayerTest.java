package GameProgramTests;

import GameProgram.GamePlayer;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GamePlayerTest {

    @Test
    public void testInitializer() {
        GamePlayer player = new GamePlayer("kid");
        assertEquals("kid", player.getUsername());
        assertEquals(80, player.getXCoordinate());
        assertEquals(300, player.getYCoordinate());
    }

    @Test
    public void testSetLocation() {
        GamePlayer player = new GamePlayer("kid");
        player.setLocation(100, 100);
        assertEquals(100, player.getXCoordinate());
        assertEquals(100, player.getYCoordinate());
    }

    @Test
    public void testGetWidth() {
        GamePlayer player = new GamePlayer("kid");
        assertEquals(20, player.getWidth());
    }

    @Test
    public void testGetHeight() {
        GamePlayer player = new GamePlayer("kid");
        assertEquals(20, player.getHeight());
    }
}
