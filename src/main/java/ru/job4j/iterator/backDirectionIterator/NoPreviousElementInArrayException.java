package ru.job4j.iterator.backDirectionIterator;

public class NoPreviousElementInArrayException extends ArrayIndexOutOfBoundsException {

    private static final String message = "There is no backward elements in array";

    @Override
    public String getMessage() {
        return message;
    }
}
