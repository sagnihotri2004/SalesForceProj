package ShubhiOrg.Salesforce_Testing;

import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class ReadOR1 {
  @Test
  public void ReadRepository() throws IOException {
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

	  System.out.println("SUccessful");
  }
  @BeforeMethod
  public void beforeMethod() {
	  System.out.println("SUccessful");
  }

}
