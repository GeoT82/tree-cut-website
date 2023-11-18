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
@WebServlet("/quoteDAO")
public class quoteDAO 
{
	private static final long serialVersionUID = 1L;
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
	public quoteDAO(){}
	
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
    
    public List<quote> listAllQuotes() throws SQLException {
        List<quote> listQuote = new ArrayList<quote>();        
        String sql = "SELECT * FROM Quote";      
        connect_func();      
        statement = (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        System.out.println("LISTING");
         
        while (resultSet.next()) {
            String time = resultSet.getString("times");
            String smithNote = resultSet.getString("smithNote");
            String clientNote = resultSet.getString("clientNote");
            double price = resultSet.getDouble("price");
            int quoteID = resultSet.getInt("quoteID"); 
            int requestID = resultSet.getInt("requestID"); 
            int clientID = resultSet.getInt("clientID");
            Date date = resultSet.getTimestamp("issueDate");

             
            quote quotes = new quote(time, smithNote, clientNote, price, quoteID, requestID,clientID, date);
            listQuote.add(quotes);
        }        
        resultSet.close();
        disconnect();        
        return listQuote;
    }
    
    public List<quote> listAllQuotes(int uID) throws SQLException {
        List<quote> listQuote = new ArrayList<quote>();        
        System.out.println(uID);
        String sql = "SELECT * FROM Quote where clientID = " + uID;      
        connect_func(); 
        System.out.println(sql);
        statement = (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        System.out.println("LISTING");
         
        while (resultSet.next()) {
            String time = resultSet.getString("times");
            String smithNote = resultSet.getString("smithNote");
            String clientNote = resultSet.getString("clientNote");
            double price = resultSet.getDouble("price");
            int quoteID = resultSet.getInt("quoteID"); 
            int requestID = resultSet.getInt("requestID"); 
            int clientID = resultSet.getInt("clientID");
            Date date = resultSet.getTimestamp("issueDate");

             
            quote quotes = new quote(time, smithNote, clientNote, price, quoteID, requestID,clientID, date);
            listQuote.add(quotes);
        }        
        resultSet.close();
        disconnect();        
        return listQuote;
    }
    
    public List<quote> listQuote(int qID) throws SQLException {
        List<quote> listQuote = new ArrayList<quote>();        
        System.out.println(qID);
        String sql = "SELECT * FROM Quote where quoteID = " + qID;      
        connect_func(); 
        System.out.println(sql);
        statement = (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        System.out.println("LISTING");
         
        while (resultSet.next()) {
            String time = resultSet.getString("times");
            String smithNote = resultSet.getString("smithNote");
            String clientNote = resultSet.getString("clientNote");
            double price = resultSet.getDouble("price");
            int quoteID = resultSet.getInt("quoteID"); 
            int requestID = resultSet.getInt("requestID"); 
            int clientID = resultSet.getInt("clientID");
            Date date = resultSet.getTimestamp("issueDate");

             
            quote quotes = new quote(time, smithNote, clientNote, price, quoteID, requestID,clientID, date);
            listQuote.add(quotes);
        }        
        resultSet.close();
        disconnect();        
        return listQuote;
    }
    
    public int getQuoteID(int rID) throws SQLException {     
    	int quoteID = 0;
        String sql = "SELECT * FROM Quote where quote.requestID = " + rID;      
        connect_func();      
        statement = (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        System.out.println("getting Quote ID");
         
        while (resultSet.next()) {
            quoteID = resultSet.getInt("quoteID"); 
        }        
        resultSet.close();
        disconnect();        
        return quoteID;
    }
    
    protected void disconnect() throws SQLException {
        if (connect != null && !connect.isClosed()) {
        	connect.close();
        }
    }
    
    public void insert(quote quotes, String date) throws SQLException {
    	connect_func("root","pass1234");         
    	
    	System.out.println("INSERT RUNNIUNG");
    	statement =  (Statement) connect.createStatement();
    	statement.execute("SET FOREIGN_KEY_CHECKS = 0;");
    	System.out.println("CHECKS OFF");
    	
		String sql = "insert into Quote(times, smithNote, price, requestID, clientID, issueDate) values (?, ?, ?, ?, ?, ?)";
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
			preparedStatement.setString(1, quotes.getTime());
			preparedStatement.setString(2, quotes.getSmithNote());
			preparedStatement.setDouble(3, quotes.getPrice());
			preparedStatement.setInt(4, quotes.getRequestID());
			preparedStatement.setInt(5, quotes.getClientID());
			preparedStatement.setString(6, date);

		preparedStatement.executeUpdate();
        preparedStatement.close();
        
        statement.execute("SET FOREIGN_KEY_CHECKS = 1;");
        System.out.println("CHECKS ON");
        System.out.println("INSERT DONE");
    }
    
    public boolean delete(int quoteID) throws SQLException {
        String sql = "DELETE FROM Quote WHERE quoteID = ?";        
        connect_func();
        statement =  (Statement) connect.createStatement();
        statement.execute("SET FOREIGN_KEY_CHECKS = 0;");
        
         
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setInt(1, quoteID);
         
        boolean rowDeleted = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        statement.execute("SET FOREIGN_KEY_CHECKS = 1;");
        disconnect();
        
        return rowDeleted;     
    }
    
     
    public boolean update(quote quotes) throws SQLException {
        String sql = "update Quote set time=?, smithNote =?, clientNote = ?, price = ? where quoteID = ?";
        connect_func();
        
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setInt(1, quotes.getQuoteID());
        preparedStatement.setString(2, quotes.getTime());
        preparedStatement.setString(3, quotes.getSmithNote());
		preparedStatement.setString(4, quotes.getClientNote());
		preparedStatement.setDouble(5, quotes.getPrice());
         
        boolean rowUpdated = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        return rowUpdated;     
    }
    
    public void update(int qID, String note, String user) throws SQLException {
		String sql = "";
		System.out.println("UPDATE POST RUNNIUNG");
		if(user.matches("davidSmith@gmail.com")) {
			sql = "update Quote set smithNote = '" + note + "' where quoteID = " + qID + ";";
		} else {
			sql = "update Quote set clientNote = '" + note + "' where quoteID = " + qID + ";";
		}
        
        connect_func();
        statement =  (Statement) connect.createStatement();
        
        statement.execute(sql); 
        disconnect();
    }
    
    public quote getQuote(int quoteID) throws SQLException {
    	quote quote = null;
        String sql = "SELECT * FROM User WHERE email = ?";
         
        connect_func();
         
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setInt(1, quoteID);
         
        ResultSet resultSet = preparedStatement.executeQuery();
         
        if (resultSet.next()) {
            String time = resultSet.getString("time");
            String smithNote = resultSet.getString("smithNote");
            String clientNote = resultSet.getString("clientNote");
            double price = resultSet.getDouble("price");
            int requestID = resultSet.getInt("requestID");
            int clientID = resultSet.getInt("clientID");
            Date date = resultSet.getTimestamp("issueDate");
            
            quote = new quote(time, smithNote, clientNote, price, quoteID, requestID, clientID, date);
        }
         
        resultSet.close();
        statement.close();
         
        return quote;
    }
    
    
    public int getUserID(int qID) throws SQLException {
    	int clientID = 0;
        String sql = "SELECT * FROM Quote WHERE quoteID = ?";
         
        connect_func();
         
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setInt(1, qID);
         
        ResultSet resultSet = preparedStatement.executeQuery();
         
        if (resultSet.next()) {
            clientID = resultSet.getInt("clientID"); 
        }
         
        resultSet.close();
        statement.close();
         
        return clientID;
    }
    
    
    public int getRequestID(int qID) throws SQLException {
    	int requestID = 0;
        String sql = "SELECT * FROM Quote WHERE quoteID = ?";
         
        connect_func();
         
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setInt(1, qID);
         
        ResultSet resultSet = preparedStatement.executeQuery();
         
        if (resultSet.next()) {
        	requestID = resultSet.getInt("requestID"); 
        }
         
        resultSet.close();
        statement.close();
         
        return requestID;
    }
    
    public double getPrice(int qID) throws SQLException {
    	double price = 0;
        String sql = "SELECT * FROM Quote WHERE quoteID = ?";
         
        connect_func();
         
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setInt(1, qID);
         
        ResultSet resultSet = preparedStatement.executeQuery();
         
        if (resultSet.next()) {
        	price = resultSet.getDouble("price"); 
        }
         
        resultSet.close();
        statement.close();
         
        return price;
    }
    
    
    public void init() throws SQLException, FileNotFoundException, IOException{
    	connect_func();
        statement =  (Statement) connect.createStatement();
        
        String[] INITIAL = {"use testdb",
        					"drop table if exists Quote;",
        					"SET FOREIGN_KEY_CHECKS = 0;",
					        ("CREATE TABLE if not exists Quote( " +
					            "quoteID int not null auto_increment, "+
					            "clientNote varchar(150) default 'pending', "+ 
					            "smithNote varchar(150) default 'pending', "+ 
					            "times time not null default '1:00:00',"+ 
					            "price double not null default '0.00',"+
					            "requestID int not null default '0',"+
					            "clientID int not null default '0', " +
					            "PRIMARY KEY (QuoteID),"+
					            "Foreign key (requestID) references Request(requestID)," +
					            "foreign key (clientID) references User(clientID));"),
					        "SET FOREIGN_KEY_CHECKS = 1;"
        					};
        String[] TUPLES = {"SET FOREIGN_KEY_CHECKS = 0;",
        					"alter table Quote auto_increment = 20;",
        					("INSERT INTO Quote( clientNote , smithNote, times, price, requestID, clientID)"+
        					"VALUES ( 'Sold!', '', '1:00:00', 4500, 200, 111),"+
        					"( 'Sold!', '', '2:00:00', 5500, 201, 111),"+
        					"( 'Sold!', '', '3:00:00', 6500, 204, 111),"+
        					"( 'Sold!', '', '4:00:00', 7500, 4, 109),"+
        					"( 'Sold!', '', '5:00:00', 8500, 5, 110),"+
        					"( 'Sold!', '', '6:00:00', 9500, 6, 100),"+
        					"( 'Sold!', '', '7:00:00', 10500, 7, 103),"+
        					"( 'Sold!', '', '8:00:00', 11500, 8, 105),"+
        					"( 'Sold!', '', '9:00:00', 12500, 9, 104),"+
        					"( 'Sold!', '', '10:00:00', 13500, 210, 111);"),
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
