import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarTest {

    @Test
    void getAge() {
        Car car = new Car(12,"Toyota");
        assertEquals(12,car.getAge());
    }
}