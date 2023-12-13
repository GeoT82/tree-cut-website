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
@WebServlet("/requestDAO")
public class requestDAO 
{
	private static final long serialVersionUID = 1L;
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
	public requestDAO(){}
	
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
    
    public List<request> listAllRequests() throws SQLException {
        List<request> listRequest = new ArrayList<request>();        
        String sql = "SELECT * FROM Request";      
        connect_func();      
        statement = (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        System.out.println("LISTING");
         
        while (resultSet.next()) {
            String smithNote = resultSet.getString("smithNote");
            String clientNote = resultSet.getString("clientNote");
            int requestID = resultSet.getInt("requestID");
            int quoteID = resultSet.getInt("quoteID");
            int clientID = resultSet.getInt("clientID");
            Date date = resultSet.getTimestamp("issueDate");
            int treeCount = resultSet.getInt("treeCount");
            

             
            request requests = new request(smithNote, clientNote, requestID, quoteID, clientID, date, treeCount);
            listRequest.add(requests);
        }        
        resultSet.close();
        disconnect();        
        return listRequest;
    }
    
    
    public List<request> getBigClient() throws SQLException {
    	System.out.println("GET BIG CLIENT RUNNING");
    	
        List<request> listRequest = new ArrayList<request>();        
        String sql = "select clientID, sumCount from TreeCount where sumCount = (select max(sumCount) from TreeCount);";      
        connect_func();      
        statement = (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        
        System.out.println("LISTING");
         
        while (resultSet.next()) {
            int clientID = resultSet.getInt("clientID");
            int treeCount = resultSet.getInt("sumCount");
            

             
            request requests = new request("", "", 0, 0, clientID, null, treeCount);
            listRequest.add(requests);
        }        
        resultSet.close();
        disconnect();        
        System.out.println("GET BIG CLIENT TERMINATED");
        
        return listRequest;
    }
    
    
    public List<request> getTreeCount() throws SQLException {
    	System.out.println("GET TREE COUNT RUNNING");
        List<request> listRequest = new ArrayList<request>();        
        String sql = "select clientID, sum(treeCount) from Request group by clientID;";      
        connect_func();      
        statement = (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        System.out.println("LISTING");
         
        while (resultSet.next()) {
            int clientID = resultSet.getInt("clientID");
            int treeCount = resultSet.getInt("sum(treeCount)");
            

             
            request requests = new request("", "", 0, 0, clientID, null, treeCount);
            listRequest.add(requests);
        }        
        resultSet.close();
        disconnect();        
        System.out.println("GET TREE COUNT TERMINATED");
        return listRequest;
    }
    
    
    
    
    public List<request> listAllRequests(int uID) throws SQLException {
        List<request> listRequest = new ArrayList<request>();        
        String sql = "SELECT * FROM Request where clientID = " + uID;
        System.out.println(sql);
        connect_func();      
        statement = (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        System.out.println("LISTING");
         
        while (resultSet.next()) {
            String smithNote = resultSet.getString("smithNote");
            String clientNote = resultSet.getString("clientNote");
            int requestID = resultSet.getInt("requestID");
            int quoteID = resultSet.getInt("quoteID");
            int clientID = resultSet.getInt("clientID");
            Date date = resultSet.getTimestamp("issueDate");
            int treeCount = resultSet.getInt("treeCount");

             
            request requests = new request(smithNote, clientNote, requestID, quoteID, clientID, date, treeCount);
            listRequest.add(requests);
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
    
    public List<request> listRequests(int rID) throws SQLException {
        List<request> listRequest = new ArrayList<request>();        
        String sql = "SELECT * FROM Request Where Request.requestID = " + rID;      
        connect_func();      
        statement = (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        System.out.println("LISTING REQUESTS");
         
        while (resultSet.next()) {
            String smithNote = resultSet.getString("smithNote");
            String clientNote = resultSet.getString("clientNote");
            int requestID = resultSet.getInt("requestID");
            int quoteID = resultSet.getInt("quoteID");
            int clientID = resultSet.getInt("clientID");
            Date date = resultSet.getTimestamp("issueDate");
            int treeCount = resultSet.getInt("treeCount");

             
            request requests = new request(smithNote, clientNote, requestID, quoteID, clientID, date, treeCount);
            listRequest.add(requests);
        }        
        resultSet.close();
        disconnect();        
        return listRequest;
    }
    
    
    public List<request> listPendRequests() throws SQLException {
        List<request> listRequest = new ArrayList<request>();        
        String sql = "SELECT * FROM Request Where Request.quoteID = 0 ";      
        connect_func();      
        statement = (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        System.out.println("LISTING REQUESTS");
         
        while (resultSet.next()) {
            String smithNote = resultSet.getString("smithNote");
            String clientNote = resultSet.getString("clientNote");
            int requestID = resultSet.getInt("requestID");
            int quoteID = resultSet.getInt("quoteID");
            int clientID = resultSet.getInt("clientID");
            Date date = resultSet.getTimestamp("issueDate");
            int treeCount = resultSet.getInt("treeCount");
             
            request requests = new request(smithNote, clientNote, requestID, quoteID,clientID, date, treeCount);
            listRequest.add(requests);
        }        
        resultSet.close();
        disconnect();        
        return listRequest;
    }
    
    
    public void insert(request requests) throws SQLException {
    	connect_func("root","pass1234");         
		String sql = "insert into User(smithNote, clientNote, requestID) values (?, ?, ?)";
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setString(1, requests.getSmithNote());
		preparedStatement.setString(2, requests.getClientNote());
		preparedStatement.setInt(3, requests.getRequestID());	

		preparedStatement.executeUpdate();
        preparedStatement.close();
    }
    
    public void insert(int uID, String note, String date) throws SQLException {
    	connect_func();
        statement =  (Statement) connect.createStatement();
        System.out.println(uID);
        System.out.println(note);
        System.out.println(date);
      
        
        statement.execute("SET FOREIGN_KEY_CHECKS = 0;");
        String sql = "insert into Request(clientID, clientNote, issueDate, treeCount) values (?, ?, ?, ?);";
        
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setInt(1, uID);
        preparedStatement.setString(2, note);
		preparedStatement.setString(3, date);
		preparedStatement.setInt(4, 1);
         
		preparedStatement.executeUpdate();
        preparedStatement.close();
        
        statement.execute("SET FOREIGN_KEY_CHECKS = 1;");
        disconnect();
     
    }
    
    
    public void saveNote(int rID, int uID, String note, String date) throws SQLException {
    	System.out.println("SAVE NOTE FUNCTION RUNNING IN REQUESTDAO");
    	
    	connect_func();
        statement =  (Statement) connect.createStatement();
        System.out.println(rID);
        System.out.println(uID);
        System.out.println(note);
        System.out.println(date);
      
        
        statement.execute("SET FOREIGN_KEY_CHECKS = 0;");
        String sql = "insert into RequestResponse(requestID, clientID, reply, issueDate) values (?, ?, ?, ?);";
        
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setInt(1, rID);
        preparedStatement.setInt(2, uID);
        preparedStatement.setString(3, note);
		preparedStatement.setString(4, date);
         
		preparedStatement.executeUpdate();
        preparedStatement.close();
        
        statement.execute("SET FOREIGN_KEY_CHECKS = 1;");
        disconnect();
     
        System.out.println("SAVE NOTE FUNCTION TERMINATED IN REQUESTDAO");
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
    
    public void update(int qID, int rID) throws SQLException {
    	
        String sql = "update Request set quoteID = " + qID + " where requestID = " + rID + ";";
        connect_func();
        statement =  (Statement) connect.createStatement();
        
        statement.execute(sql);   
    }
    
    public void update(int rID, String note, String user) throws SQLException {
		String sql = "";
		System.out.println("UPDATE POST RUNNIUNG");
		if(user.matches("davidSmith@gmail.com")) {
			sql = "update Request set smithNote = '" + note + "' where requestID = " + rID + ";";
		} else {
			sql = "update Request set clientNote = '" + note + "' where requestID = " + rID + ";";
		}
        
        connect_func();
        statement =  (Statement) connect.createStatement();
        
        statement.execute(sql);   
    }
    
    public void deleteQuote(int qID) throws SQLException {
		String sql = "";
		System.out.println("Delete Quote RUNNIUNG");
		sql = "update Request set quoteID = 0  where quoteID = " + qID + ";";
        
        connect_func();
        statement =  (Statement) connect.createStatement();
        statement.execute("SET FOREIGN_KEY_CHECKS = 0;");
        
        statement.execute(sql);  
        
        statement.execute("SET FOREIGN_KEY_CHECKS = 1;");
    }
    
    public request getRequest(int requestID) throws SQLException {
    	request request = null;
        String sql = "SELECT * FROM Request WHERE requestID = ?";
         
        connect_func();
         
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setInt(1, requestID);
         
        ResultSet resultSet = preparedStatement.executeQuery();
         
        if (resultSet.next()) {
            String smithNote = resultSet.getString("smithNote");
            String clientNote = resultSet.getString("clientNote"); 
            int quoteID = resultSet.getInt("quoteID");
            int clientID = resultSet.getInt("clientID");
            Date date = resultSet.getTimestamp("issueDate");
            int treeCount = resultSet.getInt("treeCount");
            
            request = new request(smithNote, clientNote, requestID, quoteID, clientID, date, treeCount);
        }
         
        resultSet.close();
        statement.close();
         
        return request;
    }
    
    public int getLatestRequest() throws SQLException {
    	int requestID = 0;
        String sql = "SELECT max(requestID) FROM Request";
         
        connect_func();
        statement =  (Statement) connect.createStatement();
         
        ResultSet resultSet = statement.executeQuery(sql);
         
        if (resultSet.next()) {
            requestID = resultSet.getInt(1);
        }
         
        resultSet.close();
        statement.close();
         
        return requestID;
    }
    
    
    public int getUserID(int rID) throws SQLException {
    	int clientID = 0;
        String sql = "SELECT * FROM Request WHERE requestID = ?";
         
        connect_func();
         
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setInt(1, rID);
         
        ResultSet resultSet = preparedStatement.executeQuery();
         
        if (resultSet.next()) {
            clientID = resultSet.getInt("clientID"); 
        }
         
        resultSet.close();
        statement.close();
         
        return clientID;
    }
    
    
    public void updateTreeCount(int rID) throws SQLException {
    	
        String sql = "SELECT * FROM Request WHERE requestID = ?";
         
        connect_func();
        
        statement =  (Statement) connect.createStatement();
         
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setInt(1, rID);
         
        ResultSet resultSet = preparedStatement.executeQuery();
         
        if (resultSet.next()) {
            int treeCount = resultSet.getInt("treeCount");
            treeCount++;
            sql = "update Request set treeCount = " + treeCount + " where requestID = " + rID + ";";
            statement.execute(sql);
            
        }
         
        resultSet.close();
        preparedStatement.close();
        statement.close();
        disconnect();
    
    }
    
    
    
    public void init() throws SQLException, FileNotFoundException, IOException{
    	connect_func();
        statement =  (Statement) connect.createStatement();
        
        String[] INITIAL = {"use testdb;",
					        "drop table if exists Request;",
					        "SET FOREIGN_KEY_CHECKS = 0;",
					        ("Create TABLE if not exists Request(\n"
					        		+ "  requestID int not null auto_increment,\n"
					        		+ "  quoteID int not null default 0,\n"
					        		+ "  clientID int not null default 0,\n"
					        		+ "  treeCount int not null default 0,\n"
					        		+ "  clientNote varchar(30) default 'pending',  \n"
					        		+ "  smithNote varchar(30) default 'pending',\n"
					        		+ "  issueDate datetime not null default '1990-01-31 10:24:40', \n"
					        		+ "  PRIMARY KEY (requestID),\n"
					        		+ "  foreign key (quoteID) references Quote(quoteID),\n"
					        		+ "  foreign key (clientID) references User(clientID)\n"
					        		+ ");")
        					};
        String[] TUPLES = {
        			"alter table Request auto_increment = 200;",
        			("INSERT INTO Request(clientNote, smithNote, clientID, quoteID, issueDate, treeCount)\n"
        					+ "VALUES \n"
        					+ "( 'Added Tree', 'Ok', 111, 20, '2020-04-16 06:53:40', 2),\n"
        					+ "( 'Wait for One more tree', 'Alright', 111, 21, '2022-07-16 07:23:40', 1),\n"
        					+ "( 'Looks Good!', 'Thanks', 108, 23, '2022-05-16 09:52:40', 1),\n"
        					+ "( 'What does distance mean!', 'From home', 105, 23, '2022-02-16 07:34:40', 1),\n"
        					+ "( 'Who is this!', 'David', 102, 22, '2022-08-16 08:51:40', 1),\n"
        					+ "( '3 big trees!', 'Wow', 100, 24, '2021-10-16 10:14:40', default),\n"
        					+ "( 'Tonight!', 'I cant', 110, 25, '2021-12-16 12:21:40', default),\n"
        					+ "( 'Cut only to the stump!', 'Sure thing', 112, 26, '2022-01-16 15:12:40', default),\n"
        					+ "( 'I think Im missing a tree!', 'Better find it!', 107, 27, '2022-04-16 13:51:40', 1),\n"
        					+ "( 'How do I get a quote!', 'I will send one out', 106, 28, '2022-08-16 06:46:40', 2),\n"
        					+ "( 'Tomorrow!', 'Yes', 110, 29, '2021-04-16 09:21:40', 1),\n"
        					+ "( 'Seems ok!', 'Good', 106, 21, '2020-05-16 10:12:40', default);"),
        			"SET FOREIGN_KEY_CHECKS = 1;",
        			("create view TreeCount AS \n"
        					+ "select clientID, sum(treeCount) as sumCount\n"
        					+ "from Request\n"
        					+ "group by clientID;")
			    	};
        
        //for loop to put these in database
        for (int i = 0; i < INITIAL.length; i++)
        	statement.execute(INITIAL[i]);
        for (int i = 0; i < TUPLES.length; i++)	
        	statement.execute(TUPLES[i]);
        disconnect();
    }
    
    
   
    
    
    
    
    
	
	

}
