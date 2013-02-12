package POST;

// CSC-868 2/10/13
// Group 1 - Team 1
// Robert Bierman, Steven Gimeno, Ying Kit Ng (Kent), Bianca Uy

// We store the description and unit price with the invoice so that we do not need the product catalog to print
// and reconcile an invoice.  This allows the price (or description) to change in the catalog without effecting a prior invoice.
public class InvoiceLine {
	String UPC;
	int quantity;
	String description;
	float unitPrice;
	
	
	public void addline (String inUPC, int quan, String desc, float price)
	{
		UPC = inUPC;
		description = desc;
		unitPrice = price;
		quantity = quan;
	}
	
	public String getUPC ()
	{
		return (UPC);
	}
	
	public int getQuantity()
	{
		return (quantity);
	}
	
	public String getDesc()
	{
		return (description);
	}
	
	public float getUnitPrice()
	{
		return (unitPrice);
	}
}
