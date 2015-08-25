import java.util.List;
import org.sql2o.*;
import java.util.HashSet;
import java.util.ArrayList;

public class Restaurant {
  private String name;
  private int id;
  private String type;
  private Integer ranking;
  private boolean vegan;


  public Restaurant(String name, String type, Integer ranking, boolean vegan) {
    this.name = name;
    this.type = type;
    this.ranking = ranking;
    this.vegan = vegan;
  }

  public String getName() {
    return name;
  }

  public int getId() {
    return id;
  }

  public String getType(){
    return type;
  }

  public int getRanking(){
    return ranking;
  }

  public String getVegan(){
    String veganString;
    if (vegan == true){
      veganString = "yes";
    } else {
      veganString = "no";
    }
    return veganString;
  }

  public static List<Restaurant> all() {
    String sql = "SELECT * FROM restaurants ORDER BY type";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Restaurant.class);
    }
  }

  public void save() {
  try(Connection con = DB.sql2o.open()) {
    String sql = "INSERT INTO restaurants (name, type, ranking, vegan) VALUES (:name, :type, :ranking, :vegan)";
    this.id = (int) con.createQuery(sql, true)
      .addParameter("name",name)
      .addParameter("type",type)
      .addParameter("ranking",ranking)
      .addParameter("vegan",vegan)
      .executeUpdate()
      .getKey();
    }
  }

  public static Restaurant find(int id) {
  try(Connection con = DB.sql2o.open()) {
    String sql = "SELECT * FROM restaurants where id=:id";
    Restaurant restaurant = con.createQuery(sql)
      .addParameter("id", id)
      .executeAndFetchFirst(Restaurant.class);
    return restaurant;
    }
  }

  public static List<Restaurant> findType(String searchtype){
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM restaurants where type=:searchtype";
      return con.createQuery(sql)
        .addParameter("searchtype", searchtype)
        .executeAndFetch(Restaurant.class);
      }

  }

  public static void removeRestaurant(int restaurantId) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM restaurants WHERE id=:id;";
      con.createQuery(sql)
        .addParameter("id",restaurantId)
        .executeUpdate();
    }
  }

  public static List<String> dupTypes() {
    String sql = "SELECT type FROM restaurants";

    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql)
        .executeAndFetch(String.class);
      }


  }

  public static List<String> removeDups(List<String> dupList){
    HashSet<String>cleanSet = new HashSet<String>(dupList);
    List<String> cleanList = new ArrayList<String>(cleanSet);
    return cleanList;
  }

}
