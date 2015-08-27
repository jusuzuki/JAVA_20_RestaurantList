import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

import java.util.HashMap;
import java.util.Iterator;
import java.time.LocalDateTime;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";


    //homepage with a list of all the restaurants
    get("/", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();

      //start session
      String inputtedUsername = request.queryParams("username");

      //check for not-null username
      if (inputtedUsername != null){

        request.session().attribute("username", inputtedUsername);
        model.put("username", inputtedUsername);
        User newUser = new User(inputtedUsername, "123");

        if (newUser.checkExistingUser(inputtedUsername) == true){
          Integer userId = newUser.getId();
          request.session().attribute("userId");
        }


        else{
        newUser.save();
        Integer userId = newUser.getId();
        request.session().attribute("userId", userId);
        }
      }

      List<Restaurant> restaurants = Restaurant.all();

      List<String> types = Restaurant.listTypes();

      model.put("restaurants",restaurants);
      model.put("types",types);
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    //form to add new restaurant (same?)
    get("/restaurants/new", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();


      model.put("template", "templates/restaurant-form.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());


    //after adding new restaurant update restaurant list
    post("/restaurants", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      String name = request.queryParams("name");
      String type = request.queryParams("type");
      String veganString = request.queryParams("vegan");
      boolean vegan = veganString.equals("yes");
      String price_range = request.queryParams("price_range");
      String area = request.queryParams("area");
      String address = request.queryParams("address");
      String phone = request.queryParams("phone");
      String website = request.queryParams("website");
      int ranking_average = Integer.parseInt(request.queryParams("ranking_average"));

      Restaurant newRestaurant = new Restaurant(name,type,ranking_average,vegan, price_range, area, address, phone, website);
      newRestaurant.save();

      List<Restaurant> restaurants = Restaurant.all();
      List<String> types = Restaurant.listTypes();
      model.put("restaurants",restaurants);
      model.put("types",types);
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());


    //page for a specific restaurant
    get("/restaurants/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String,Object>();
      int restaurantId = Integer.parseInt(request.params(":id"));
      Restaurant restaurant = Restaurant.find(restaurantId);

      //get the username from the session
      model.put("username", request.session().attribute("username"));
      Integer reviewer_id = request.session().attribute("userId");// doesn't need two arguments because we are just retrieving!
      String reviewer = Review.getReviewer(reviewer_id);
      model.put("reviewer", reviewer);

      // get reviews by restaurant id
      List<Review> listreviews = Review.listReviews(restaurantId);
      model.put("listreviews", listreviews);

      model.put("restaurant", restaurant);
      model.put("template", "templates/restaurant.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());


    //remove restaurant
    get("/restaurants/:id/removerestaurant", (request,response) -> {
      HashMap<String,Object> model = new HashMap<String,Object>();
      int restaurantId = Integer.parseInt(request.params(":id"));
      Restaurant.removeRestaurant(restaurantId);
      List<Restaurant> restaurants = Restaurant.all();
      model.put("restaurants",restaurants);
      model.put("template","templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    //search restaurant by type
    post("/searchresults", (request,response) -> {
      HashMap<String,Object> model = new HashMap<String,Object>();
      String searchtype = request.queryParams("type");
      List<Restaurant> searchresults = Restaurant.findType(searchtype);
      List<String> types = Restaurant.listTypes();
      model.put("types",types);
      model.put("searchresults", searchresults);
      model.put("template","templates/searchresults.vtl");

      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    // //add review to restaurant
    post("/restaurants/:id", (request,response) -> { //This section does not work! :(
       HashMap<String,Object> model = new HashMap<String,Object>();
       int restaurantId = Integer.parseInt(request.params(":id"));

       Restaurant restaurant = Restaurant.find(restaurantId);

       //get the username from the session
       model.put("username", request.session().attribute("username"));
       Integer reviewer_id = request.session().attribute("userId");// doesn't need two arguments because we are just retrieving!

       // get reviewer by user id
       String reviewer = Review.getReviewer(reviewer_id);
       model.put("reviewer", reviewer);

       //post new review
       String review_description = request.queryParams("review_description");
       Integer ranking = Integer.parseInt(request.queryParams("ranking"));
       String review_date = request.queryParams("review_date");

       Review newReview = new Review(review_description, ranking, reviewer_id, review_date, restaurantId);
       newReview.save();

       // get reviews by restaurant id
       List<Review> listreviews = Review.listReviews(restaurantId);
       model.put("listreviews", listreviews);

       //add restaurant to model
       model.put("restaurant", restaurant);


      model.put("template","templates/restaurant.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

 }
}
