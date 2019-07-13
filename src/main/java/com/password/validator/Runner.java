package com.password.validator;
public class Runner {

	static Validator validator = new Validator();

	public boolean changePassword(final String newPass, final String oldPass) {
		boolean result = false;
		if (validator.validateOldPassword(oldPass) && validator.validateNewPassword(newPass)) {
			if (validator.percentageMatch(newPass, oldPass)) {
				result = true;
			}
		}
		return result;
	}

	public static void main(String[] args) {
		String newPass = "n!k@sn1Kos";
		String oldPass = "Arkusi@1";
		Runner r = new Runner();
		System.out.println(r.changePassword(newPass, oldPass));
	}
}
