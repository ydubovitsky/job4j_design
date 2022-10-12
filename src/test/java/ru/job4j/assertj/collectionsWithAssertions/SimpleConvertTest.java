package ru.job4j.assertj.collectionsWithAssertions;

import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SimpleConvertTest {

    @Test
    void checkArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");
        assertThat(array).hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1));
    }

    @Test
    void isListEmpty() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] stringArray = simpleConvert.toArray();
        assertThat(stringArray)
                .isEmpty();
    }

    @Test
    void checkIsArrayOfStringsContainFiveElements() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] stringArray = simpleConvert.toArray("java", "react", "job4j", "figma", "javascript");
        assertThat(stringArray)
                .isInstanceOf(String[].class)
                .isNotNull()
                .hasSize(5);
    }

    @Test
    void checkIsArrayOfStringsContainerKeyWords() {
        String[] keyWordsArray = {"java", "react"};
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] stringArray = simpleConvert.toArray("java", "react", "job4j", "figma", "javascript");
        assertThat(stringArray)
                .isInstanceOf(String[].class)
                .isNotNull()
                .contains(keyWordsArray);
    }

    @Test
    void checkIsArrayOfStringsContainWordsWithLengthMoreThanThreeSymbols() {
        int maxLength = 3;
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] stringArray = simpleConvert.toArray("java", "react", "job4j", "figma", "javascript");
        assertThat(stringArray)
                .isInstanceOf(String[].class)
                .isNotNull()
                .allSatisfy(word -> assertThat(word.length()).isGreaterThan(maxLength));
    }

    @Test
    void checkIsArrayOfStringsContainWordsWithLengthMoreThanThreeAndLessThanTwentySymbols() {
        int minLength = 3;
        int maxLength = 20;
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] stringArray = simpleConvert.toArray("java", "react", "job4j", "figma", "javascript");
        assertThat(stringArray)
                .isInstanceOf(String[].class)
                .isNotNull()
                .allSatisfy(word -> {
                    assertThat(word.length()).isGreaterThan(minLength);
                    assertThat(word.length()).isLessThan(maxLength);
                });
    }

    @Test
    void checkIsListMayContainNotOnlyUniqSymbols() {
        String word = "java";
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> stringsList = simpleConvert.toList(
                "java",
                "java",
                "react",
                "job4j",
                "figma",
                "javascript");

        assertThat(stringsList)
                .isInstanceOf(List.class)
                .isNotNull()
                .filteredOn(w -> w.equals(word))
                .hasSize(2);
    }

    @Test
    void checkIsSetContainOnlyUniqSymbols() {
        String word = "java";
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> stringsSet = simpleConvert.toSet(
                "java",
                "java",
                "react",
                "job4j",
                "figma",
                "javascript");

        assertThat(stringsSet)
                .isInstanceOf(Set.class)
                .isNotNull()
                .containsOnlyOnce(word);
    }

    @Test
    void checkWhenProcessElementInListWhichIndexGreaterThanArraySizeThrowsIndexOutOfBoundsException() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> stringsList = simpleConvert.toList(
                "java",
                "java",
                "react",
                "job4j",
                "figma",
                "javascript");

        assertThatThrownBy(() -> stringsList.get(stringsList.size()))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void checkMapThatAllKeysIsPositive() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Map<String, Integer> stringsMap = simpleConvert.toMap(
                "java",
                "java",
                "react",
                "job4j",
                "figma",
                "javascript");

        assertThat(stringsMap)
                .isNotEmpty()
                .hasSize(5)
                .allSatisfy((value, key) -> {
                    if (key != 0) {
                        assertThat(key).isPositive();
                    }
                });
    }

    @Test
    void checkMapThatAllValuesIsUniq() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Map<String, Integer> stringsMap = simpleConvert.toMap(
                "java",
                "java",
                "java",
                "java",
                "react",
                "job4j",
                "java",
                "java",
                "figma",
                "javascript");

        assertThat(stringsMap)
                .isNotEmpty()
                .hasSize(5)
                .containsOnlyKeys(Stream.of(
                        "java",
                        "react",
                        "job4j",
                        "figma",
                        "javascript").collect(Collectors.toList()));
    }

    @Test
    void checkMapThatAllValuesOrdered() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Map<String, Integer> stringsMap = simpleConvert.toMap(
                "java",
                "java",
                "java",
                "java",
                "react",
                "job4j",
                "java",
                "java",
                "figma",
                "javascript");
        assertThat(stringsMap)
                .containsExactlyInAnyOrderEntriesOf(
                        new HashMap<String, Integer>() {{
                            put("figma", 8);
                            put("java", 0);
                            put("javascript", 9);
                            put("job4j", 5);
                            put("react", 4);
                        }}
                );
    }
}