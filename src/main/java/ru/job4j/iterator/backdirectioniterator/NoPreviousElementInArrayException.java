package ru.job4j.iterator.backdirectioniterator;

public class NoPreviousElementInArrayException extends ArrayIndexOutOfBoundsException {

    private static final String MESSAGE = "There is no backward elements in array";

    @Override
    public String getMessage() {
        return MESSAGE;
    }
}
