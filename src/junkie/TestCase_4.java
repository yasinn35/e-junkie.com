package junkie;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import utility.BaseDriver;

public class TestCase_4 extends BaseDriver {

    @Test
    public void US4() {

        driver.get("https://e-junkie.com/wiki/demo");

        WebElement addToCart_eBook = driver.findElement(By.xpath("//a[text()='Add to Cart']"));
        addToCart_eBook.click();

        WebElement iframe = driver.findElement(By.xpath("//iframe[@class='EJIframeV3 EJOverlayV3']"));
        wait.until(ExpectedConditions.visibilityOf(iframe));
        driver.switchTo().frame(iframe);

        WebElement payUsingDebitCard = driver.findElement(By.cssSelector("button[class='Payment-Button CC']"));
        wait.until(ExpectedConditions.visibilityOf(payUsingDebitCard));
        payUsingDebitCard.click();

        WebElement emailInput = driver.findElement(By.cssSelector("input[placeholder='Email']"));
        wait.until(ExpectedConditions.visibilityOf(emailInput));
        emailInput.sendKeys("test@email.com");

        WebElement confirmEmail = driver.findElement(By.cssSelector("input[placeholder='Confirm Email']"));
        confirmEmail.sendKeys("test@email.com");

        WebElement name = driver.findElement(By.cssSelector("input[placeholder='Name On Card']"));
        name.sendKeys("Brian");

        WebElement phone = driver.findElement(By.cssSelector("input[autocomplete='phone']"));
        phone.sendKeys("123456789");

        WebElement company = driver.findElement(By.cssSelector("input[autocomplete='company']"));
        company.sendKeys("technoStudy");

        WebElement address = driver.findElement(By.xpath("//p[@class='Billing-Address1']//input"));
        address.sendKeys("Green Street");

        WebElement city = driver.findElement(By.xpath("//p[@class='Billing-City Inline MarginRight']//input"));
        city.sendKeys("New York");

        WebElement postalCode = driver.findElement(By.xpath("//p[@class='Billing-PostCode Inline']//input"));
        postalCode.sendKeys("10001");

        WebElement cardNumberInput = driver.findElement(By.xpath("//p[@class='Card-Number']/input"));
        wait.until(ExpectedConditions.invisibilityOf(payUsingDebitCard));
        cardNumberInput.sendKeys("4242 4242 4242 4242");

        WebElement expDate = driver.findElement(By.xpath("//p[@class='Card-Expiry Inline MarginRight']/input"));
        expDate.sendKeys("12/28");

        WebElement cvc = driver.findElement(By.xpath("//p[@class='Card-CVV Inline']/input"));
        cvc.sendKeys("315");

        driver.switchTo().defaultContent();

        driver.switchTo().frame(iframe);
        WebElement payButton = driver.findElement(By.cssSelector("button[class='Pay-Button']"));
        wait.until(ExpectedConditions.invisibilityOf(payUsingDebitCard));
        payButton.click();

        WebElement tyMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='green_text_margin']")));

        Assert.assertTrue(tyMessage.getText().contains("your order is confirmed. Thank you!"));

        driver.switchTo().defaultContent();

        delayQuit();
    }
}
