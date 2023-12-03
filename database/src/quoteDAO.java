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
            int billID = resultSet.getInt("billID");
            Date date = resultSet.getTimestamp("issueDate");

             
            quote quotes = new quote(time, smithNote, clientNote, price, quoteID, requestID,clientID, billID, date);
            listQuote.add(quotes);
        }        
        resultSet.close();
        disconnect();        
        return listQuote;
    }
    
    
    public List<quote> getEasyClient() throws SQLException {
    	
    	System.out.println("GET EASY CLIENT RUNNING");
        List<quote> listQuote = new ArrayList<quote>();        
        String sql = "select clientID from Quote where billID != 0 and clientNote = 'pending';";      
        connect_func();      
        statement = (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        System.out.println("LISTING");
         
        while (resultSet.next()) { 
            int clientID = resultSet.getInt("clientID");

             
            quote quotes = new quote(null, "", "", 0, 0, 0,clientID, 0, null);
            listQuote.add(quotes);
        }        
        resultSet.close();
        disconnect();     
        System.out.println("GET EASY CLIENT TERMINATED");
        return listQuote;
    }
    
    public List<quote> getOneTreeQuotes() throws SQLException {
    	System.out.println("GET ONE TREE QUOTES RUNNING");
        List<quote> listQuote = new ArrayList<quote>();        
        String sql = "select quoteID, requestID from Quote where billID != 0 and quoteID in ( select quoteID from Request	where treeCount = 1 );";      
        connect_func();      
        statement = (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        System.out.println("LISTING");
         
        while (resultSet.next()) { 
            int quoteID = resultSet.getInt("quoteID");
            int requestID = resultSet.getInt("requestID");
            System.out.println("QuoteID: " + quoteID + " Request ID: " + requestID);

             
            quote quotes = new quote(null, "", "", 0, quoteID, requestID, 0, 0, null);
            listQuote.add(quotes);
        }        
        resultSet.close();
        disconnect();      
        System.out.println("GET ONE TREE QUOTES TERMINATED");
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
            int billID = resultSet.getInt("billID");
            Date date = resultSet.getTimestamp("issueDate");

             
            quote quotes = new quote(time, smithNote, clientNote, price, quoteID, requestID,clientID, billID, date);
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
            int billID = resultSet.getInt("billID");
            Date date = resultSet.getTimestamp("issueDate");

             
            quote quotes = new quote(time, smithNote, clientNote, price, quoteID, requestID,clientID, billID, date);
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
    
    
    public void update(int qID, int bID) throws SQLException {
    	
        String sql = "update Quote set billID = " + bID + " where quoteID = " + qID + ";";
        connect_func();
        statement =  (Statement) connect.createStatement();
        
        statement.execute(sql);   
    }
    
    public void saveNote(int qID, int uID, String note, String date) throws SQLException {
    	System.out.println("SAVE NOTE FUNCTION RUNNING IN QUOTEDAO");
    	
    	connect_func();
        statement =  (Statement) connect.createStatement();
        System.out.println(qID);
        System.out.println(uID);
        System.out.println(note);
        System.out.println(date);
      
        
        statement.execute("SET FOREIGN_KEY_CHECKS = 0;");
        String sql = "insert into QuoteResponse(quoteID, clientID, reply, issueDate) values (?, ?, ?, ?);";
        
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setInt(1, qID);
        preparedStatement.setInt(2, uID);
        preparedStatement.setString(3, note);
		preparedStatement.setString(4, date);
         
		preparedStatement.executeUpdate();
        preparedStatement.close();
        
        statement.execute("SET FOREIGN_KEY_CHECKS = 1;");
        disconnect();
     
        System.out.println("SAVE NOTE FUNCTION TERMINATED IN QUOTEDAO");
    }
    
    
    public boolean delete(int quoteID) throws SQLException {
    	System.out.println("DELETE QUOTE RUNNIUNG");
    	
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
        
        System.out.println("DELETE QUOTE TERMINATED");
        
        return rowDeleted;     
    }
    
    public void deleteBill(int bID) throws SQLException {
		String sql = "";
		System.out.println("DELETE BILL RUNNIUNG");
		sql = "update Quote set billID = 0  where billID = " + bID + ";";
        
        connect_func();
        statement =  (Statement) connect.createStatement();
        statement.execute("SET FOREIGN_KEY_CHECKS = 0;");
        
        statement.execute(sql);  
        
        statement.execute("SET FOREIGN_KEY_CHECKS = 1;");
        
        System.out.println("DELETE BILL TERMINATED");
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
        System.out.println("UPDATE POST TERMINATED");
    }
    
    public quote getQuote(int quoteID) throws SQLException {
    	System.out.println("GET QUOTE RUNNIUNG");
    	quote quote = null;
        String sql = "SELECT * FROM Quote WHERE quoteID = ?";
         
        connect_func();
         
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setInt(1, quoteID);
         
        ResultSet resultSet = preparedStatement.executeQuery();
         
        if (resultSet.next()) {
            String time = resultSet.getString("times");
            String smithNote = resultSet.getString("smithNote");
            String clientNote = resultSet.getString("clientNote");
            double price = resultSet.getDouble("price");
            int requestID = resultSet.getInt("requestID");
            int clientID = resultSet.getInt("clientID");
            Date date = resultSet.getTimestamp("issueDate");
            int billID = resultSet.getInt("billID");
            
            quote = new quote(time, smithNote, clientNote, price, quoteID, requestID, clientID, billID, date);
        }
         
        resultSet.close();
        statement.close();
        System.out.println("GET QUOTE TERMINATED");
         
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
					        ("Create TABLE if not exists Quote(\n"
					        		+ "  quoteID int not null auto_increment, \n"
					        		+ "  clientNote varchar(150) default 'pending', \n"
					        		+ "  smithNote varchar(150) default 'pending', \n"
					        		+ "  times time not null default '1:00:00', \n"
					        		+ "  price double not null default '0.00',\n"
					        		+ "  requestID int not null default '0',\n"
					        		+ "  clientID int not null default '0', \n"
					        		+ "  billID int not null default '0',\n"
					        		+ "  issueDate datetime not null default '1990-01-31 10:24:40', \n"
					        		+ "  PRIMARY KEY (QuoteID),\n"
					        		+ "  Foreign key (requestID) references Request(requestID),\n"
					        		+ "  Foreign key (billID) references Bill(billID),\n"
					        		+ "  foreign key (clientID) references User(clientID)\n"
					        		+ ");")
        					};
        String[] TUPLES = {
        					"alter table Quote auto_increment = 20;",
        					("INSERT INTO Quote( clientNote , smithNote, times, price, requestID, clientID, issueDate, billID)\n"
        							+ "VALUES \n"
        							+ "( 'Looks Good!', 'Awsome', '1:00:00', 4500, 200, 111, '2022-05-16 06:46:40', default),\n"
        							+ "( 'Please!', 'Sure', '2:00:00', 5500, 201, 111, '2022-08-16 06:46:40', 10),\n"
        							+ "( 'Sold!', 'thank you', '3:00:00', 6500, 204, 111, '2022-06-16 06:46:40', default),\n"
        							+ "( 'Why so much!', 'Its a lot of trees', '4:00:00', 7500, 202, 109, '2022-03-16 06:46:40', 10),\n"
        							+ "( 'Roger!', 'Yay', '5:00:00', 8500, 5, 110, '2022-11-16 06:46:40', 11),\n"
        							+ "( 'NeverMind!', 'OK', '6:00:00', 9500, 6, 100, '2023-01-16 06:46:40', 12),\n"
        							+ "( default, 'Here you go', '7:00:00', 10500, 7, 103, '2022-05-16 06:46:40', 13),\n"
        							+ "( default, 'Just look over this', '8:00:00', 11500, 208, 105, '2022-09-16 06:46:40', 14),\n"
        							+ "( default, 'I got you', '9:00:00', 12500, 9, 104, '2022-06-16 06:46:40', 15),\n"
        							+ "( 'Nice!', 'Thanks', '10:00:00', 13500, 210, 111, '2022-12-16 06:46:40', default);"),
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
