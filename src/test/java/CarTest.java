import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarTest {
    Car car = new Car(12,"Toyota");
    @Test //Annotation @Test skal bruges for hver metode, hvis den skal indgå i testen
    void getAge() {

        assertEquals(12,car.getAge());
    }

    @Test
    void getBrand(){
        assertEquals("Toyota",car.getBrand());
    }
}