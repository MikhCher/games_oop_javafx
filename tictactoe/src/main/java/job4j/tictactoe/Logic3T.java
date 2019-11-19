package job4j.tictactoe;

import java.util.function.Predicate;

public class Logic3T {
    private final Figure3T[][] table;

    public Logic3T(Figure3T[][] table) {
        this.table = table;
    }

    public boolean fillBy(Predicate<Figure3T> predicate, int startX, int startY, int deltaX, int deltaY) {
        boolean result = true;
        for (int index = 0; index != this.table.length; index++) {
            Figure3T cell = this.table[startX][startY];
            startX += deltaX;
            startY += deltaY;
            if (!predicate.test(cell)) {
                result = false;
                break;
            }
        }
        return result;
    }

    public boolean isWinnerX() {
        boolean result = false;
        for (int i = 0; i < table[0].length; i++) {
            if (table[i][i].hasMarkX()) {
                if (checkThreeInARow(i)) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }

    public boolean isWinnerO() {
        boolean result = false;
        for (int i = 0; i < table[0].length; i++) {
            if (table[i][i].hasMarkO()) {
                if (checkThreeInARow(i)) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }

    public boolean hasGap() {
        boolean result = false;
        for (Figure3T[] row : table) {
            for (Figure3T cell : row) {
                if (!cell.hasMarkX() && !cell.hasMarkO()) {
                    result = true;
                    break;
                }
            }
            if (result) {
                break;
            }
        }
        return result;
    }

    private boolean checkThreeInARow(int i) {
        boolean vertical = fillBy(Figure3T::hasMarkO, 0, i, 1, 0)
                || fillBy(Figure3T::hasMarkX, 0, i, 1, 0);
        boolean horizontal = fillBy(Figure3T::hasMarkO, i, 0, 0, 1)
                || fillBy(Figure3T::hasMarkX, i, 0, 0, 1);
        boolean diagonal = (fillBy(Figure3T::hasMarkO, 0, 0, 1, 1))
                || (fillBy(Figure3T::hasMarkO, 2, 0, -1, 1))
                || (fillBy(Figure3T::hasMarkX, 0, 0, 1, 1))
                || (fillBy(Figure3T::hasMarkX, 2, 0, -1, 1));
        return vertical || horizontal || diagonal;
    }
}
