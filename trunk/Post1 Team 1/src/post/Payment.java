package POST;

//CSC-868 2/10/13
//Group 1 - Team 1
//Robert Bierman, Steven Gimeno, Ying Kit Ng (Kent), Bianca Uy

public abstract class Payment {

	float amount;
	String paymentType;
	
	public Payment()
	{
		
	}
	
	public String getPaymentType()
	{
		return (paymentType);
	}
	
	public float getAmount ()
	{
		return (amount);
	}
	
	public void setAmount (float amt)
	{
		amount = amt;
	}
	
	public boolean processPayment()
	{
		return (true);
	}
	
	public String getPaymentDetails()
	{
		return null;
	}
}
