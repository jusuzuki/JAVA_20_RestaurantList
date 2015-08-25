// import org.fluentlenium.adapter.FluentTest;
// import org.junit.ClassRule;
// import org.junit.Test;
// import org.openqa.selenium.WebDriver;
// import org.openqa.selenium.htmlunit.HtmlUnitDriver;
//
// import static org.fluentlenium.core.filter.FilterConstructor.*;
// import static org.assertj.core.api.Assertions.assertThat;
//
// public class AppIntegrationTest extends FluentTest {
//   public WebDriver webDriver = new HtmlUnitDriver();
//
//   @Override
//   public WebDriver getDefaultDriver() {
//       return webDriver;
//   }
//
//   @ClassRule
//   public static ServerRule server = new ServerRule();
//
//   // @Test
//   // public void rootTest() {
//   //   goTo("http://localhost:4567/");
//   //   assertThat(pageSource()).contains("Todo list!");
//   // }
//
//   // @Test
//   // public void taskIsCreatedTest() {
//   //   goTo("http://localhost:4567/");
//   //   click("a", withText("Add a new task"));
//   //   fill("#description").with("Mow the lawn");
//   //   submit(".btn");
//   //   assertThat(pageSource()).contains("Your task has been saved.");
//   // }
//
//   // @Test
//   // public void taskShowPageDisplayDescription() {
//   //   goTo("http://localhost:4567/tasks/new");
//   //   fill("#description").with("Mow the lawn");
//   //   submit(".btn");
//   //   click("a", withText("view all tasks"));
//   //   click("a", withText("Mow the lawn"));
//   //   assertThat(pageSource()).contains("Mow the lawn");
//   // }
// }
