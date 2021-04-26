package tqs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class FlightSteps {
    private WebDriver driver;

    @When("I navigate to {string}")
    public void iNavigateTo(String url) {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get(url);
    }

    @And("I choose {string} as my departure city")
    public void iChooseAsMyDepartureCity(String city) {
        Select s1 = new Select(driver.findElement(By.name("fromPort")));
        s1.selectByVisibleText(city);
    }

    @And("I choose {string} as my destination city")
    public void iChooseAsMyDestinationCity(String city) {
        Select s1 = new Select(driver.findElement(By.name("toPort")));
        s1.selectByVisibleText(city);
    }

    @And("I click on {string}")
    public void iClickOn(String btnName) {
        driver.findElement(By.xpath("//input[@value='"+btnName+"']")).click();
    }

    @Then("I am redirected to {string}")
    public void iAmRedirectedTo(String url) {
        assertThat(driver.getCurrentUrl(), is(url));
        driver.quit();
    }
}
