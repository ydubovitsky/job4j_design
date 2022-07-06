package ru.job4j.iterator.backDirectionIterator;

import java.util.logging.Logger;

public class BackDirectionIteratorImpl implements BackDirectionIterator {

    private static final Logger LOGGER = Logger.getLogger(BackDirectionIteratorImpl.class.getName());

    private int[] data;
    private int currentIteratorPosition;

    public BackDirectionIteratorImpl(int[] data) {
        this.data = data;
        this.currentIteratorPosition = data.length;
    }

    @Override
    public boolean hasPrevious() {
        LOGGER.info(String.format("Current iterator position - %s" , currentIteratorPosition));
        return currentIteratorPosition > 0;
    }

    @Override
    public int previous() {
        if(hasPrevious()) {
            this.currentIteratorPosition = this.currentIteratorPosition - 1; //FIXME Redundant?
            return data[this.currentIteratorPosition];
        }
        throw new NoPreviousElementInArrayException();
    }
}
