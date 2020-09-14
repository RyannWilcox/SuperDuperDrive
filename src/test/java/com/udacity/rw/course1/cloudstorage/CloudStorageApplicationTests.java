package com.udacity.rw.course1.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CloudStorageApplicationTests {

	@LocalServerPort
	private int port;

	private WebDriver driver;

	@BeforeAll
	static void beforeAll() {
		WebDriverManager.chromedriver().setup();
	}

	@BeforeEach
	public void beforeEach() {
		this.driver = new ChromeDriver();
	}

	@AfterEach
	public void afterEach() {
		if (this.driver != null) {
			driver.quit();
		}
	}


	/**
	 * Attempt to access the homepage without being logged in.
	 */
	@Test
	public void attemptToAccessUnauthorizedPage() {
		driver.get("http://localhost:" + this.port + "/home");
		Assertions.assertEquals("Login", driver.getTitle());
	}

	@Test
	public void signupSignoutAuthorization() {

		/**
		 * Sign up, login and then make sure the home page is accessible
		 */
		driver.get("http://localhost:" + this.port + "/signup");
		SignupPage signupPage = new SignupPage(driver);
		signupPage.signup("Ryan","Wilcox","rwilcox","password123");

		driver.get("http://localhost:" + this.port + "/login");
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login("rwilcox","password123");

		Assertions.assertEquals("Home",driver.getTitle());

		/**
		 * Logout, attempt to access the home page, it should be inaccessible
		 */
		HomePage homePage = new HomePage(driver);
		homePage.logout();
		driver.get("http://localhost:" + this.port + "/home");

		Assertions.assertEquals("Login", driver.getTitle());


	}
}
