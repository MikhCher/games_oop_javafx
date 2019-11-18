package ru.job4j.chess.firuges.black;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;
import static java.lang.Math.abs;
public class BishopBlack implements Figure {
    private final Cell position;
    public BishopBlack(final Cell position) {
        this.position = position;
    }
    @Override
    public Cell position() {
        return this.position;
    }
    @Override
    public Cell[] way(Cell source, Cell dest) {
        if (!isDiagonal(source, dest)) {
            throw new IllegalStateException(
                    String.format("Could not way by diagonal from %s to %s", source, dest)
            );
        }
        int size = abs(source.x - dest.x);
        Cell[] steps = new Cell[size];
        int deltaX;
        int deltaY;
        if (source.x < dest.x) {
            deltaX = 1;
            if (source.y < dest.y) {
                deltaY = 1;
            } else {
                deltaY = -1;
            }
        } else {
            deltaX = -1;
            if (source.y < dest.y) {
                deltaY = 1;
            } else {
                deltaY = -1;
            }
        }
        steps = this.IntermediatePositions(steps, source, deltaX, deltaY);
        return steps;
    }

    private Cell[] IntermediatePositions(Cell[] steps, Cell source, int deltaX, int deltaY) {
        Cell[] cellValue = Cell.values();
        for (int index = 0; index < steps.length; index++) {
            for (int i = 0; i < cellValue.length; i++) {
                if (cellValue[i].x == source.x + ((index + 1) * deltaX) && cellValue[i].y == source.y + ((index + 1) * deltaY)) {
                    steps[index] = cellValue[i];
                    break;
                }
            }
        }
        return steps;
    }
    public boolean isDiagonal(Cell source, Cell dest) {
        boolean diagonal = false;
        if(abs(source.x - dest.x) == abs(source.y - dest.y)) {
            diagonal = true;
        }
        return diagonal;
    }
    @Override
    public Figure copy(Cell dest) {
        return new BishopBlack(dest);
    }
}