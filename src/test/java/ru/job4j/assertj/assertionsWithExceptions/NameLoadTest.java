package ru.job4j.assertj.assertionsWithExceptions;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NameLoadTest {
    @Test
    void checkEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::getMap)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("no data");
    }

    @Test
    void whenInParseMethodNamesIsEmptyThrowIllegalArgumentException() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse())
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenInParseMethodNamesNotContainEqualitySymbol() {
        NameLoad nameLoad = new NameLoad();
        String arg = "=";
        assertThatThrownBy(() -> nameLoad.parse(arg))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(" does not contain")
                .hasMessageContaining(arg);
    }

    @Test
    void whenInParseMethodNamesContainEqualitySymbol() {
        NameLoad nameLoad = new NameLoad();
        String arg = "= and etc";
        assertThatThrownBy(() -> nameLoad.parse(arg))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("does not contain a key")
                .hasMessageContaining(arg);
    }

    @Test
    void whenInParseMethodNamesContainEqualitySymbolOnTheLastPlace() {
        NameLoad nameLoad = new NameLoad();
        String arg = "and etc =";
        assertThatThrownBy(() -> nameLoad.parse(arg))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("does not contain a value")
                .hasMessageContaining(arg);
    }

    @Test
    void whenValuesIsEmptyThrowIllegalStateException() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.getMap())
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("collection contains no data");
    }
}