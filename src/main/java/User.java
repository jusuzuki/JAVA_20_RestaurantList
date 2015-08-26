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

}
