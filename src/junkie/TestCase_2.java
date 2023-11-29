package junkie;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import utility.BaseDriver;

public class TestCase_2 extends BaseDriver {

    @Test
    public void US2() {
        driver.get("https://e-junkie.com/wiki/demo");

        WebElement addToCart_eBook = driver.findElement(By.xpath("//a[text()='Add to Cart']"));
        addToCart_eBook.click();

        WebElement iframe = driver.findElement(By.xpath("//iframe[@class='EJIframeV3 EJOverlayV3']"));
        wait.until(ExpectedConditions.visibilityOf(iframe));
        driver.switchTo().frame(iframe);

        WebElement payUsingDebitCard = driver.findElement(By.cssSelector("button[class='Payment-Button CC']"));
        wait.until(ExpectedConditions.visibilityOf(payUsingDebitCard));
        payUsingDebitCard.click();

        driver.switchTo().defaultContent();
        driver.switchTo().frame(iframe);
        WebElement payButton = driver.findElement(By.cssSelector("button[class='Pay-Button']"));
        wait.until(ExpectedConditions.invisibilityOf(payUsingDebitCard));
        payButton.click();

        WebElement invalid = driver.findElement(By.xpath("//div[@id='SnackBar']//span"));
        String invalidTexts = invalid.getText();
        System.out.println(invalidTexts);

        Assert.assertTrue(invalid.isDisplayed());

    }
}
