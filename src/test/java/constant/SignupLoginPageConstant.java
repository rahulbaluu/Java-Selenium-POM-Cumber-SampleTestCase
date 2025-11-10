package constant;

public class SignupLoginPageConstant {
	public static String signupUserName = "input[type='text']";
	public static String signupEmail = "input[data-qa='signup-email']";
	public static String signupButton = "button[data-qa='signup-button']";
	public static String loginEmail = "input[data-qa='login-email']";
	public static String loginPassword = "input[data-qa='login-password']";
	public static String loginErrorMessageElement = "//p[text()='Your email or password is incorrect!']";
	public static String existingLoginEmailErrorMessageElement = "//p[text()='Email Address already exist!']";
	public static String loginButton = "button[data-qa='login-button']";
}
