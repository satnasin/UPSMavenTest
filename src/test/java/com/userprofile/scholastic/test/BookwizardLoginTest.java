package com.userprofile.scholastic.test;

import java.util.Hashtable;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.userprofile.scholastic.base.BaseSetup;
import com.userprofile.scholastic.base.RunControl;
import com.userprofile.scholastic.pages.BookwizardLoginPage;

public class BookwizardLoginTest
{
	@Test(dataProvider="gTestData")
	public void bookwizardLogin(Hashtable<String,String> data)
 {
			
			//WebDriver driver = BaseSetup.setDriver();
			WebDriver driver = BaseSetup.startBrowser("chrome", "http://www.scholastic.com/bookwizard/");
			BookwizardLoginPage lpage = PageFactory.initElements(driver, BookwizardLoginPage.class);
			lpage.clickGo();
			lpage.twait();
			driver.switchTo().frame("GB_frame");
			lpage.typeEmail(data.get("Email"));
			lpage.typePassword(data.get("Password"));
			lpage.clicksignIn();
			lpage.twait();
			
			if (lpage.checkSignout()) {
					Reporter.log("Login In successful",true);
					Assert.assertEquals(true, true);
					
				} else {
					System.out.println("No Login");
					Reporter.log("Login not successful", true);
					Assert.assertEquals(false, true);
					
				}
				
	}
	@BeforeTest
	public void checkRunmode()
	{
		 boolean run = RunControl.checkTestRunMode("BookwizardLoginTest");
		 if(!run){
			 throw new SkipException("Skipping the test as Runmode is NO");
		 }
	}
	
	@DataProvider
	public Object[][] gTestData(){
		return RunControl.getTestData("BookwizardLoginTest");
	}
	
	
}
