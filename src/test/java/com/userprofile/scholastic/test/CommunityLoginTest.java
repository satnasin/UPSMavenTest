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

import com.userprofile.scholastic.base.*;
import com.userprofile.scholastic.pages.CommunityLoginPage;

public class CommunityLoginTest 
{
	@Test (dataProvider="gTestData")
	public void checkValidUser(Hashtable<String, String>data)
	{
		//WebDriver driver = BaseSetup.setDriver();
		WebDriver driver = BaseSetup.startBrowser("firefox", "http://community.scholastic.com/");
		CommunityLoginPage login_page = PageFactory.initElements(driver, CommunityLoginPage.class);
		login_page.clickSignIn();
		String text_loginPopUp=login_page.checkSignInNowPopUp();
		Reporter.log("Login Popup for Stacks >"+text_loginPopUp,true);
		Assert.assertEquals("Sign in now.", text_loginPopUp);
		//System.out.println("******"+text_loginPopUp);
		login_page.typeUname(data.get("uName"));
		login_page.typePassword(data.get("password"));
		login_page.clickGo();
		BaseSetup.twait();
		if (login_page.checkCancelPopup()) {
			Reporter.log("Login In not successful",true);
			Assert.assertEquals(true, false);
			
		} else {
			System.out.println("Login In successful");
			Reporter.log("Login In successful", true);
			Assert.assertEquals(true, true);
			
		}
		
				
	}
		
	@BeforeTest
	public void checkRunmode()
	{
		 boolean run = RunControl.checkTestRunMode("CommunityLoginTest");
		 if(!run){
			 throw new SkipException("Skipping the test as Runmode is NO");
		 }
	}
	
	@DataProvider
	public Object[][] gTestData(){
		return RunControl.getTestData("CommunityLoginTest");
	}
	

}
