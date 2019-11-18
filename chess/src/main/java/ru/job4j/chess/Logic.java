package ru.job4j.chess;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;
import java.util.Arrays;
import java.util.Optional;
public class Logic {
    private final Figure[] figures = new Figure[32];
    private int index = 0;
    public void add(Figure figure) {
        this.figures[this.index++] = figure;
    }
    public boolean move(Cell source, Cell dest) {
        boolean rst = true;
        int index = this.findBy(source);
        if (index != -1) {
            try {
                this.figures[index].way(source, dest);
            } catch (IllegalStateException ise) {
                rst = false;
            }
            Cell[] steps = null;
            if (rst) {
                steps = this.figures[index].way(source, dest);
            }
            if (rst && steps.length > 0 && steps[steps.length - 1].equals(dest)) {
                for (int i = 0; i < steps.length; i++) {
                    int j = findBy(steps[i]);
                    if (j != - 1) {
                        rst = false;
                        break;
                    }
                }
                if (rst) {
                    this.figures[index] = this.figures[index].copy(dest);
                }
            } else {
                rst = false;
            }
        }
        return rst;
    }
    public void clean() {
        for (int position = 0; position != this.figures.length; position++) {
            this.figures[position] = null;
        }
        this.index = 0;
    }
    private int findBy(Cell cell) {
        int rst = -1;
        for (int index = 0; index != this.figures.length; index++) {
            if (this.figures[index] != null && this.figures[index].position().equals(cell)) {
                rst = index;
                break;
            }
        }
        return rst;
    }
    @Override
    public String toString() {
        return "Logic{" +
                "figures=" + Arrays.toString(this.figures) +
                '}';
    }
}