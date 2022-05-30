package pl.pjatk.Menu;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class FoodTest {

    private final Food food1 = new Food("Pierogi", "Polish food", 5.99);
    private final Food food2 = new Food("Pasta", "Italian food", 6.99);

    @Test
    void getName() {
        assertEquals("Pierogi",food1.getName());
        assertEquals("Pasta",food2.getName());
    }

    @Test
    void getPrice() {
        assertEquals(5.99,food1.getPrice());
        assertEquals(6.99,food2.getPrice());
    }

    @Test
    void setAvailable() {
        assertTrue(food1.getAvailable());
        food1.setAvailable(false);
        assertFalse(food1.getAvailable());
    }

    @Test
    void getAvailable() {
        assertTrue(food1.getAvailable());
        assertTrue(food2.getAvailable());
    }

    @Test
    void getNumberOfFood() {
        assertEquals(2,Food.getNumberOfFood());
    }

    @Test
    void toSave() {
        assertEquals("Pierogi 5.99 Polish food",food1.toSave());
        food2.setAvailable(false);
        assertEquals("Pasta 0 Italian food",food2.toSave());
    }

    @Test
    void testToString() {
        assertEquals("Pierogi, 5.99$\nPolish food",food1.toString());
        food2.setAvailable(false);
        assertEquals("Pasta, UNAVAILABLE\nItalian food",food2.toString());
    }
}