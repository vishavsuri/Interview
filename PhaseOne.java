package weatherCheck;

import java.util.Scanner;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class PhaseOne {

	public static String WeatherCheck() {
		
			System.out.println("Please enter cityName to check weather: ");			
			Scanner sc=new Scanner(System.in);
			String cityName=sc.nextLine();
			WebDriverManager.chromedriver().setup();
			WebDriver driver=new ChromeDriver();
			WebDriverWait wait=new WebDriverWait(driver,6);
			try {
			driver.get("https://www.ndtv.com/");
			driver.manage().window().maximize();
			
			if(driver.findElement(By.xpath("//*[@class='notnow']")).isDisplayed()) {
				driver.findElement(By.xpath("//*[@class='notnow']")).click();
			}
			driver.findElement(By.xpath("//a[@class='topnavmore']")).click();
			driver.findElement(By.xpath("//a[text()='WEATHER']")).click();	
			
			wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[text()='Pin your City']"))));
			if(driver.findElement(By.xpath("//*[text()='Pin your City']")).isDisplayed()) {
				
				driver.findElement(By.xpath("//*[@id='searchBox']")).sendKeys(cityName);
				Thread.sleep(1000);
				driver.findElement(By.xpath("//*[@id='"+cityName+"' and @type='checkbox']")).click();		
			}
			
			if(driver.findElement(By.xpath("//*[@class='cityText' and text()='"+cityName+"']")).isDisplayed()) {
				driver.findElement(By.xpath("//*[@class='cityText' and text()='"+cityName+"']")).click();
				System.out.println("Searched city is displaying");
			}
			
			if(driver.findElement(By.xpath("//*[@class='leaflet-popup-content-wrapper']")).isDisplayed()) {
				String tempInCelcius=driver.findElement(By.xpath("//*[contains(text(),'Temp in Degrees:')]")).getText();
				System.out.println("Weather details are displaying");
				System.out.println(cityName+" "+tempInCelcius+" C");
			}
			
			driver.quit();
			sc.close();
		
				}catch(Exception ex) {
				ex.printStackTrace();	
				}
		return cityName;
		
		
	}
	

}
