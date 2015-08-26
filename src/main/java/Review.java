import java.util.List;
import org.sql2o.*;

public class Review {

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

  public static List<String> listReviews(Integer restaurant_id){
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT review_description FROM reviews WHERE restaurant_id=:restaurant_id";
      List<String> reviews = con.createQuery(sql)
        .addParameter("restaurant_id", restaurant_id)
        .executeAndFetch(String.class);
        return reviews;
      }
  }

}
