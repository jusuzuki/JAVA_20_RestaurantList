import org.junit.rules.ExternalResource;
import org.sql2o.*;

public class DatabaseRule extends ExternalResource {

  protected void before() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/dining_list_test", null, null);
   }

  protected void after() {
    try(Connection con = DB.sql2o.open()) {
      String deleteRestaurantsQuery = "DELETE FROM restaurants *;";
      //String deleteCategoriesQuery = "DELETE FROM categories *;";
      con.createQuery(deleteRestaurantsQuery).executeUpdate();
      //con.createQuery(deleteCategoriesQuery).executeUpdate();

    }
  }
}
