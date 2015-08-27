import org.sql2o.*;

public class User {

  private int id;
  private String user_name;
  private String user_password;

  public User (String user_name, String user_password){
    this.user_name = user_name;
    this.user_password = user_password;
  }

  public String getUserName(){
    return user_name;
  }

  public String getPassword(){
    return user_password;
  }

  public int getId() {
    return id;
  }

  public boolean checkExistingUser(String inputtedUsername){
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT user_name FROM users WHERE user_name = :inputtedUsername";
      String userExists  = con.createQuery(sql)
        .addParameter("inputtedUsername",inputtedUsername)
        .executeAndFetchFirst(String.class); //multiple?

        if(userExists != null){
          return true;
        }

        else{
          return false;
        }

      } //try
  }

  public void save() {
  try(Connection con = DB.sql2o.open()) {
    String sql = "INSERT INTO users (user_name, user_password) VALUES (:user_name, :user_password)";
    this.id = (int) con.createQuery(sql, true)
      .addParameter("user_name",user_name)
      .addParameter("user_password",user_password)
      .executeUpdate()
      .getKey();
      }
    }

}//close main
