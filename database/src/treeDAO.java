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

             
            tree trees = new tree(image1, image2, image3, address, distance, width, height, treeID, requestID);
            listTree.add(trees);
        }        
        resultSet.close();
        disconnect();        
        return listTree;
    }
    
    public List<tree> listTrees(int id) throws SQLException {
        List<tree> listTree = new ArrayList<tree>();        
        String sql = "SELECT * FROM Tree Where Tree.requestID = 'id'";      
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

             
            tree trees = new tree(image1, image2, image3, address, distance, width, height, treeID, requestID);
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
    
    public void insert(user users) throws SQLException {
    	connect_func("root","pass1234");         
		String sql = "insert into User(email, firstName, lastName, password, creditCard, phoneNumber, clientID) values (?, ?, ?, ?, ?, ?, ?)";
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
			preparedStatement.setString(1, users.getEmail());
			preparedStatement.setString(2, users.getFirstName());
			preparedStatement.setString(3, users.getLastName());
			preparedStatement.setString(4, users.getPassword());
			preparedStatement.setString(5, users.getCreditCard());
			preparedStatement.setString(6, users.getPhoneNumber());			
			preparedStatement.setInt(7, users.getClientID());	

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
    
    
    public void init() throws SQLException, FileNotFoundException, IOException{
    	connect_func();
        statement =  (Statement) connect.createStatement();
        
        String[] INITIAL = {"drop database if exists testdb; ",
					        "create database testdb; ",
					        "use testdb; ",
					        "drop table if exists User; ",
					        ("CREATE TABLE if not exists User( " +
					            "email VARCHAR(50) NOT NULL, " + 
					            "firstName VARCHAR(10) NOT NULL, " +
					            "lastName VARCHAR(10) NOT NULL, " +
					            "password VARCHAR(20) NOT NULL, " +
					            "creditCard CHAR(16) NOT NULL," +
					            "phoneNumber CHAR(10) NOT NULL," +
					            "clientID INT NOT NULL," +
					            "PRIMARY KEY (email) "+"); ")
        					};
        String[] TUPLES = {("insert into User(email, firstName, lastName, password, creditCard, phoneNumber, clientID)"+
        			"values ('susie@gmail.com', 'Susie ', 'Guzman', 'susie1234', '12345678', '1233216547', '3333'),"+
        			"('davidSmith@gmail.com', 'David', 'Smith', 'ds1234', '23452342342', '2142552', '1111')," +
			    			"('root', 'default', 'default','pass1234', '87654321', '0000000000', '0000');")
			    			};
        
        //for loop to put these in database
        for (int i = 0; i < INITIAL.length; i++)
        	statement.execute(INITIAL[i]);
        for (int i = 0; i < TUPLES.length; i++)	
        	statement.execute(TUPLES[i]);
        disconnect();
    }
    
    
   
    
    
    
    
    
	
	

}
