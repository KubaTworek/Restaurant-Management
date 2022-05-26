package pl.pjatk.Menu;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

class MenuTest {

    private final Menu menu = new Menu();


    @BeforeEach
    void init(){
        menu.getMenu().add(new Food("Pierogi", "Polish food", 5.99));
    }

    /*@org.junit.jupiter.api.Test
    void getMenu() {
        fail("Test wasnt made");
    }

    @org.junit.jupiter.api.Test
    void startMenu() {
        fail("Test wasnt made");
    }*/

    @org.junit.jupiter.api.Test
    void changeAvailability() {
        boolean output = menu.getMenu().get(0).getAvailable();
        menu.getMenu().get(0).setAvailable(!output);
        boolean output2 = menu.getMenu().get(0).getAvailable();

        assertTrue(output);
        assertFalse(output2);
    }

    /*@org.junit.jupiter.api.Test
    void makingFoodToMenu() {
        fail("Test wasnt made");
    }

    @org.junit.jupiter.api.Test
    void choosingFoodFromMenu() {
        fail("Test wasnt made");
    }*/

    @org.junit.jupiter.api.Test
    void addToMenu() {
        assertEquals(new Food("Pierogi", "Polish food", 5.99).toSave(), menu.getMenu().get(0).toSave());
    }

    @org.junit.jupiter.api.Test
    void deleteFromMenu() {
        boolean output = false;

        for(int i=0; i<30; i++) {
            menu.deleteFromMenu(i);
        }
        if(menu.getMenu().isEmpty()) {
            output = true;
        }
        assertTrue(output);
    }

    /*@org.junit.jupiter.api.Test
    void writeOutMenu() {
        fail("Test wasnt made");
    }

    @org.junit.jupiter.api.Test
    void saveToFile() {
        fail("Test wasnt made");
    }

    @org.junit.jupiter.api.Test
    void writeFromFile() {
        fail("Test wasnt made");
    }*/
}