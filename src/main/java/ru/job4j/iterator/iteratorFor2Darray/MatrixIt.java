package ru.job4j.iterator.iteratorFor2Darray;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIt implements Iterator<Integer> {

    private final int[][] data;
    private int column = 0;
    private int row = 0;

    public MatrixIt(int[][] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        if (data.length > row) {
            if (data[row].length > 0) {
                if (data[row].length > column) {
                    return true;
                } else {
                    this.row++;
                    this.column = 0;
                    return hasNext();
                }
            } else {
                if (data.length - 1 > row) {
                    row++;
                    return hasNext();
                } else {
                    return false;
                }
            }
        } else {
            return false;
        }
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[row][column++];
    }
}
