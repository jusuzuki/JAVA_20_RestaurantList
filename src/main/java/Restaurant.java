import java.util.List;
import org.sql2o.*;
import java.util.HashSet;
import java.util.ArrayList;

public class Restaurant {
  private int id;
  private String name;
  private String type;
  private Integer ranking_average;
  private boolean vegan;
  private String price_range;
  private String area;
  private String address;
  private String phone;
  private String website;


  public Restaurant(String name, String type, Integer ranking_average, boolean vegan, String price_range, String area, String address, String phone, String website) {
    this.name = name;
    this.type = type;
    this.ranking_average = ranking_average;
    this.vegan = vegan;
    this.price_range = price_range;
    this.area = area;
    this.address = address;
    this.phone = phone;
    this.website = website;
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

  public int getRankingAverage(){
    return ranking_average;
  }

  public String rankingStars(int ranking_average){
    String htmlString = "<span class=\"glyphicon glyphicon-star\" aria-hidden=\"true\"></span>";
    for (int i = 2; i <=ranking_average; i++){
      htmlString = htmlString + "<span class=\"glyphicon glyphicon-star\" aria-hidden=\"true\"></span>";
    }
    //System.out.println(htmlString);
    return htmlString;
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

  public String getPriceRange(){
    return price_range;
  }

  public String getArea(){
    return area;
  }

  public String getAddress(){
    return address;
  }

  public String getPhone(){
    return phone;
  }

  public String getWebsite(){
    return website;
  }

  public static List<Restaurant> all() {
    String sql = "SELECT * FROM restaurants ORDER BY type";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Restaurant.class);
    }
  }

  public void save() {
  try(Connection con = DB.sql2o.open()) {
    String sql = "INSERT INTO restaurants (name, type, ranking_average, vegan, price_range, area, address, phone, website) VALUES (:name, :type, :ranking_average, :vegan, :price_range, :area, :address, :phone, :website)";
    this.id = (int) con.createQuery(sql, true)
      .addParameter("name",name)
      .addParameter("type",type)
      .addParameter("ranking_average",ranking_average)
      .addParameter("vegan",vegan)
      .addParameter("price_range",price_range)
      .addParameter("area",area)
      .addParameter("address",address)
      .addParameter("phone",phone)
      .addParameter("website",website)
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

  public static void removeRestaurant(int restaurantId) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM restaurants WHERE id=:id;";
      con.createQuery(sql)
        .addParameter("id",restaurantId)
        .executeUpdate();
    }
  }

  public static List<String> listTypes() {
  try(Connection con = DB.sql2o.open()) {
    String sql = "SELECT DISTINCT type FROM restaurants;";
    List<String> types = con.createQuery(sql)
      .executeAndFetch(String.class);
      return types;
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

}
