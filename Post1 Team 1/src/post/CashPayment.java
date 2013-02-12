package POST;

//CSC-868 2/10/13
//Group 1 - Team 1
//Robert Bierman, Steven Gimeno, Ying Kit Ng (Kent), Bianca Uy

public class CashPayment extends Payment {
	public CashPayment ()
	{
		paymentType = "Cash";
	}
	
	public CashPayment (float amount)
	{
		paymentType = "Cash";
		this.setAmount(amount);
	}
	
	public String getPaymentDetails()
	{
		String s;
		
		s = this.getPaymentType() + ": \t\tAmount: " + this.getAmount();
		
		return (s);
	}

}
