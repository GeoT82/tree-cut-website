import java.math.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class bill 
{
		protected String smithNote;
		protected String clientNote;
		protected int billID;
		protected int quoteID;
		protected int clientID;
		protected double price;
		
		protected Date issueDate;
		protected Date dueDate;
		protected Date payDate;
		boolean payStatus;
	    
	    protected SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"); 
	 
	    //constructors
	    public bill() {
	    }
	    
	    
	    public bill(String smithNote, String clientNote) 
	    {
	    	setSmithNote(smithNote);
	    	setClientNote(clientNote);
	    	setBillID( (int) (Math.random() * 100 + 1) );
	    	setQuoteID( (int) (Math.random() * 100 + 1) );
	    }
	 
	    
	    public bill(int billID, String smithNote, String clientNote, int quoteID, int clientID, double price, Date issueDate, Date dueDate) 
	    {
	    	setSmithNote(smithNote);
	    	setClientNote(clientNote);
	    	setBillID(billID);
	    	setQuoteID(quoteID);
	    	setClientID(clientID);
	    	setPrice(price);
	    	setIssueDate(issueDate);
	    	setDueDate(dueDate);
	    }
	    
	    
	    public bill(int billID, String smithNote, String clientNote, int quoteID, int clientID, double price, Date issueDate, Date dueDate, Date payDate, boolean payStatus) 
	    {
	    	setSmithNote(smithNote);
	    	setClientNote(clientNote);
	    	setBillID(billID);
	    	setQuoteID(quoteID);
	    	setClientID(clientID);
	    	setPrice(price);
	    	setIssueDate(issueDate);
	    	setDueDate(dueDate);
	    	setPayDate(payDate);
	    	setPayStatus(payStatus);
	    }
	    
	    
	    public bill(int quoteID, int userID, double price, Date issueDate, Date dueDate) 
	    {
	    	setSmithNote("");
	    	setClientNote("");
	    	setBillID(0);
	    	setQuoteID(quoteID);
	    	setClientID(userID);
	    	setPrice(price);
	    	setIssueDate(issueDate);
	    	setDueDate(dueDate);
	    	setPayStatus(false);
	    	
	    }
	    
	    
	   //getter and setter methods
	    public String getSmithNote() {
	        return smithNote;
	    }
	    public void setSmithNote(String smithNote) {
	        this.smithNote = smithNote;
	    }
	    
	    
	    public String getClientNote() {
	        return clientNote;
	    }
	    public void setClientNote(String clientNote) {
	        this.clientNote = clientNote;
	    }
	    
	    
	    public int getBillID() {
	        return billID;
	    }
	    public void setBillID(int billID) {
	        this.billID = billID;
	    }
	    
	    public int getQuoteID() {
	        return quoteID;
	    }
	    public void setQuoteID(int quoteID) {
	        this.quoteID = quoteID;
	    }
	    
	    public int getClientID() {
	        return clientID;
	    }
	    public void setClientID(int clientID) {
	        this.clientID = clientID;
	    }
	    
	    public double getPrice() {
	        return price;
	    }
	    public void setPrice(double price) {
	        this.price = price;
	    }
	    
	    public String getIssueDate() {
	        return formatter.format(issueDate);
	    }
	    public void setIssueDate(Date issueDate) {
	        this.issueDate = issueDate; 
	    }
	    
	    public String getDueDate() {
	        return formatter.format(dueDate);
	    }
	    public void setDueDate(Date dueDate) {
	        this.dueDate = dueDate; 
	    }
	    
	    
	    public String getPayDate() {
	    	if (payDate == null)
	    		return "";
	    	else
	    		return formatter.format(payDate);
	    }
	    public void setPayDate(Date payDate) {
	        this.payDate = payDate; 
	    }
	    
	    public String getPayStatus() {
	        if(payStatus == false) {
	        	return "Unpaid";
	        } else {
	        	return "Paid";
	        }
	    }
	    public void setPayStatus(boolean payStatus) {
	        this.payStatus = payStatus;
	    }
	  
	   
	}