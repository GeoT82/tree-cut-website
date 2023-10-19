import java.math.*;

public class bill 
{
		protected String smithNote;
		protected String clientNote;
		protected int billID;
	 
	    //constructors
	    public bill() {
			
	    }
	    
	    
	    public bill(String smithNote, String clientNote) 
	    {
	    	setSmithNote(smithNote);
	    	setClientNote(clientNote);
	    	setBillID( (int) (Math.random() * 100 + 1) );
	    }
	 
	    
	    public bill(String smithNote, String clientNote, int billID) 
	    {
	    	setSmithNote(smithNote);
	    	setClientNote(clientNote);
	    	setBillID(billID);
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
	    
	    
	    public int getbillID() {
	        return billID;
	    }
	    public void setBillID(int billID) {
	        this.billID = billID;
	    }
	  
	   
	}