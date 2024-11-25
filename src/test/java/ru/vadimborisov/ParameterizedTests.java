package ru.vadimborisov;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class ParameterizedTests {

  @BeforeAll
  static void beforeAll() {
    Configuration.browserPosition = "0x0";
    Configuration.browserSize = "1920x1080";
    Configuration.pageLoadStrategy = "none";
  }

  @BeforeEach
  void setup() {
    open("https://mvnrepository.com/");
  }

  @ValueSource(strings = {
          "Spring Web", "JUnit Jupiter API"
  })
  @ParameterizedTest(name = "При запросе {0} в поисковой выдаче maven должен быть первым в списке {0}")
  void searchStringsParametersInMavenTest(String testData) {
    $("#query").setValue(testData);
    $("input[type=submit]").click();
    $("div[role=main]").$$(".im").get(0).shouldHave(text(testData));
  }
}
