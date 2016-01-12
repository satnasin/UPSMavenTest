package rough;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Regtest {
	public static boolean y=false;
	public static void main(String[] args) {
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("http://infinityring.scholastic.com/#");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//div[@class='login-box']/a[1]")).click();
		
		//boolean y = false;
		try {
			y = driver.findElement(By.xpath("//div[@class='modal-content login hub-popup']/section[2]/a")).isDisplayed();
			if(y){
				System.out.println("presnet");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(y+"chcking for reg window");
		}
		
		
	}

}
