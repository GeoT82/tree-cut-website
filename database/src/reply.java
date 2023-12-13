import java.math.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class reply 
{
	    protected int replyID;
	    protected int docID;
	    protected int clientID;
	    protected String reply;
	    protected Date issueDate;
	    
	    protected SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");  
	 
	    //constructors
	    public reply() {
	    	this(0,0,0,"", new Date());
	    }
	 
	 
	
	    public reply(int replyID, int docID, int clientID, String reply, Date issueDate)
	    {
	    	setReplyID(replyID);
	    	setDocID(docID);
	    	setClientID(clientID);
	    	setReply(reply);
	    	setIssueDate(issueDate);
	    }
	    
	   //getter and setter methods
	    public int getReplyID() {
	        return replyID;
	    }
	    public void setReplyID(int replyID) {
	        this.replyID = replyID;
	    }
	    
	    public int getDocID() {
	        return docID;
	    }
	    public void setDocID(int docID) {
	        this.docID = docID;
	    }
	    
	    public int getClientID() {
	        return clientID;
	    }
	    public void setClientID(int clientID) {
	        this.clientID = clientID;
	    }
	    
	    public String getReply() {
	        return reply;
	    }
	    public void setReply(String reply) {
	        this.reply = reply;
	    }
	    
	    public String getIssueDate() {
	        return formatter.format(issueDate);
	    }
	    public void setIssueDate(Date issueDate) {
	        this.issueDate = issueDate; 
	    }
	    
	    
	    
	    
	   
	}