public class MissingTile {
    public static void tileGrid(Grid board) {
        // TODO: implement me!
        tilingHelper(board.size(), 0, 0, board.getPaintedCellX(), board.getPaintedCellY(), board);
    }

    private static void tilingHelper(int size, int topX, int topY, int paintedX, int paintedY, Grid board) {
        // a 2^0 x 2^0 tile, with 1 entry painted is already solved
        if (size == 1) {
            return;
        }

        int half_size = size >> 1;

        // set an L-shaped triminoes in the center, according to where the painted entry was (quadrant)
        if (paintedX < topX + half_size && paintedY < topY + half_size) { // NW
            board.setTile(topX + half_size - 1, topY + half_size - 1, 3);
            tilingHelper(half_size, topX, topY, paintedX, paintedY, board);

            tilingHelper(half_size, topX + half_size, topY, topX + half_size, topY + half_size - 1, board);
            tilingHelper(half_size, topX, topY + half_size, topX + half_size - 1, topY + half_size, board);
            tilingHelper(half_size, topX + half_size, topY + half_size, topX + half_size, topY + half_size, board);
        } else if (paintedX >= topX + half_size && paintedY < topY + half_size) { // NE
            board.setTile(topX + half_size, topY + half_size - 1, 0);
            tilingHelper(half_size, topX + half_size, topY, paintedX, paintedY, board);

            tilingHelper(half_size, topX, topY, topX + half_size - 1, topY + half_size - 1, board);
            tilingHelper(half_size, topX, topY + half_size, topX + half_size - 1, topY + half_size, board);
            tilingHelper(half_size, topX + half_size, topY + half_size, topX + half_size, topY + half_size, board);
        } else if (paintedX < topX + half_size && paintedY >= topY + half_size) { // SW
            board.setTile(topX + half_size - 1, topY + half_size, 2);
            tilingHelper(half_size, topX, topY + half_size, paintedX, paintedY, board);

            tilingHelper(half_size, topX, topY, topX + half_size - 1, topY + half_size - 1, board);
            tilingHelper(half_size, topX + half_size, topY, topX + half_size, topY + half_size - 1, board);
            tilingHelper(half_size, topX + half_size, topY + half_size, topX + half_size, topY + half_size, board);
        } else { // SE
            board.setTile(topX + half_size, topY + half_size, 1);
            tilingHelper(half_size, topX + half_size, topY + half_size, paintedX, paintedY, board);

            tilingHelper(half_size, topX, topY, topX + half_size - 1, topY + half_size - 1, board);
            tilingHelper(half_size, topX + half_size, topY, topX + half_size, topY + half_size - 1, board);
            tilingHelper(half_size, topX, topY + half_size, topX + half_size - 1, topY + half_size, board);
        }
    }
}
