package ee.mikkelsaar.bank.object;

public class Password {
	String password;
	String confirmPassword;

	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	
	public String getPassword() {
		return password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}
}
