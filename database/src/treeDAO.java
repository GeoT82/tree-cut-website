import java.io.FileNotFoundException;

import java.lang.System;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 * Servlet implementation class Connect
 */
@WebServlet("/treeDAO")
public class treeDAO 
{
	private static final long serialVersionUID = 1L;
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
	public treeDAO(){}
	
	/** 
	 * @see HttpServlet#HttpServlet()
     */
    protected void connect_func() throws SQLException {
    	//uses default connection to the database
        if (connect == null || connect.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            connect = (Connection) DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/testdb?allowPublicKeyRetrieval=true&useSSL=false&user=root&password=pass1234");
            System.out.println(connect);
        }
    }
    
    public boolean database_login(String email, String password) throws SQLException{
    	try {
    		connect_func("root","pass1234");
    		String sql = "select * from user where email = ?";
    		preparedStatement = connect.prepareStatement(sql);
    		preparedStatement.setString(1, email);
    		ResultSet rs = preparedStatement.executeQuery();
    		return rs.next();
    	}
    	catch(SQLException e) {
    		System.out.println("failed login");
    		return false;
    	}
    }
	//connect to the database 
    public void connect_func(String username, String password) throws SQLException {
        if (connect == null || connect.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            connect = (Connection) DriverManager
  			      .getConnection("jdbc:mysql://127.0.0.1:3306/testdb?allowPublicKeyRetrieval=true&"
  			          + "useSSL=false&user=" + username + "&password=" + password);
            System.out.println(connect);
        }
    }
    
    public List<tree> listAllTree() throws SQLException {
        List<tree> listTree = new ArrayList<tree>();        
        String sql = "SELECT * FROM Tree";      
        connect_func();      
        statement = (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        System.out.println("LISTING TREES");
         
        while (resultSet.next()) {
            String image1 = resultSet.getString("image1");
            String image2 = resultSet.getString("image2");
            String image3 = resultSet.getString("image3");
            String address = resultSet.getString("address");
            double distance = resultSet.getDouble("distance");
            double width = resultSet.getDouble("width");
            double height = resultSet.getDouble("height"); 
            int treeID = resultSet.getInt("treeID"); 
            int requestID = resultSet.getInt("requestID"); 
            boolean cutStatus = resultSet.getBoolean("cutStatus");
            Date date = resultSet.getTimestamp("cutDate");

             
            tree trees = new tree(treeID, image1, image2, image3, address, distance, width, height, requestID, cutStatus, date);
            listTree.add(trees);
        }        
        resultSet.close();
        disconnect();        
        return listTree;
    }
    
    
    public List<tree> getTallestTree() throws SQLException {
    	System.out.println("GET TALLEST TREE RUNNING");
        List<tree> listTree = new ArrayList<tree>();        
        String sql = "select treeID, height from Tree where height = (select max(height) from Tree);";      
        connect_func();      
        statement = (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        System.out.println("LISTING TREES");
         
        while (resultSet.next()) {
            int treeID = resultSet.getInt("treeID"); 
            int height = resultSet.getInt("height"); 

             
            tree trees = new tree(treeID, "", "", "", "", 0, 0, height, 0, false, null);
            listTree.add(trees);
        }        
        resultSet.close();
        disconnect();       
        
        System.out.println("GET TALLEST TREE TERMINATED");
        return listTree;
    }
    
    
    public List<tree> getTreeDetails() throws SQLException {
    	System.out.println("GET TREE DETAILS RUNNING");
        List<tree> listTree = new ArrayList<tree>();        
        String sql = "select treeID, T.requestID, cutDate, R.clientID from Tree T, Request R where cutStatus = true and T.requestID = R.requestID;";      
        connect_func();      
        statement = (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        System.out.println("LISTING TREES");
         
        while (resultSet.next()) {
            int treeID = resultSet.getInt("treeID"); 
            int requestID = resultSet.getInt("T.requestID");
            Date date = resultSet.getTimestamp("cutDate");
            int clientID = resultSet.getInt("R.clientID");

             
            tree trees = new tree(treeID, requestID, date, clientID);
            listTree.add(trees);
        }        
        resultSet.close();
        disconnect();       
        
        System.out.println("GET TREE DETAILS TERMINATED");
        return listTree;
    }
    
    
    public List<tree> listTrees(int rID) throws SQLException {
        List<tree> listTree = new ArrayList<tree>();        
        String sql = "SELECT * FROM Tree Where Tree.requestID = " + rID;      
        connect_func();      
        statement = (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        System.out.println("LISTING TREES");
         
        while (resultSet.next()) {
            String image1 = resultSet.getString("image1");
            String image2 = resultSet.getString("image2");
            String image3 = resultSet.getString("image3");
            String address = resultSet.getString("address");
            double distance = resultSet.getDouble("distance");
            double width = resultSet.getDouble("width");
            double height = resultSet.getDouble("height"); 
            int treeID = resultSet.getInt("treeID"); 
            int requestID = resultSet.getInt("requestID"); 
            boolean cutStatus = resultSet.getBoolean("cutStatus");
            Date date = resultSet.getTimestamp("cutDate");

             
            tree trees = new tree(treeID, image1, image2, image3, address, distance, width, height, requestID, cutStatus, date);
            listTree.add(trees);
        }        
        resultSet.close();
        disconnect();        
        return listTree;
    }
    
    
    protected void disconnect() throws SQLException {
        if (connect != null && !connect.isClosed()) {
        	connect.close();
        }
    }
    
    public void insert(tree tree) throws SQLException {
    	connect_func("root","pass1234");         
		String sql = "insert into Tree(image1, image2, image3, address, distance, width, height, requestID) values (?, ?, ?, ?, ?, ?, ?, ?)";
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
			preparedStatement.setString(1, tree.getImage1());
			preparedStatement.setString(2, tree.getImage2());
			preparedStatement.setString(3, tree.getImage3());
			preparedStatement.setString(4, tree.getAddress());
			preparedStatement.setDouble(5, tree.getDistance());
			preparedStatement.setDouble(6, tree.getWidth());			
			preparedStatement.setDouble(7, tree.getHeight());	
			preparedStatement.setInt(8, tree.getRequestID());	

		preparedStatement.executeUpdate();
        preparedStatement.close();
    }
    
    
    
    
    public void updateCutStatus(int tID, String cDate) throws SQLException {
		
		System.out.println("UPDATE CUT STATUS RUNNIUNG IN TREEDAO");
		
		connect_func();
        statement =  (Statement) connect.createStatement();
        
        String sql = "";
		System.out.println("TREE ID: " + tID);
		System.out.println("CUT DATE: " + cDate);
		
		sql = "update Tree set cutStatus = true where treeID = " + tID + ";";
        
        statement.execute(sql);   
        
        sql = "update Tree set cutDate = '" + cDate + "' where treeID = " + tID + ";";
        
        System.out.println(sql);
        
        statement.execute(sql);   
        
        disconnect();
        
        System.out.println("UPDATE CUT STATUS TERMINATED IN TREEDAO");
    }
    
    
    public void init() throws SQLException, FileNotFoundException, IOException{
    	connect_func();
        statement =  (Statement) connect.createStatement();
        
        String[] INITIAL = {"use testdb; ",
					        "drop table if exists Tree; ",
					        "SET FOREIGN_KEY_CHECKS = 0;",
					        ("Create TABLE if not exists Tree(\n"
					        		+ "  treeID int not null auto_increment, \n"
					        		+ "  requestID int not null default 0,\n"
					        		+ "  distance double (12,2) not null default '0', \n"
					        		+ "  width double (12,2) not null default '0',\n"
					        		+ "  height double (12,2) not null default '0', \n"
					        		+ "  address varchar(30) not null default 'unknown', \n"
					        		+ "  image1 varchar(30) not null default 'blank.png', \n"
					        		+ "  image2 varchar(30) not null default 'blank.png', \n"
					        		+ "  image3 varchar(30) not null default 'blank.png',\n"
					        		+ "  cutStatus boolean not null default false,\n"
					        		+ "  cutDate datetime default null, \n"
					        		+ "  PRIMARY KEY (treeID),\n"
					        		+ "  FOREIGN KEY (requestID) REFERENCES Request(requestID));")
        					};
        String[] TUPLES = {"alter table Tree auto_increment = 500;",
        			("INSERT INTO Tree(distance, width, height, address, image1, image2, image3, requestID, cutDate, cutStatus)\n"
        					+ "VALUES \n"
        					+ "( 5, 123, 234, 'Detroit', 'a', 'b', 'c',200, '2022-11-19 7:24:40', true),\n"
        					+ "( 10, 232, 52, 'Detroit', 'a', 'd', 'e',200, '2022-05-19 7:24:40', true),\n"
        					+ "( 15, 121, 2356, 'Detroit', 'h', 'g', 'f',201, '2022-09-19 7:24:40', true),\n"
        					+ "( 20, 180, 678, 'Detroit', 'i', 'j', 'k',202, default, default),\n"
        					+ "( 25, 280, 346, 'Detroit', 'n', 'm', 'l',203, '2022-04-19 7:24:40', true),\n"
        					+ "( 30, 321, 7456, 'Detroit', 'o', 'p', 'q',204, '2022-08-19 7:24:40', true),\n"
        					+ "( 35, 213, 345, 'Detroit', 't', 's', 'r',209,  default, default),\n"
        					+ "( 40, 91, 537, 'Detroit', 'u', 'b', 't',210, '2022-10-19 7:24:40', true),\n"
        					+ "( 50, 32, 964, 'Detroit', 'f', 'r', 'i',211, default, default),\n"
        					+ "( 45, 145, 457, 'Detroit', 'v','y', 's',210, default, default);"),
        			"SET FOREIGN_KEY_CHECKS = 1;"
			    			};
        
        //for loop to put these in database
        for (int i = 0; i < INITIAL.length; i++)
        	statement.execute(INITIAL[i]);
        for (int i = 0; i < TUPLES.length; i++)	
        	statement.execute(TUPLES[i]);
        disconnect();
    }
    
    
   
    
    
    
    
    
	
	

}
