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
@WebServlet("/replyDAO")
public class replyDAO 
{
	private static final long serialVersionUID = 1L;
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
	public replyDAO(){}
	
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
    
    public List<reply> listAllBillReplies(int billID) throws SQLException {
        List<reply> listReply = new ArrayList<reply>();        
        String sql = "SELECT * FROM BillResponse where billID = " + billID;
        System.out.println(sql);
        connect_func();      
        statement = (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        System.out.println("LISTING");
         
        while (resultSet.next()) {
            String reply = resultSet.getString("reply");
            int replyID = resultSet.getInt("replyBID");
            int clientID = resultSet.getInt("clientID");
            Date issueDate = resultSet.getTimestamp("issueDate");
            

             
            reply replies = new reply(replyID, billID, clientID, reply, issueDate);
            listReply.add(replies);
        }        
        resultSet.close();
        disconnect();        
        return listReply;
    }
    
    public List<reply> listAllQuoteReplies(int quoteID) throws SQLException {
    	List<reply> listReply = new ArrayList<reply>();        
        String sql = "SELECT * FROM QuoteResponse where quoteID = " + quoteID;
        System.out.println(sql);
        connect_func();      
        statement = (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        System.out.println("LISTING");
         
        while (resultSet.next()) {
            String note = resultSet.getString("reply");
            int replyID = resultSet.getInt("replyQID");
            int clientID = resultSet.getInt("clientID");
            Date date = resultSet.getTimestamp("issueDate");
            

             
            reply replies = new reply(replyID, quoteID, clientID, note, date);
            listReply.add(replies);
        }        
        resultSet.close();
        disconnect();        
        return listReply;
    }
    
    protected void disconnect() throws SQLException {
        if (connect != null && !connect.isClosed()) {
        	connect.close();
        }
    }
    
    
    
    
    
    
    public void init() throws SQLException, FileNotFoundException, IOException{
    	connect_func();
        statement =  (Statement) connect.createStatement();
        
        String[] INITIAL = {"use testdb;",
					        "drop table if exists Request;",
					        "SET FOREIGN_KEY_CHECKS = 0;",
					        ("CREATE TABLE if not exists Request( " +
					            "smithNote varchar(30) default 'pending', " + 
					            "clientNote varchar(30) default 'pending', " +
					            "requestID int not null auto_increment, " +
					            "quoteID int not null default 0," +
					            "clientID int not null default 0, " +
					            "PRIMARY KEY (requestID), " +
					            "foreign key (quoteID) references Quote(quoteID)," +
					            "foreign key (clientID) references User(clientID)" +
					            "); "),
					        "SET FOREIGN_KEY_CHECKS = 1;"
        					};
        String[] TUPLES = {"SET FOREIGN_KEY_CHECKS = 0;",
        			"alter table Request auto_increment = 200;",
        			("INSERT INTO Request(clientNote, smithNote, clientID, quoteID)"+
        			"values  ( 'Sold!', '', 111, 20)," +
        			"( 'Sold!', '', 111, 21)," +
        			"( 'Sold!', '', 108, default)," +
        			"( 'Sold!', '', 109, default)," +
        			"( 'Sold!', '', 111, 22)," +
        			"( 'Sold!', '', 100, default)," +
        			"( 'Sold!', '', 111, default)," +
        			"( 'Sold!', '', 112, default)," +
        			"( 'Sold!', '', 107, default)," +
        			"( 'Sold!', '', 106, default)," +
        			"( 'Sold!', '', 111, 29)," +
        			"( 'Sold!', '', 111, 21);"),
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
