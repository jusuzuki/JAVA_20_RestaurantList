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
  Restaurant myRestaurant = new Restaurant("Chipotle", "Texmex", 5, true, "5-10", "SW", "SW 3rd Ave", "212-555-NYC-calling", "wwww.chipotle.com");
  myRestaurant.save();
  assertEquals(Restaurant.all().get(0).getName(), "Chipotle");
  }

  @Test
  public void restaurants_instantiatesCorrectly_true() {
    Restaurant test = new Restaurant("Chipotle", "Texmex", 5, true, "5-10", "SW", "SW 3rd Ave", "212-555-NYC-calling", "wwww.chipotle.com");
    assertEquals(true, test instanceof Restaurant);
  }

  @Test
    public void find_findsRestaurantInDatabase_true() {
      Restaurant myRestaurant = new Restaurant("Chipotle", "Texmex", 5, true, "5-10", "SW", "SW 3rd Ave", "212-555-NYC-calling", "wwww.chipotle.com");
      myRestaurant.save();
      Restaurant savedRestaurant = Restaurant.find(myRestaurant.getId());
      assertEquals(savedRestaurant.getName(), "Chipotle");
    }
}
