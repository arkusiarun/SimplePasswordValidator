package com.password.validator;

public interface IValidator {
	public boolean validationStage1(final String newPass);

	public boolean validationStage2(final String newPass);

	public boolean validationStage3(final String newPass);

	public boolean validationStage4(final String newPass);

	public boolean validateNewPassword(final String newPass);

	public boolean validationStepEngine(boolean stage1, boolean stage2, boolean stage3, boolean stage4);

	public boolean validateOldPassword(final String oldPass);

	public boolean percentageMatch(String newPass, String oldPass);
}
