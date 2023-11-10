import java.math.*;

public class request extends tree
{
		protected tree[] treeArr;
	 	protected String smithNote;
	    protected String clientNote;
	    protected int requestID;
	    protected int quoteID;
	    protected int clientID;
	 
	    //constructors
	    public request() {
	    }
	   
	 
	    public request(String smithNote, String clientNote,  int quoteID) 
	    {
	        setSmithNote(smithNote);
	        setClientNote(clientNote);
	        setQuoteID(quoteID);
	        setRequestID( (int) (Math.random() * 100 + 1) );
	    }
	    
	    public request(String smithNote, String clientNote, int requestID, int quoteID, int clientID) 
	    {
	        setSmithNote(smithNote);
	        setClientNote(clientNote);
	        setRequestID(requestID);
	        setQuoteID(quoteID);
	        setClientID(clientID);
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
	    
	    
	    public int getRequestID() {
	        return requestID;
	    }
	    public void setRequestID(int requestID) {
	        this.requestID = requestID;
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
	   
	}