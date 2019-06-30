package geometry;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withinPercentage;
import static org.junit.jupiter.api.Assertions.*;

class Vec3Test {

    final Vec3 e1 = new Vec3(1,0,0);
    final Vec3 e2 = new Vec3(0,1,0);
    final Vec3 e3 = new Vec3(0,0,1);
    final Vec3 zero = new Vec3(0,0,0);
    Vec3 v1;
    Vec3 v2;
    Vec3 v3;

    @BeforeEach
    void setUp() {
        v1 = new Vec3(3,4,0);
        v2 = new Vec3(1,2,-3);
    }

    @Test
    void length() {
        assertThat(e1.length()).isEqualTo(1.0);
        assertThat(v1.length()).isEqualTo(5.0);
    }

    @Test
    void squared_length() {
        assertThat(e1.squared_length()).isEqualTo(1.0);
        assertThat(v1.squared_length()).isEqualTo(25.0);
    }


    @Test
    void add() {
        Vec3 sum = new Vec3(3,4,1);
        assertThat(v1.add(e3)).isEqualTo(sum);
    }

    @Test
    void subtract() {
        Vec3 sub = new Vec3(-3,-4,1);
        assertThat(e3.subtract(v1)).isEqualTo(sub);
    }

    @Test
    void multiply() {
        Vec3 product = v1.multiply(v2);

        assertThat(product.x()).isCloseTo(3, withinPercentage(1));
        assertThat(product.y()).isCloseTo(8, withinPercentage(1));
        assertThat(product.z()).isCloseTo(0, withinPercentage(1));
    }

    @Test
    void multiply1() {
        Vec3 produkt = new Vec3(-2,-4,6);
        assertThat(v2.multiply(-2)).isEqualTo(produkt);
    }

    @Test
    void divide() {
        Vec3 divided = v1.divide(v2);

        assertThat(divided.x()).isCloseTo(3, withinPercentage(1));
        assertThat(divided.y()).isCloseTo(2, withinPercentage(1));
        assertThat(divided.z()).isCloseTo(0, withinPercentage(1));
    }

    @Test
    void divide1() {
        Vec3 dividend = new Vec3(0.5, 1.0, -1.5);
        assertThat(v2.divide(2)).isEqualTo(dividend);
    }

    @Test
    void dot() {
        assertThat(Vec3.dot(v1,v2)).isEqualTo(11);
    }

    @Test
    void unit_vector() {
        Vec3 unit = Vec3.unit_vector(v1);

        assertThat(unit.x()).isCloseTo(0.6, withinPercentage(1));
        assertThat(unit.y()).isCloseTo(0.8, withinPercentage(1));
        assertThat(unit.z()).isCloseTo(0, withinPercentage(1));
    }

    @Test
    void make_unit_vector() {
        Vec3 unit = new Vec3(0.6, 0.8, 0);
        v1.make_unit_vector();

        assertThat(v1.x()).isCloseTo(0.6, withinPercentage(1));
        assertThat(v1.y()).isCloseTo(0.8, withinPercentage(1));
        assertThat(v1.z()).isCloseTo(0, withinPercentage(1));
    }

    @Test
    void cross() {
        assertThat(Vec3.cross(e1,e2)).isEqualTo(e3);
        assertThat(Vec3.cross(e2,e3)).isEqualTo(e1);
        assertThat(Vec3.cross(e3,e1)).isEqualTo(e2);
    }
}