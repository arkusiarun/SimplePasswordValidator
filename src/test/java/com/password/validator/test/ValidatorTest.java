package com.password.validator.test;

import org.testng.Assert;
import org.testng.annotations.*;
import com.password.validator.*;

public class ValidatorTest {

	private static IValidator validator;

	@BeforeClass
	public static void initValidator() {
		validator = new Validator();
	}

	@DataProvider
	public Object[][] ValidPasswordProvider() {
		return new Object[][] { { new String[] { "n!k@sn1Kos", "J@vaC0deG##ks", } } };
	}

	@DataProvider
	public Object[][] InvalidPasswordProvider() {
		return new Object[][] { { new String[] { "mY1A@", "aaaaA12@", "!@#$%jkibyyA" } } };
	}

	@Test(dataProvider = "ValidPasswordProvider")
	public void ValidPasswordTest(String[] password) {

		for (String temp : password) {
			boolean valid = validator.validateNewPassword(temp);
			System.out.println("Password is valid : " + temp + " , " + valid);
			Assert.assertEquals(true, valid);
		}

	}

	@Test(dataProvider = "InvalidPasswordProvider", dependsOnMethods = "ValidPasswordTest")
	public void InValidPasswordTest(String[] password) {

		for (String temp : password) {
			boolean valid = validator.validateNewPassword(temp);
			System.out.println("Password is valid : " + temp + " , " + valid);
			Assert.assertEquals(false, valid);
		}
	}
}
