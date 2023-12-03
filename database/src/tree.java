import java.math.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class tree 
{
		protected String image1;
	 	protected String image2;
	    protected String image3;
	    protected String address;
	    protected double distance;
	    protected double width;
	    protected double height;
	    protected int treeID;
	    protected int requestID;
	    protected int clientID;
	    protected boolean cutStatus;
	    protected Date date;
	    
	    protected SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");  
	 
	    //constructors
	    public tree() {
	    }
	    
	    public tree(int treeID, String image1, String image2, String image3, String address, double distance, double width, double height, int requestID, boolean cutStatus, Date date) 
	    {
	    	setImage1(image1);
	    	setImage2(image2);
	    	setImage3(image3);
	    	setAddress(address);
	    	setDistance(distance);
	    	setWidth(width);
	    	setHeight(height);
	    	setTreeID(treeID);
	    	setRequestID(requestID);
	    	setCutStatus(cutStatus);
	    	setDate(date);
	    }
	 
	    
	    public tree(String image1, String image2, String image3, String address, double distance, double width, double height, int requestID, boolean cutStatus) 
	    {
	    	setImage1(image1);
	    	setImage2(image2);
	    	setImage3(image3);
	    	setAddress(address);
	    	setDistance(distance);
	    	setWidth(width);
	    	setHeight(height);
	    	setTreeID(0);
	    	setRequestID(requestID);
	    	setCutStatus(cutStatus);
	    }
	    
	    public tree( int treeID, int requestID, Date date, int clientID)
	    {
	    	
	    	setTreeID(treeID);
	    	setRequestID(requestID);
	    	setDate(date);
	    	setClientID(clientID);
	    }
	 
	 
	    
	   //getter and setter methods
	    public String getImage1() {
	        return image1;
	    }
	    public void setImage1(String image1) {
	        this.image1 = image1;
	    }
	    
	    
	    public String getImage2() {
	        return image2;
	    }
	    public void setImage2(String image2) {
	        this.image2 = image2;
	    }
	    
	    
	    public String getImage3() {
	        return image3;
	    }
	    public void setImage3(String image3) {
	        this.image3 = image3;
	    }
	    
	    
	    public String getAddress() {
	        return address;
	    }
	    public void setAddress(String address) {
	        this.address = address;
	    }
	    
	    
	    public double getDistance() {
	        return distance;
	    }
	    public void setDistance(double distance) {
	        this.distance = distance;
	    }
	    
	    
	    public double getWidth() {
	        return width;
	    }
	    public void setWidth(double width) {
	        this.width = width;
	    }
	    
	    
	    public double getHeight() {
	        return height;
	    }
	    public void setHeight(double height) {
	        this.height = height;
	    }
	    
	    
	    public int getTreeID() {
	        return treeID;
	    }
	    public void setTreeID(int treeID) {
	        this.treeID = treeID;
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
	    
	    public String getCutStatus() {
	        if(cutStatus == false) {
	        	return "Uncut";
	        } else {
	        	return "Cut";
	        }
	    }
	    public void setCutStatus(boolean cutStatus) {
	        this.cutStatus = cutStatus;
	    }
	    
	    public String getDate() {
	    	if (date == null) 
	    		return "";
	    	else 
	    		return formatter.format(date);
	    }
	    public void setDate(Date date) {
	        this.date = date; 
	    }
	   
	}