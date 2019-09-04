package ee.mikkelsaar.bank.dao;

import java.util.List;

import ee.mikkelsaar.bank.object.Account;

public interface UserDAO {
	public void changePassword(String username, String password);
	public void changeRole(String username, String authority);
	public List<Account> getAccounts(String username);
}
