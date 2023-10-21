import java.math.*;

public class quote 
{
		protected String time;
	 	protected String smithNote;
	    protected String clientNote;
	    protected double price;
	    protected int quoteID;
	 
	    //constructors
	    public quote() {
	    }
	 
	    public quote(String time, String smithNote, String clientNote, double price) 
	    {
	        setTime(time);
	        setSmithNote(smithNote);
	        setClientNote(clientNote);
	        setPrice(price);
	        setQuoteID( (int) (Math.random() * 100 + 1) );
	    }
	    
	    public quote(String time, String smithNote, String clientNote, double price, int quoteID) 
	    {
	    	setTime(time);
	        setSmithNote(smithNote);
	        setClientNote(clientNote);
	        setPrice(price);
	        setQuoteID(quoteID);
	    }
	 
	    
	  //getter and setter methods
	    public String getTime() {
	        return smithNote;
	    }
	    public void setTime(String time) {
	        this.time = time;
	    }
	    
	    
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
	    
	    
	    public int getQuoteID() {
	        return quoteID;
	    }
	    public void setQuoteID(int quoteID) {
	        this.quoteID = quoteID;
	    }
	  
	  
	    public double getPrice() {
	        return price;
	    }
	    public void setPrice(double price) {
	        this.price = price;
	    }
	   
	}