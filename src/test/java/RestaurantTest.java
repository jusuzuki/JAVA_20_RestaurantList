import org.junit.*;
import static org.junit.Assert.*;

public class RestaurantTest {
  @Rule
  public DatabaseRule  database= new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
    assertEquals(Restaurant.all().size(), 0);
  }

  @Test
  public void all_savesIntoDatabase_true() {
  Restaurant myRestaurant = new Restaurant("Chipotle", "Texmex", 5, true);
  myRestaurant.save();
  assertEquals(Restaurant.all().get(0).getName(), "Chipotle");
  }

  @Test
  public void restaurants_instantiatesCorrectly_true() {
    Restaurant test = new Restaurant("Chipotle","Texmex", 5, true);
    assertEquals(true, test instanceof Restaurant);
  }

  @Test
    public void find_findsTaskInDatabase_true() {
      Restaurant myRestaurant = new Restaurant("Chipotle", "Texmex", 5, true);
      myRestaurant.save();
      Restaurant savedRestaurant = Restaurant.find(myRestaurant.getId());
      assertEquals(savedRestaurant.getName(), "Chipotle");
    }
}
