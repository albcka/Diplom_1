package praktikum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.List;
import static org.junit.Assert.assertEquals;


@RunWith(Parameterized.class)
public class BurgerParamTest {
    private final Bun bun;
    private final Ingredient firstIngredient;
    private final Ingredient secondIngredient;
    private final float expectedPrice;
    private final Burger burger = new Burger();

    public BurgerParamTest(Bun bun, Ingredient firstIngredient, Ingredient secondIngredient, float expectedPrice){
        this.expectedPrice = expectedPrice;
        this.bun = bun;
        this.firstIngredient = firstIngredient;
        this.secondIngredient = secondIngredient;
    }

    @Parameterized.Parameters(name = "Бургер с ожидаемой стоимостью: {3}")
    public static Object[][] getTestParameters() {
        Database database = new Database();
        List<Bun> buns = database.availableBuns();
        List<Ingredient> ingredients = database.availableIngredients();
        return new Object[][]{
                {buns.get(0), ingredients.get(2), ingredients.get(3), 600},
                {buns.get(1), ingredients.get(0), ingredients.get(4), 700},
                {buns.get(2), ingredients.get(1), ingredients.get(5), 1100},
        };
    }
    // Тесты для getPrice()
    @Test
    public void testGetPrice() {

        burger.setBuns(bun);
        burger.addIngredient(firstIngredient);
        burger.addIngredient(secondIngredient);

        assertEquals(expectedPrice, burger.getPrice(), 0.001);
    }

    // Тесты для getReceipt()
    @Test
    public void testGetReceipt() {

        burger.setBuns(bun);
        burger.addIngredient(firstIngredient);
        burger.addIngredient(secondIngredient);

        String expectedReceipt = String.format(
                "(==== %s ====)%n" +
                        "= %s %s =%n" +
                        "= %s %s =%n" +
                        "(==== %s ====)%n" +
                        "%nPrice: %f%n",
                bun.getName(),
                firstIngredient.getType().toString().toLowerCase(), firstIngredient.getName(),
                secondIngredient.getType().toString().toLowerCase(), secondIngredient.getName(),
                bun.getName(),
                expectedPrice
        );
        assertEquals("Цена должна совпадать с ожидаемой", expectedReceipt, burger.getReceipt());
    }
}
