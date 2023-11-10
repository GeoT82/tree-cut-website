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
@WebServlet("/billDAO")
public class billDAO 
{
	private static final long serialVersionUID = 1L;
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
	public billDAO(){}
	
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
    
    public List<bill> listAllBills() throws SQLException {
        List<bill> listBill = new ArrayList<bill>();        
        String sql = "SELECT * FROM Bill";      
        connect_func();      
        statement = (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        System.out.println("LISTING BILLS");
         
        while (resultSet.next()) {
            String smithNote = resultSet.getString("smithNote");
            String clientNote = resultSet.getString("clientNote");
            int billID = resultSet.getInt("billID");
            int quoteID = resultSet.getInt("quoteID");
            int clientID = resultSet.getInt("clientID");
            double price = resultSet.getDouble("price");

             
            bill bills = new bill(billID, smithNote, clientNote, quoteID, clientID, price);
            listBill.add(bills);
        }        
        resultSet.close();
        disconnect();        
        return listBill;
    }
    
    
    public List<bill> listAllBills(int id) throws SQLException {
        List<bill> listRequest = new ArrayList<bill>();        
        String sql = "SELECT * FROM Bill Where Bill.clientID = " + id;      
        connect_func();      
        statement = (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        System.out.println("LISTING BILLS");
         
        while (resultSet.next()) {
            String smithNote = resultSet.getString("smithNote");
            String clientNote = resultSet.getString("clientNote");
            int billID = resultSet.getInt("billID");
            int quoteID = resultSet.getInt("quoteID");
            int userID = resultSet.getInt("clientID");
            double price = resultSet.getDouble("price");

             
            bill bills = new bill(billID, smithNote, clientNote, quoteID, userID, price);
            listRequest.add(bills);
        }        
        resultSet.close();
        disconnect();        
        return listRequest;
    }
    
    protected void disconnect() throws SQLException {
        if (connect != null && !connect.isClosed()) {
        	connect.close();
        }
    }
    
    public List<bill> listRequests(int id) throws SQLException {
    	List<bill> listBill = new ArrayList<bill>();        
        String sql = "SELECT * FROM Request WHERE Request.requestID = " + id;      
        connect_func();      
        statement = (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        System.out.println("LISTING BILLS");
         
        while (resultSet.next()) {
            String smithNote = resultSet.getString("smithNote");
            String clientNote = resultSet.getString("clientNote");
            int billID = resultSet.getInt("billID");
            int quoteID = resultSet.getInt("quoteID");
            int clientID = resultSet.getInt("clientID");
            double price = resultSet.getDouble("price");

             
            bill bills = new bill(billID, smithNote, clientNote, quoteID, clientID, price);
            listBill.add(bills);
        }        
        resultSet.close();
        disconnect();        
        return listBill;
    }
    
    
    public void generate(bill bill) throws SQLException {
    	System.out.println("GENERATING BILL");
    	connect_func("root","pass1234");         
    	statement =  (Statement) connect.createStatement();
    	statement.execute("SET FOREIGN_KEY_CHECKS = 0;");
    	
		String sql = "insert into Bill(smithNote, clientNote, quoteID, clientID, price) values (?, ?, ?, ?, ?)";
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setString(1, bill.getSmithNote());
		preparedStatement.setString(2, bill.getClientNote());
		preparedStatement.setInt(3, bill.getQuoteID());	
		preparedStatement.setInt(4, bill.getClientID());
		preparedStatement.setDouble(5, bill.getPrice());	

		preparedStatement.executeUpdate();
        preparedStatement.close();
        statement.execute("SET FOREIGN_KEY_CHECKS = 1;");
        disconnect();
        System.out.println("BILL GENERATED");
    }
    
    public boolean delete(String requestID) throws SQLException {
        String sql = "DELETE FROM Request WHERE requestID = ?";        
        connect_func();
         
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, requestID);
         
        boolean rowDeleted = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        return rowDeleted;     
    }
     
    public boolean update(request requests) throws SQLException {
        String sql = "update Request set smithNote=?, clientNote =?, treeCount = ? where requestID = ?";
        connect_func();
        
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setInt(1, requests.getRequestID());
        preparedStatement.setString(2, requests.getSmithNote());
		preparedStatement.setString(3, requests.getClientNote());
         
        boolean rowUpdated = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        return rowUpdated;     
    }
    
   
    
    
    
    public void init() throws SQLException, FileNotFoundException, IOException{
    	connect_func();
        statement =  (Statement) connect.createStatement();
        
        String[] INITIAL = {"use testdb; ",
					        "drop table if exists Bill; ",
					        "SET FOREIGN_KEY_CHECKS = 0;",
					        ("CREATE TABLE if not exists Bill( " +
					        	"billID int not null auto_increment, "+
					        	"clientNote varchar(150) default 'pending', "+ 
					        	"smithNote varchar(150) default 'pending',"+
					        	"price double(10,2) not null default 0," +
					        	"quoteID int not null default 0,"+
					        	"clientID int not null default 0, " +
					        	"PRIMARY KEY (billID)," +
					        	"Foreign key (quoteID) references Quote(quoteID)," +
					        	"Foreign key (clientID) references User(clientID)); "),
					        "SET FOREIGN_KEY_CHECKS = 1;"
        					};
        String[] TUPLES = {"SET FOREIGN_KEY_CHECKS = 0;",
        			"alter table Bill auto_increment = 10;",
        			("INSERT INTO Bill(clientNote, SmithNote, quoteID)"+
        			"values ( 'Sold!', '', 2),"
        			+ "( 'Sold!', '', 4),"
        			+ "( 'Sold!', '',3),"
        			+ "( 'Sold!', '',4),"
        			+ "( 'Sold!', '',1),"
        			+ "( 'Sold!', '',2),"
        			+ "( 'Sold!', '',5),"
        			+ "( 'Sold!', '',6),"
        			+ "( 'Sold!', '',6),"
        			+ "( 'Sold!', '',1);"),
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
