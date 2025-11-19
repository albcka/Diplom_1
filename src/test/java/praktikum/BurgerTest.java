package praktikum;
import org.junit.Test;
import org.junit.Before;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class BurgerTest {

    @Mock
    private Bun bun;

    @Mock
    private Ingredient ingredient1;

    @Mock
    private Ingredient ingredient2;

    @Mock
    private Ingredient ingredient3;

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
        burger.addIngredient(ingredient1);
        assertEquals(1, burger.ingredients.size());
        assertTrue(burger.ingredients.contains(ingredient1));
    }

    // Тесты для removeIngredient()
    @Test
    public void testRemoveIngredient() {
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);
        burger.removeIngredient(0);
        assertEquals(1, burger.ingredients.size());
        assertFalse(burger.ingredients.contains(ingredient1));
    }

    // Тесты для moveIngredient()
    @Test
    public void testMoveIngredient() {
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);
        burger.addIngredient(ingredient3);

        burger.moveIngredient(0, 2);

        assertEquals(ingredient2, burger.ingredients.get(0));
        assertEquals(ingredient3, burger.ingredients.get(1));
        assertEquals(ingredient1, burger.ingredients.get(2));
    }

    // Тесты для getPrice()
    @Test
    public void testGetPrice() {
        when(bun.getPrice()).thenReturn(100f);
        when(ingredient1.getPrice()).thenReturn(50f);
        when(ingredient2.getPrice()).thenReturn(30f);

        burger.setBuns(bun);
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);

        float expectedPrice = 100f * 2 + 50f + 30f; // 280
        assertEquals(expectedPrice, burger.getPrice(), 0.001);
    }

    // Тесты для getReceipt()
    @Test
    public void testGetReceipt() {
        when(bun.getName()).thenReturn("white bun");
        when(bun.getPrice()).thenReturn(100f);

        when(ingredient1.getType()).thenReturn(IngredientType.SAUCE);
        when(ingredient1.getName()).thenReturn("hot sauce");
        when(ingredient1.getPrice()).thenReturn(50f);

        burger.setBuns(bun);
        burger.addIngredient(ingredient1);

        String receipt = burger.getReceipt();

        // Проверяем ключевые части чека
        assertTrue(receipt.contains("(==== white bun ====)"));
        assertTrue(receipt.contains("= sauce hot sauce ="));
        assertTrue(receipt.contains("Price:"));
        assertTrue(receipt.contains("250"));
    }
}

