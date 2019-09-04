package ee.mikkelsaar.bank.dao;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.authentication.encoding.MessageDigestPasswordEncoder;

import ee.mikkelsaar.bank.object.Account;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class JdbcUserDAO implements UserDAO{
	
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	private List<Account> accounts;
	
	@Resource(name="dataSource")
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		setJdbcTemplateDataSource();
	}

	@Autowired
    transient private MessageDigestPasswordEncoder passwordEncoder;
	
	@Override
	public void changePassword(String username, String password) {
		password = passwordEncoder.encodePassword(password, null);
		
		String sql = "UPDATE users SET password=? WHERE username=?";
		jdbcTemplate.update(sql, new Object[] {password, username});
	}

	@Override
	public void changeRole(String username, String authority) {
		String sql = "UPDATE authorities SET authority=? WHERE username=?";
		jdbcTemplate.update(sql, new Object[] {authority, username});
	}

	@Override
	public List<Account> getAccounts(String username) {
		String sql = "SELECT nr, balance FROM accounts WHERE username=?";
		accounts = jdbcTemplate.query(sql, new Object[] {username}, new AccountMapper());
		
		return accounts;
	}
	

	
	
	private void setJdbcTemplateDataSource() {
		jdbcTemplate = new JdbcTemplate(dataSource);	
	}
	
	private static final class AccountMapper implements RowMapper<Account>
	{
	    @Override
	    public Account mapRow(ResultSet resultset, int rowNum) throws SQLException {
	    	Account a=new Account();
	        a.setNr(resultset.getInt("nr"));
	        a.setBalance(resultset.getFloat("balance"));
	        return a;
	    }
	}

}
