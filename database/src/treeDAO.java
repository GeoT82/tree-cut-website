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

             
            tree trees = new tree(treeID, image1, image2, image3, address, distance, width, height, requestID, cutStatus);
            listTree.add(trees);
        }        
        resultSet.close();
        disconnect();        
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

             
            tree trees = new tree(treeID, image1, image2, image3, address, distance, width, height, requestID, cutStatus);
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
    
    public boolean delete(String email) throws SQLException {
        String sql = "DELETE FROM User WHERE email = ?";        
        connect_func();
         
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, email);
         
        boolean rowDeleted = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        return rowDeleted;     
    }
     
    public boolean update(user users) throws SQLException {
        String sql = "update User set firstName=?, lastName =?,password = ?,creditCard = ?, phoneNumber = ? where email = ?";
        connect_func();
        
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, users.getEmail());
		preparedStatement.setString(2, users.getFirstName());
		preparedStatement.setString(3, users.getLastName());
		preparedStatement.setString(4, users.getPassword());
		preparedStatement.setString(5, users.getCreditCard());
		preparedStatement.setString(6, users.getPhoneNumber());
         
        boolean rowUpdated = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        return rowUpdated;     
    }
    
    public user getUser(String email) throws SQLException {
    	user user = null;
        String sql = "SELECT * FROM User WHERE email = ?";
         
        connect_func();
         
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, email);
         
        ResultSet resultSet = preparedStatement.executeQuery();
         
        if (resultSet.next()) {
            String firstName = resultSet.getString("firstName");
            String lastName = resultSet.getString("lastName");
            String password = resultSet.getString("password");
            String creditCard = resultSet.getString("creditCard");
            String phoneNumber = resultSet.getString("phoneNumber"); 
            int clientID = resultSet.getInt("clientID"); 
            
            user = new user(email, firstName, lastName, password, creditCard, phoneNumber, clientID);
        }
         
        resultSet.close();
        statement.close();
         
        return user;
    }
    
    
    public void updateCutStatus(int tID) throws SQLException {
		String sql = "";
		System.out.println("UPDATE CUT STATUS RUNNIUNG IN TREEDAO");
		
		sql = "update Tree set cutStatus = true where treeID = " + tID + ";";
        
        connect_func();
        statement =  (Statement) connect.createStatement();
        
        statement.execute(sql);   
        
        System.out.println("UPDATE CUT STATUS TERMINATED IN TREEDAO");
    }
    
    
    public void init() throws SQLException, FileNotFoundException, IOException{
    	connect_func();
        statement =  (Statement) connect.createStatement();
        
        String[] INITIAL = {"use testdb; ",
					        "drop table if exists Tree; ",
					        "SET FOREIGN_KEY_CHECKS = 0;",
					        ("CREATE TABLE if not exists Tree( " +
					        		"treeID int not null auto_increment,"+ 
					        		"requestID int not null default 0,"+
					        		"distance double (12,2) not null default '0', "+ 
					        		"width double (12,2) not null default '0',"+
					        		"height double (12,2) not null default '0', "+
					        		"address varchar(30) not null default 'unknown', " +
					        		"image1 varchar(30) not null default 'blank.png',  "+
					        		"image2 varchar(30) not null default 'blank.png',  "+
					        		"image3 varchar(30) not null default 'blank.png',"+
					        		"PRIMARY KEY (treeID),"+
					        		"FOREIGN KEY (requestID) REFERENCES Request(requestID)"+"); "),
					        "SET FOREIGN_KEY_CHECKS = 1;"
        					};
        String[] TUPLES = {"alter table Tree auto_increment = 500;",
        			"SET FOREIGN_KEY_CHECKS = 0;",
        			("INSERT INTO Tree(distance, width, height, address, image1, image2, image3, requestID)"+
        			"values ( 5, 123, 123, 'Detroit', 'a', 'b', 'c',200),"
        			+ "( 10, 232, 123, 'Detroit', 'a', 'd', 'e',200),"
        			+ "( 15, 121, 123, 'Detroit', 'h', 'g', 'f',201),"
        			+ "( 20, 180, 123, 'Detroit', 'i', 'j', 'k',201),"
        			+ "( 25, 280, 123, 'Detroit', 'n', 'm', 'l',204),"
        			+ "( 30, 321, 123, 'Detroit', 'o', 'p', 'q',204),"
        			+ "( 35, 213, 123, 'Detroit', 't', 's', 'r',210),"
        			+ "( 40, 91, 123, 'Detroit', 'u', 'b', 't',210),"
        			+ "( 50, 32, 15, 'Detroit', 'f', 'r', 'i',211),"
        			+ "( 45, 145, 123, 'Detroit', 'v','y', 's',210);"),
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
