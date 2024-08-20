package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class WildberriesScript {

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();

        try {
            driver.manage().window().setSize(new Dimension(1920, 1080));

            driver.get("https://www.wildberries.ru/");

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

            WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("searchInput")));
            Thread.sleep(5000);
            searchBox.sendKeys("Pencil");

            WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("applySearchBtn")));
            searchButton.click();

            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".general-preloader")));

            WebElement firstProduct = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".product-card__wrapper")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", firstProduct);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", firstProduct);

            WebElement addToCartButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".btn-main")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addToCartButton);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addToCartButton);

            Thread.sleep(5000);

            WebElement cartButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[data-wba-header-name='Cart']")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", cartButton);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", cartButton);

            WebElement cartItem = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".basket-item")));
            if (cartItem != null) {
                System.out.println("Товар успешно добавлен в корзину!");
            } else {
                System.out.println("Товар не был добавлен в корзину.");
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}
