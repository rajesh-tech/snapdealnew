package org.snapprogram;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SnapDeal {

	public static void main(String[] args) throws Exception {
	WebDriver driver;
		
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Intel\\Downloads\\chromedriver_win32\\chromedriver.exe");
		
		driver = new ChromeDriver();
		
		driver.get("https://www.snapdeal.com");
		
		driver.manage().window().maximize();
		{

	FileInputStream fls = null;
	XSSFWorkbook workbook = null;
	XSSFSheet sheet = null;
	Object[][] excelData = null;
	fls = new FileInputStream("C:\\Users\\Intel\\snapdeal.xlsx");
	workbook = new XSSFWorkbook(fls);
	sheet = workbook.getSheet("Sheet1");

	int rows = sheet.getLastRowNum();
	System.out.println(rows);
	int columns = sheet.getRow(0).getLastCellNum();
	System.out.println(columns);
	excelData = new Object[rows][columns];
	for(int i=1; i<=rows; i++)
	{
	for(int j=0; j<columns; j++)
	{
	excelData[i-1][j] = sheet.getRow(i).getCell(j).getStringCellValue().toString();
	System.out.println(excelData[i-1][j]);
	}
	}
	
	workbook.close();
	
	driver.findElement(By.xpath("//span[text()='Sign In']")).click();
	
	driver.findElement(By.xpath("//a[text()='Your Account']")).click();
	
	Thread.sleep(2000);
	
	driver.findElement(By.name("username")).sendKeys(excelData[0][0].toString());
	
	driver.findElement(By.xpath("//button[text()='continue']")).click();
	
	Thread.sleep(4000);
	
	driver.findElement(By.id("j_password_login_uc")).sendKeys(excelData[0][1].toString());
	
	driver.findElement(By.id("submitLoginUC")).click();
	
	Thread.sleep(4000);
	
	driver.findElement(By.id("inputValEnter")).sendKeys(excelData[0][2].toString());
	
	driver.findElement(By.xpath("//span[@class='searchTextSpan']")).click();
	
	driver.findElement(By.xpath("//input[@class='sd-input']")).sendKeys(excelData[0][3].toString());
	
	driver.findElement(By.xpath("//button[@class='pincode-check']")).click();
	
	
	}

}
}