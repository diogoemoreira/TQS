import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SeleniumJupiter.class)
public class p3ex3Test {

    @Test
    public void teste1(FirefoxDriver driver) {
        driver.get("https://blazedemo.com/");
        driver.manage().window().setSize(new Dimension(655, 815));
        driver.findElement(By.name("fromPort")).click();
        driver.findElement(By.cssSelector(".form-inline:nth-child(1) > option:nth-child(1)")).click();
        {
            String value = driver.findElement(By.name("fromPort")).getAttribute("value");
            assertThat(value, is("Paris"));
        }
        driver.findElement(By.name("toPort")).click();
        {
            WebElement dropdown = driver.findElement(By.name("toPort"));
            dropdown.findElement(By.xpath("//option[. = 'Berlin']")).click();
        }
        driver.findElement(By.cssSelector(".form-inline:nth-child(4) > option:nth-child(4)")).click();
        {
            String value = driver.findElement(By.name("toPort")).getAttribute("value");
            assertThat(value, is("Berlin"));
        }
        driver.findElement(By.cssSelector(".btn-primary")).click();
        driver.findElement(By.cssSelector("tr:nth-child(2) .btn")).click();
        driver.findElement(By.id("inputName")).click();
        driver.findElement(By.id("inputName")).sendKeys("Didi");
        {
            String value = driver.findElement(By.id("inputName")).getAttribute("value");
            assertThat(value, is("Didi"));
        }
        driver.findElement(By.id("address")).click();
        driver.findElement(By.id("address")).sendKeys("asdsdad");
        driver.findElement(By.id("city")).sendKeys("asadsadsa");
        driver.findElement(By.id("state")).click();
        driver.findElement(By.id("state")).sendKeys("asdasda");
        driver.findElement(By.id("zipCode")).click();
        driver.findElement(By.id("zipCode")).sendKeys("12345");
        driver.findElement(By.id("creditCardNumber")).click();
        driver.findElement(By.id("creditCardNumber")).sendKeys("11111111");
        driver.findElement(By.id("nameOnCard")).click();
        driver.findElement(By.id("nameOnCard")).sendKeys("Didi");
        driver.findElement(By.cssSelector(".checkbox")).click();
        assertTrue(driver.findElement(By.cssSelector(".checkbox")).isEnabled());
        driver.findElement(By.cssSelector(".btn-primary")).click();
        assertThat(driver.getTitle(), is("BlazeDemo Confirmation"));
    }
}
