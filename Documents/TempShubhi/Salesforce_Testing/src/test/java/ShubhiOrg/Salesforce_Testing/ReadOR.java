package ShubhiOrg.Salesforce_Testing;

import java.io.File;
import java.io.FileInputStream;
//import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
//import org.testng.annotations.Test;


public class ReadOR {


public static void main(String[] args) throws IOException{

// Specify the file location I used . operation here because
//we have object repository inside project directory only
File src=new File("ObjectRepository.properties");

// Create  FileInputStream object
FileInputStream fis=new FileInputStream(src);

// Create Properties class object to read properties file
Properties pro=new Properties();

// Load file so we can use into our script
pro.load(fis);

System.out.println("Property class loaded");
System.out.println("Username Property : "+ pro.getProperty("Login_UserName"));
System.out.println("Passowrd Property : "+ pro.getProperty("Login_Password"));
//// Open FirefoxBrowser
//WebDriver driver=new FirefoxDriver();
//
//// Maximize window
//driver.manage().window().maximize();
//Login_UserName.Xpath = //*[@id='username']
//Login_Password.Xpath = //*[@id='password']
//Ligin_Button.Xpath = //*[@id='Login']
//// Pass application
//driver.get("http://www.facebook.com");

// Enter username here I used keys which is specified in Object repository.
// Here getProperty is method which
// will accept key and will return value for the same
//driver.findElement(By.xpath(pro.getProperty("facebook.login.username.xpath"))).
//sendKeys("Selenium@gmail.com");
//
//// Enter password here I used keys which is specified in Object repository.
//// Here getProperty is method which
//// will accept key and will return value for the same
//driver.findElement(By.xpath(pro.getProperty("facebook.login.password.xpath"))).
//sendKeys("adsadasdas");
//
//// Click on login button here I used keys which is specified in Object repository.
//// Here getProperty is method which
//// will accept key and will return value for the same
//driver.findElement(By.xpath(pro.getProperty("facebook.login.Signup.xpath"))).click();

}

}
