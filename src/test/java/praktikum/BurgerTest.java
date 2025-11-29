package praktikum;
import org.junit.Test;
import org.junit.Before;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.Assert.*;

public class BurgerTest {

    @Mock
    private Bun bun;

    @Mock
    private Ingredient fisrtIngredient;

    @Mock
    private Ingredient secondIngredient;

    @Mock
    private Ingredient thirdIngredient;

    private Burger burger;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        burger = new Burger();
    }

    // Тесты для setBuns()
    @Test
    public void testSetBuns() {
        burger.setBuns(bun);
        assertEquals(bun, burger.bun);
    }

    // Тесты для addIngredient()
    @Test
    public void testAddIngredient() {
        burger.addIngredient(fisrtIngredient);
        assertTrue(burger.ingredients.contains(fisrtIngredient));
    }

    // Тесты для removeIngredient()
    @Test
    public void testRemoveIngredient() {
        burger.addIngredient(fisrtIngredient);
        burger.addIngredient(secondIngredient);
        burger.removeIngredient(0);
        assertFalse(burger.ingredients.contains(fisrtIngredient));
    }

    // Тесты для moveIngredient()
    @Test
    public void testMoveIngredient() {
        burger.addIngredient(fisrtIngredient);
        burger.addIngredient(secondIngredient);
        burger.addIngredient(thirdIngredient);

        burger.moveIngredient(0, 2);

        assertEquals(fisrtIngredient, burger.ingredients.get(2));
    }


}

