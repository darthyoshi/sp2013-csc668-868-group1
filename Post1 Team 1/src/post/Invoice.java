package POST;

//CSC-868 2/10/13
//Group 1 - Team 1
//Robert Bierman, Steven Gimeno, Ying Kit Ng (Kent), Bianca Uy

import	java.util.Date;
import post.ProductCatalog;

public class Invoice {
	Date invDate = new Date();
	String customerName;
	InvoiceLine[] line = new InvoiceLine[100];
	private float total = 0;
	Payment pay;
	private int invoiceline = 0;

	public Invoice (String cust)
	{
		customerName = cust;
	}
	
	public void setCustomer (String cust)
	{
		customerName = cust;
	}
	
	public String getCustomer ()
	{
		return customerName;
	}
	
	public Date getDate()
	{
		return (invDate);
	}
	
	public String getFormatedDate() {
		String dateString;
		
		dateString = invDate.toString();
		
		return dateString;
	}
	public float getTotal ()
	{
		return total;
	}
	
	public void setPayment (Payment p)
	{
		pay = p;
	}
	
	public Payment getPayment()
	{
		return (pay);
	}
	
	public int addline (ProductCatalog p, String UPC, int quan)
	{
		
		if (p.checkUPC(UPC))
		{
			invoiceline = invoiceline + 1;
		
			line[invoiceline].addline(UPC, quan, p.getItemName(UPC), p.getPrice(UPC));
		
			total = total + (p.getPrice(UPC) * quan);
		
			return invoiceline; //indicates which line number this is
		}
	return 0; //Indicate that this is an invalid UPC code
	}
	
	public InvoiceLine getline (int lineNum)
	{
		return line[lineNum];
	}
	
	public int getNumberOfInvoiceLines ()
	{
		return invoiceline;
	}
	
	public void printInvoice ()
	{
	int i;
	
	System.out.println ("Group 1 Store");
	
	System.out.println("Customer: \t" + this.getCustomer());
	System.out.println("Date: \t" + this.getFormatedDate());
	
	System.out.println ("\rLine #\tItem\t\tQty\tUnit Price\tExtended Price");
	for (i = 0; i < this.getNumberOfInvoiceLines(); i = i + 1)
	{
	System.out.println(i + "\t" + this.getline(i).getDesc() + "\t" + this.getline(i).getQuantity() + "\t" + this.getline(i).getUnitPrice() + "\t" + this.getline(i).getUnitPrice() * this.getline(i).getQuantity());	
	}
	
	System.out.println("\r\r\t\t\tTotal:\t\t" + this.getTotal());
	
	//Need to add payment menthod
	}


}
