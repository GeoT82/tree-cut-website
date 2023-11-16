import java.math.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class quote 
{
		protected String time;
	 	protected String smithNote;
	    protected String clientNote;
	    protected double price;
	    protected int quoteID;
	    protected int requestID;
	    protected int clientID;
	    protected Date date;
	    
	    protected SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"); 
	 
	    //constructors
	    public quote() {
	    }
	 
	    public quote(double price, String time, String smithNote, int requestID, int clientID, Date Date) 
	    {
	        setTime(time);
	        setSmithNote(smithNote);
	        setPrice(price);
	        setRequestID(requestID);
	        setClientID(clientID);
	        setDate(date);
	    }
	    
	    public quote(String time, String smithNote, String clientNote, double price, int quoteID, int requestID, int clientID, Date date) 
	    {
	    	setTime(time);
	        setSmithNote(smithNote);
	        setClientNote(clientNote);
	        setPrice(price);
	        setQuoteID(quoteID);
	        setRequestID(requestID);
	        setClientID(clientID);
	        setDate(date);
	    }
	 
	    
	  //getter and setter methods
	    public String getTime() {
	        return time;
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
	    
	    
	    public int getRequestID() {
	        return requestID;
	    }
	    public void setRequestID(int requestID) {
	        this.requestID = requestID;
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
	    
	    public String getDate() {
	        return formatter.format(date);
	    }
	    public void setDate(Date date) {
	        this.date = date; 
	    }
	   
	}