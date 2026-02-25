import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class MissingTileTest {
    @Test
    public void tilesExampleBoard() {
        Grid board = new BasicBoard(4, 3, 1);
        MissingTile.tileGrid(board);
        assertTrue(board.isFullyTiled());
    }

    @Test
    public void tilesLargerBoard() {
        Grid board = new BasicBoard(64, 10, 50);
        MissingTile.tileGrid(board);
        assertTrue(board.isFullyTiled());
    }
}
