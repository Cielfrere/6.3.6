import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import options.Options;
import java.time.Duration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WildberriesScript {

    private static final Logger logger = LogManager.getLogger(WildberriesScript.class);
    private static WebDriver driver;

    @BeforeAll
    public static void setUp() {
        driver = Options.option();
    }

    @AfterAll
    public static void tearDown() {
        driver.quit();
    }

    @Test
    public void wildberriesTestScript() throws InterruptedException {
        driver.get("https://www.wildberries.ru");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("searchInput")));
        Thread.sleep(5000);
        searchBox.sendKeys("Pencil");

        WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("applySearchBtn")));
        searchButton.click();

        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".general-preloader")));

        WebElement firstProduct = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector
                (".product-card__wrapper")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", firstProduct);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", firstProduct);

        WebElement addToCartButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector
                (".btn-main")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addToCartButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addToCartButton);

        WebElement cartButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector
                ("a[data-wba-header-name='Cart']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", cartButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", cartButton);

        WebElement cartItem = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a[data-wba-header-name='Cart']")));
        Thread.sleep(5000);

        if (!cartItem.isDisplayed()) {
            throw new RuntimeException("Не удалось добавить товар в корзину");

        } else {
            logger.info("Товар успешно добавлен в корзину");
        }
    }
}
