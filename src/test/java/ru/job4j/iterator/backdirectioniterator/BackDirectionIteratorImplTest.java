package ru.job4j.iterator.backdirectioniterator;

import org.junit.Before;
import org.junit.Test;

import java.util.stream.IntStream;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class BackDirectionIteratorImplTest {

    private static final int START_RANGE = 0;
    private static final int END_RANGE = 10;

    private BackDirectionIterator iterator;

    @Before
    public void init() {
        iterator = new BackDirectionIteratorImpl(
                IntStream.range(START_RANGE, END_RANGE).toArray()
        );
    }

    @Test
    public void hasPrevious() {
        assertThat(iterator.hasPrevious(), is(true));
        assertThat(iterator.hasPrevious(), is(true));
        assertThat(iterator.hasPrevious(), is(true));
    }

    @Test
    public void previous() {
        assertThat(iterator.previous(), is(9));
        assertThat(iterator.previous(), is(8));
        assertThat(iterator.previous(), is(7));
        assertThat(iterator.previous(), is(6));
        assertThat(iterator.previous(), is(5));
        assertThat(iterator.previous(), is(4));
        assertThat(iterator.previous(), is(3));
        assertThat(iterator.previous(), is(2));
        assertThat(iterator.previous(), is(1));
        assertThat(iterator.previous(), is(0));
    }

    //!TODO Make refactoring!
    @Test(expected = NoPreviousElementInArrayException.class)
    public void testIndexOutOfBoundsException() {
        for (int i = START_RANGE; i < END_RANGE + 1; i++) {
            iterator.previous();
        }
    }
}
