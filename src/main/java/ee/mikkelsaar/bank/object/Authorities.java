package ee.mikkelsaar.bank.object;

public class Authorities {
	
	public static final String ROLE_USER = "ROLE_USER";
	public static final String ROLE_ADMIN = "ROLE_ADMIN";
	public static final String ROLE_NEWUSER = "ROLE_NEWUSER";
	
	private Authorities(){
		throw new AssertionError();
	}
}
