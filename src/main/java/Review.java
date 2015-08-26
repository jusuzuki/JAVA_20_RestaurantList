import java.util.List;
import org.sql2o.*;

public class Review {
  private int id;//why? don't know
  private String review_description;
  private Integer ranking;
  private Integer reviewer_id;
  private String review_date;
  private Integer restaurant_id;

  public Review(String review_description, Integer ranking, Integer reviewer_id, String review_date, Integer restaurant_id){
    this.review_description = review_description;
    this.ranking = ranking;
    this.reviewer_id = reviewer_id;
    this.review_date = review_date;
    this.restaurant_id = restaurant_id;
  }

  public String getDescription(){
    return review_description;
  }

  public Integer getRanking(){
    return ranking;
  }

  public Integer getReviewerId(){
    return reviewer_id;
  }

  public String getReviewDate(){
    return review_date;
  }

  public Integer getRestaurantId(){
    return restaurant_id;
  }

  public static String getReviewer(Integer input_id){
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT DISTINCT user_name FROM reviews JOIN users ON (users.id = reviews.reviewer_id) WHERE reviews.reviewer_id =:input_id";
      String reviewer = con.createQuery(sql)
        .addParameter("input_id", input_id)
        .executeAndFetchFirst(String.class);
        return reviewer;
      }
  }

  public void save() {
  try(Connection con = DB.sql2o.open()) {
    String sql = "INSERT INTO reviews (review_description, ranking, reviewer_id, review_date, restaurant_id) VALUES (:review_description, :ranking, :reviewer_id, :review_date, :restaurant_id)";
    this.id = (int) con.createQuery(sql, true)
      .addParameter("review_description", review_description)
      .addParameter("ranking", ranking)
      .addParameter("reviewer_id", reviewer_id)
      .addParameter("review_date", review_date)
      .addParameter("restaurant_id", restaurant_id)
      .executeUpdate()
      .getKey();
    }
  }

  public static List<Review> listReviews(Integer restaurant_id){
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM reviews WHERE restaurant_id=:restaurant_id";
      List<Review> reviews = con.createQuery(sql)
        .addParameter("restaurant_id", restaurant_id)
        .executeAndFetch(Review.class);
        return reviews;
      }
  }

}
