package ru.job4j.assertj.assertionswithprimitivetypes;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class BoxTest {

    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere");
    }

    @Test
    void isThisTetrahedronAndNotBlankOrEmpty() {
        Box box = new Box(4, 10);
        assertThat(box.whatsThis())
                .isNotBlank()
                .isNotEmpty()
                .isEqualTo("Tetrahedron");
    }

    @Test
    void whenVertexIsEight() {
        Box box = new Box(8, 45);
        int vertex = box.getNumberOfVertices();
        assertThat(vertex)
                .isNotZero()
                .isNotNull()
                .isPositive();
    }

    @Test
    void whenVertexIsFive() {
        Box box = new Box(5, 45);
        int vertex = box.getNumberOfVertices();
        assertThat(vertex)
                .isNotZero()
                .isNotNull()
                .isNegative();
    }

    @Test
    void whenVertexNotMinusOne() {
        Box box = new Box(0, 45);
        boolean isExist = box.isExist();
        assertThat(isExist)
                .isNotNull()
                .isTrue();
    }

    @Test
    void whenVertexIsMinusOne() {
        Box box = new Box(123, 45);
        boolean isExist = box.isExist();
        assertThat(isExist)
                .isNotNull()
                .isFalse();
    }

    @Test
    void whenGetAreaIsZero() {
        Box box = new Box(123, 123);
        double area = box.getArea();
        assertThat(area)
                .isNotNaN()
                .isZero();
    }

    @Test
    void whenGetAreaIsSix() {
        Box box = new Box(8, 1);
        double area = box.getArea();
        assertThat(area)
                .isNotZero()
                .isEqualTo(6);
    }


    @Test
    void whenGetAreaMoreThanSquareOfEdge() {
        Box box = new Box(4, 5);
        double area = box.getArea();
        assertThat(area)
                .isNotNaN()
                .isPositive()
                .isGreaterThan(25);
    }

}