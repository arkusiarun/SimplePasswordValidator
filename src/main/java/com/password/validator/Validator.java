package com.password.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;
import java.lang.Math;

public class Validator implements IValidator{
	/*
	 * This Pattern Suffices for First 2 password Requirement.
	 * (?=.*[a-z])     : Check For At Least One Lower Case Alphabet.
	 * (?=.*d)         : Check For At Least One Numeric Value i.e. 0-9.
	 * (?=.*[@#$!&*])  : Check For At Least One Special Character out of @#$!&*.
	 * ((?=.*[A-Z])    : Check For At Least One Capital Letter.
	 * {6,}            : Limits length to Minimum of 6.
	 */
	static String pattern1 = "((?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?=.*[@#$!&*]).{6,40})";
	
	/*
	 * Pattern to check for Consecutive Characters Consecutive Occurrence shouldn't
	 * be more than 3.
	 */
	static String pattern2 = "([a-z])\\1{3}";

	/*
	 * Check for all Alphanumeric Characters.
	 */
	static String pattern3 = "[a-zA-Z0-9]";
	
	/*
	 * Check for Numeric Values.
	 */
	static String pattern4 = "[^0-9]";
	
	/**
	 * This method Check for String Length and Contents as per Pattern1.
	 * @param newPass
	 * @return
	 */
	
	/*
	 * Stack to 
	 */
	public static String oldPassword = "Arkusi@1";
	
	public boolean validationStage1(final String newPass) {
		Pattern p = Pattern.compile(pattern1);
		Matcher m = p.matcher(newPass);
		return m.matches();
	}
	
	/**
	 * This Method CHeck for Repeated Occurrence of Character as per Pattern2.
	 * @param newPass
	 * @return
	 */
	public boolean validationStage2(final String newPass) {
		Pattern pattern = Pattern.compile(pattern2);
		Matcher matcher = pattern.matcher(newPass);
		return !(matcher.find());
	}

	/**
	 * This Method Validates count of Special Characters.
	 * Returns False if More than 4 Special Characters are Present.
	 * @param newPass
	 * @return
	 */
	public boolean validationStage3(final String newPass) {
		String str = newPass.replaceAll(pattern3, "");
		boolean result = (str.length() <= 4) ? true : false;
		return result;
	}

	/**
	 * This Method Checks for the count of Numeric Characters.
	 * Return False if 50% or More Characters are Numeric.
	 * @param newPass
	 * @return
	 */
	public boolean validationStage4(final String newPass) {
		String str = newPass.replaceAll(pattern4, "");
		boolean result = (str.length() < newPass.length() / 2) ? true : false;
		return result;
	}
	 /**
	  * Validate New Password for All Criteria
	  * @param newPass
	  * @return
	  */
	public boolean validateNewPassword(final String newPass) {
		boolean stage1 = validationStage1(newPass);
		boolean stage2 = validationStage2(newPass);
		boolean stage3 = validationStage3(newPass);
		boolean stage4 = validationStage4(newPass);
		return validationStepEngine(stage1, stage2, stage3, stage4);
	}

	public boolean validationStepEngine(boolean stage1, boolean stage2, boolean stage3, boolean stage4) {
		return (stage1 && stage2 && stage3 && stage4);
	}

	public boolean validateOldPassword(String oldPass) {
		return oldPass.equals(oldPassword) ? true : false;
	}	
	
	public boolean percentageMatch(String newPass, String oldPass) {
		boolean result = true;
		double distance = StringUtils.getLevenshteinDistance(newPass, oldPass);
		double length = newPass.length();
		if(Math.ceil(length - distance) >= Math.ceil(80*length/100)) {
			result = false;
		}
		return result;
	}
	
}