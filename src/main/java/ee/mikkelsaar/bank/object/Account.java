package ee.mikkelsaar.bank.object;

public class Account {
	private int nr;
	private float balance;
	
	public void setNr(int nr){
		this.nr = nr;
	}
	
	public void setBalance(float balance){
		this.balance = balance;
	}
	
	public int getNr(){
		return nr;
	}
	
	public float getBalance(){
		return balance;
	}
	
	
}
