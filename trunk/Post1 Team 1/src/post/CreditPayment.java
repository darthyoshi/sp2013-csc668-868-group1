package POST;

//CSC-868 2/10/13
//Group 1 - Team 1
//Robert Bierman, Steven Gimeno, Ying Kit Ng (Kent), Bianca Uy

public class CreditPayment extends Payment {
	String creditCardNumber;
	
	
	public CreditPayment ()
	{
		paymentType = "Credit";
	}
	
	public CreditPayment (float amount, String ccNum)
	{
		paymentType = "Credit";
		this.setAmount(amount);
		creditCardNumber = ccNum;
	}
	
	public String getCreditCardNumber ()
	{
		return (creditCardNumber);
	}
	
	public void setCreditCardNumber (String ccNum)
	{
		creditCardNumber = ccNum;
	}

	public String getPaymentDetails()
	{
		String s;
		
		s = this.getPaymentType() + ": \t\tAmount: " + this.getAmount() + "\rCredit Card Number: " + this.getCreditCardNumber();
		
		return (s);
	}
	
	public boolean ProcessPayment()
	{
		//call credit card processor
		return (true);
	}
}
