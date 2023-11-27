import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.sql.PreparedStatement;


public class ControlServlet extends HttpServlet {
	    private static final long serialVersionUID = 1L;
	    private userDAO userDAO = new userDAO();
	    private requestDAO requestDAO = new requestDAO();
	    private quoteDAO quoteDAO = new quoteDAO();
	    private treeDAO treeDAO = new treeDAO();
	    private billDAO billDAO = new billDAO();
	    private replyDAO replyDAO = new replyDAO();
	    private String currentUser;
	    private HttpSession session=null;
	    
	    private SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");  
	    
	    public ControlServlet()
	    {
	    	
	    }
	    
	    public void init()
	    {
	    	userDAO = new userDAO();
	    	requestDAO = new requestDAO();
	    	quoteDAO = new quoteDAO();
	    	treeDAO = new treeDAO();
	    	billDAO = new billDAO();
	    	replyDAO = new replyDAO();
	    	currentUser= "";
	    }
	    
	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        doGet(request, response);
	    }
	    
	    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        String action = request.getServletPath();
	        System.out.println(action);
	    
	    try {
        	switch(action) {  
        	case "/login":
        		login(request,response);
        		break;
        	case "/register":
        		register(request, response);
        		break;
        	case "/initialize":
        		userDAO.init();
        		requestDAO.init();
        		quoteDAO.init();
        		treeDAO.init();
        		billDAO.init();
        		System.out.println("Database successfully initialized!");
        		rootPage(request,response,"");
        		break;
        	case "/root":
        		rootPage(request,response, "");
        		break;
        	case "/logout":
        		logout(request,response);
        		break;
        	 case "/listUser": 
                 System.out.println("The action is: listUser");
                 listUser(request, response);           	
                 break;
        	 case "/treeView":
        		 System.out.println("The action is: treeView");
                 showTrees(request, response);           	
                 break;
        	 case "/requestView":
        		 System.out.println("The action is: requestView");
                 showRequest(request, response);           	
                 break;
        	 case "/quoteView":
        		 System.out.println("The action is: quoteView");
                 showQuote(request, response);           	
                 break;
        	 case "/pendRequest":
        		 System.out.println("The action is: pendRequest");
                 pendRequest(request, response);           	
                 break;
        	 case "/createQuote":
        		 System.out.println("The action is: pendRequest");
                 createQuote(request, response);           	
                 break;
        	 case "/quoted":
        		 System.out.println("The action is: quoted");
                 quoted(request, response);           	
                 break;
        	 case "/createRequestNote":
        		 System.out.println("The action is: createRequestNote");
        		 createRequestNote(request, response);           	
                 break;
        	 case "/createQuoteNote":
        		 System.out.println("The action is: createQuoteNote");
        		 createQuoteNote(request, response);           	
                 break;
        	 case "/createBillNote":
        		 System.out.println("The action is: createBillNote");
        		 createBillNote(request, response);           	
                 break;
        	 case "/postRequestNote":
        		 System.out.println("The action is: postRequestNote");
        		 postRequestNote(request, response);           	
                 break;
        	 case "/postQuoteNote":
        		 System.out.println("The action is: postQuoteNote");
        		 postQuoteNote(request, response);           	
                 break;
        	 case "/postBillNote":
        		 System.out.println("The action is: postBillNote");
        		 postBillNote(request, response);           	
                 break;
        	 case "/requestForm":
        		 System.out.println("The action is: requestForm");
        		 requestForm(request, response);           	
                 break;
        	 case "/seeRequests":
        		 System.out.println("The action is: seeRequests");
        		 seeRequests(request, response);           	
                 break;
        	 case "/seeQuotes":
        		 System.out.println("The action is: seeQuotes");
        		 seeQuotes(request, response);           	
                 break;
        	 case "/seeBills":
        		 System.out.println("The action is: seeBills");
        		 seeBills(request, response);           	
                 break;
        	 case "/submitRequest":
        		 System.out.println("The action is: submitRequest");
        		 submitRequest(request, response);           	
                 break;
        	 case "/quoteAgree":
        		 System.out.println("The action is: quoteAgree");
        		 quoteAgree(request, response);           	
                 break;
        	 case "/quoteQuit":
        		 System.out.println("The action is: quoteQuit");
        		 quoteQuit(request, response);           	
                 break;
        	 case "/billAgree":
        		 System.out.println("The action is: billAgree");
        		 billAgree(request, response);           	
                 break;
        	 case "/billQuit":
        		 System.out.println("The action is: billQuit");
        		 //billQuit(request, response);           	
                 break;
        	 case "/payBill":
        		 System.out.println("The action is: payBill");
        		 payBill(request, response);           	
                 break;
        	 case "/billPrint":
        		 System.out.println("The action is: billPrint");
        		 billPrint(request, response);           	
                 break;
        	 case "/cutTree":
        		 System.out.println("The action is: cutTree");
        		 cutTree(request, response);           	
                 break;
        	 case "/addTreeView":
        		 System.out.println("The action is: addTreeView");
        		 addTreeView(request, response);           	
                 break;
        	 case "/submitTree":
        		 System.out.println("The action is: submitTree");
        		 submitTree(request, response);           	
                 break;
                 
                 
                 
                 
	    	}
	    }
	    catch(Exception ex) {
        	System.out.println(ex.getMessage());
	    	}
	    }
        	
	    private void listUser(HttpServletRequest request, HttpServletResponse response)
	            throws SQLException, IOException, ServletException {
	        System.out.println("listUser started: 00000000000000000000000000000000000");
	        List<user> listUser = userDAO.listAllUsers();
	        request.setAttribute("listUser", listUser);       
	        RequestDispatcher dispatcher = request.getRequestDispatcher("UserList.jsp");       
	        dispatcher.forward(request, response);
	     
	        System.out.println("listPeople finished: 111111111111111111111111111111111111");
	    }
	    
	    private void showTrees(HttpServletRequest request, HttpServletResponse response)
	            throws SQLException, ServletException, IOException {
	        System.out.println("showTrees started: 000000000000000000000000000");
	        int rID = Integer.parseInt(request.getParameter("id"));
	        System.out.println("showTrees started: 222222222222222222222222222");
	        System.out.println(rID);
	        
	        List<tree> listTree = treeDAO.listTrees(rID);
	        
	        currentUser = (String) session.getAttribute("username");
	        session.setAttribute("username", currentUser);
	        
	        RequestDispatcher dispatcher = null;
	        
	        if (currentUser.matches("davidSmith@gmail.com")) {
	        	dispatcher = request.getRequestDispatcher("davidTreeView.jsp");
	        } else {
	        	dispatcher = request.getRequestDispatcher("clientTreeView.jsp");
	        }
	        
	        request.setAttribute("listTree", listTree);
	        dispatcher.forward(request, response); // The forward() method works at server side, and It sends the same request and response objects to another servlet.
	        System.out.println("Now you see the Tree List page in your browser.");
	        System.out.println("showTrees finished: 1111111111111111111111111111");
	    }
	    
	    private void showRequest(HttpServletRequest request, HttpServletResponse response)
	            throws SQLException, ServletException, IOException {
	        System.out.println("showRequest started: 000000000000000000000000000");
	        int id = Integer.parseInt(request.getParameter("id"));
	        System.out.println("showID");
	        
	        System.out.println(id);
	        List<request> listRequest = requestDAO.listRequests(id);
	        RequestDispatcher dispatcher = request.getRequestDispatcher("requestList.jsp");
	        request.setAttribute("listRequest", listRequest);
	        
	        dispatcher.forward(request, response); // The forward() method works at server side, and It sends the same request and response objects to another servlet.
	        System.out.println("Now you see the Request List page in your browser.");
	        System.out.println("showRequest finished: 1111111111111111111111111111");
	    }
	    
	    private void showQuote(HttpServletRequest request, HttpServletResponse response)
	            throws SQLException, ServletException, IOException {
	        System.out.println("showQuote started: 000000000000000000000000000");
	        int id = Integer.parseInt(request.getParameter("id"));
	        System.out.println("showQuote started: 222222222222222222222222222");
	        System.out.println(id);
	        List<quote> listQuote = quoteDAO.listQuote(id);
	        RequestDispatcher dispatcher = request.getRequestDispatcher("quoteList.jsp");
	        request.setAttribute("listQuote", listQuote);
	        dispatcher.forward(request, response); // The forward() method works at server side, and It sends the same request and response objects to another servlet.
	        System.out.println("Now you see the Quote List page in your browser.");
	        System.out.println("showRequest finished: 1111111111111111111111111111");
	    }
	    
	    private void pendRequest(HttpServletRequest request, HttpServletResponse response)
	            throws SQLException, ServletException, IOException {
	        System.out.println("pendRequest started: 000000000000000000000000000");
	        List<request> listPendRequest = requestDAO.listPendRequests();
	        RequestDispatcher dispatcher = request.getRequestDispatcher("pendRequest.jsp");
	        request.setAttribute("listPendRequest", listPendRequest);
	        dispatcher.forward(request, response); // The forward() method works at server side, and It sends the same request and response objects to another servlet.
	        System.out.println("Now you see the pendRequest List page in your browser.");
	        System.out.println("pendRequest finished: 1111111111111111111111111111");
	    }
	    
	    private void createQuote(HttpServletRequest request, HttpServletResponse response)
	            throws SQLException, ServletException, IOException {
	        System.out.println("createQuote started: 000000000000000000000000000");
			currentUser = (String) session.getAttribute("username");
			session.setAttribute("username", currentUser);
	        int id = Integer.parseInt(request.getParameter("id"));
	        System.out.println("createQuote started: 222222222222222222222222222");
	        System.out.println(id);
	        session.setAttribute("requestID", id);
	        RequestDispatcher dispatcher = request.getRequestDispatcher("quoteMaker.jsp");
	        dispatcher.forward(request, response); // The forward() method works at server side, and It sends the same request and response objects to another servlet.
	        System.out.println("Now you see the  quote maker page in your browser.");
	        System.out.println("createQuote finished: 1111111111111111111111111111");
	    }
	    
	    private void quoted(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	    	Double price = Double.parseDouble(request.getParameter("price"));
	        System.out.println("quoted started: Price");
	   	 	String time = request.getParameter("time");
	        System.out.println("quoted started: Time");
	   	 	String note = request.getParameter("note");
	        System.out.println("quoted started: note");
	   	 	System.out.println((Integer)session.getAttribute("requestID"));
	   	 	
	   	 	int rID = (Integer)session.getAttribute("requestID");
	   	 	System.out.println(rID);
	   	 	int uID = requestDAO.getUserID(rID);
	   	 	System.out.println(uID);
	   	 	
	   	 	Date date = new Date();
	   	 	String sDate = formatter.format(date);
	   	 	
	   	 	quote quote = new quote(price, time, note, rID, uID);
	   	 	quoteDAO.insert(quote, sDate);
	   	 	
	   	 	int qID = quoteDAO.getQuoteID(rID);
	   	 	System.out.println(qID + " " + rID);
	   	 	requestDAO.update(qID, rID);
	   	 	
	   	 	quoteDAO.saveNote(qID, uID, note, sDate);
	   	 	
	   		smithPage(request, response, "");
	   	 	
	    }
	    
	    
	    private void createRequestNote(HttpServletRequest request, HttpServletResponse response)
	            throws SQLException, ServletException, IOException {
	        System.out.println("createRequestNote started: 000000000000000000000000000");
	        
	        int id = Integer.parseInt(request.getParameter("id"));
	        System.out.println(id);
	        session.setAttribute("requestID", id);
	        
	        RequestDispatcher dispatcher = request.getRequestDispatcher("requestNoteMaker.jsp");
	        dispatcher.forward(request, response); // The forward() method works at server side, and It sends the same request and response objects to another servlet.
	        System.out.println("Now you see the note form in your browser.");
	        System.out.println("createRequestNote finished: 1111111111111111111111111111");
	    }
	    
	    private void postRequestNote(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	    	
	    	String note = (request.getParameter("note"));
	   	 	int rID = (Integer)session.getAttribute("requestID");
	   	 	currentUser = (String) session.getAttribute("username");
	   	 	
	   	 	int uID = userDAO.getUserID(currentUser);
	   	 	Date date = new Date();
	   	 	
	   	 	String iDate = formatter.format(date);
	   	 	
	   	 	requestDAO.update(rID, note, currentUser);
	   	 	
	   	 	requestDAO.saveNote(rID, uID, note, iDate);
	   	 	
	   	 	
	   	 	if(currentUser.matches("davidSmith@gmail.com")) {
	   	 		smithPage(request, response, "");
	   	 	} else {
	   	 		clientPage(request,response, "");
	   	 	} 	 		   	 	
	    }
	    
	    
	    private void createQuoteNote(HttpServletRequest request, HttpServletResponse response)
	            throws SQLException, ServletException, IOException {
	        System.out.println("createQuoteNote started: 000000000000000000000000000");
	        
	        int id = Integer.parseInt(request.getParameter("id"));
	        System.out.println(id);
	        session.setAttribute("quoteID", id);
	        RequestDispatcher dispatcher = request.getRequestDispatcher("quoteNoteMaker.jsp");
	        
	        dispatcher.forward(request, response); // The forward() method works at server side, and It sends the same request and response objects to another servlet.
	        System.out.println("Now you see the note form in your browser.");
	        System.out.println("createQuoteNote finished: 1111111111111111111111111111");
	    }
	    
	    private void postQuoteNote(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	    	String note = (request.getParameter("note"));
	        System.out.println(note);
	        
	   	 	int qID = (Integer)session.getAttribute("quoteID");
	   	 	
	   	 	currentUser = (String) session.getAttribute("username");
	   	 	
	   	 	int uID = userDAO.getUserID(currentUser);
	   	 	
	   	 	Date date = new Date();
	   	 	
	   	 	String iDate = formatter.format(date);
	   	 	
	   	 	quoteDAO.update(qID, note, currentUser);
	   	 	
	   	 	quoteDAO.saveNote(qID, uID, note, iDate);
	   	 	
	   	 	if(currentUser.matches("davidSmith@gmail.com")) {
	   	 		smithPage(request, response, "");
	   	 	} else {
	   	 		clientPage(request,response, "");
	   	 	} 	
	   	 	
	    }
	    
	    
	    
	    private void createBillNote(HttpServletRequest request, HttpServletResponse response)
	            throws SQLException, ServletException, IOException {
	        System.out.println("createBillNote started: 000000000000000000000000000");
	        
	        int id = Integer.parseInt(request.getParameter("id"));
	        System.out.println(id);
	        session.setAttribute("billID", id);
	        RequestDispatcher dispatcher = request.getRequestDispatcher("billNoteMaker.jsp");
	        
	        dispatcher.forward(request, response); // The forward() method works at server side, and It sends the same request and response objects to another servlet.
	        System.out.println("Now you see the note form in your browser.");
	        System.out.println("createBillNote finished: 1111111111111111111111111111");
	    }
	    
	    
	    private void postBillNote(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	    	String note = (request.getParameter("note"));
	        System.out.println(note);
	        
	   	 	int bID = (Integer)session.getAttribute("billID");
	   	 	
	   	 	currentUser = (String) session.getAttribute("username");
	   	 	
	   	 	int uID = userDAO.getUserID(currentUser);
	   	 	
	   	 	Date date = new Date();
	   	 	
	   	 	String iDate = formatter.format(date);
	   	 	
	   	 	billDAO.update(bID, note, currentUser);
	   	 	
	   	 	billDAO.saveNote(bID, uID, note, iDate);
	   	 	
	   	 	if(currentUser.matches("davidSmith@gmail.com")) {
	   	 		smithPage(request, response, "");
	   	 	} else {
	   	 		clientPage(request,response, "");
	   	 	} 	
	   	 	
	    }
	    
	    
	    private void requestForm(HttpServletRequest request, HttpServletResponse response)
	            throws SQLException, ServletException, IOException {
	        System.out.println("requestForm started: 000000000000000000000000000");
	        RequestDispatcher dispatcher = request.getRequestDispatcher("requestForm.jsp");
	        dispatcher.forward(request, response); // The forward() method works at server side, and It sends the same request and response objects to another servlet.
	        System.out.println("Now you see the  Request Form page in your browser.");
	        System.out.println("requestForm finished: 1111111111111111111111111111");
	    }
	    
	    
	    private void submitRequest(HttpServletRequest request, HttpServletResponse response)
	            throws SQLException, ServletException, IOException {
	        System.out.println("submitRequest started: 000000000000000000000000000");
	        String note = (request.getParameter("note"));
	        
	        String image1 = (request.getParameter("image1"));
	        String image2 = (request.getParameter("image2"));
	        String image3 = (request.getParameter("image3"));
	        String address = (request.getParameter("address"));
	        double distance = Double.parseDouble(request.getParameter("distance"));
	        double width = Double.parseDouble(request.getParameter("width"));
	        double height = Double.parseDouble(request.getParameter("height"));
	        
	        currentUser = (String) session.getAttribute("username");
	        int uID = userDAO.getUserID(currentUser);
	        Date date = new Date();
	        String sDate = formatter.format(date);
	        
	        requestDAO.insert(uID, note, sDate); 
	        int rID = requestDAO.getLatestRequest();
	        System.out.println(rID);
	        
	        tree tree = new tree(image1, image2, image3, address, distance, width, height, rID, false);
	        treeDAO.insert(tree);
	        
	        requestDAO.saveNote(rID, uID, note, sDate);
	        
	        clientPage(request,response, "");
	        System.out.println("Now you see the  Request Form page in your browser.");
	        System.out.println("submitRequest finished: 1111111111111111111111111111");
	    }
	    
	    
	    
	    	        
	    private void rootPage(HttpServletRequest request, HttpServletResponse response, String view) throws ServletException, IOException, SQLException{
	    	System.out.println("root view");
			request.setAttribute("listUser", userDAO.listAllUsers());
	    	request.getRequestDispatcher("rootView.jsp").forward(request, response);
	    }
	    
	    private void smithPage(HttpServletRequest request, HttpServletResponse response, String view) throws ServletException, IOException, SQLException{
	    	System.out.println("Smith view");
			request.setAttribute("listQuote", quoteDAO.listAllQuotes());
	    	request.getRequestDispatcher("davidSmithView.jsp").forward(request, response);
	    }
	    
	    private void clientPage(HttpServletRequest request, HttpServletResponse response, String view) throws ServletException, IOException, SQLException{
	    	System.out.println("Client view");
			request.setAttribute("listRequest", requestDAO.listAllRequests());
	    	request.getRequestDispatcher("clientView.jsp").forward(request, response);
	    }
	    
	    
	    private void seeRequests(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
	    	System.out.println("Client Requests View");
	    	currentUser = (String) session.getAttribute("username");
		 	session = request.getSession();
			session.setAttribute("username", currentUser);
	    	int id = userDAO.getUserID(currentUser);
			request.setAttribute("listRequest", requestDAO.listAllRequests(id));
	    	request.getRequestDispatcher("clientRequestView.jsp").forward(request, response);
	    }
	    
	    
	    private void seeQuotes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
	    	System.out.println("Client Quotes View");
	    	currentUser = (String) session.getAttribute("username");
	    	System.out.println(currentUser);;
	    	int id = userDAO.getUserID(currentUser);
	    	
	    	if (currentUser.equals("davidSmith@gmail.com")) {
	    		System.out.println("david Match");
	    		request.setAttribute("listQuotes", quoteDAO.listAllQuotes());
	    		request.getRequestDispatcher("davidQuoteView.jsp").forward(request, response);
	    	} else {
	    		System.out.println("No Match");
	    		request.setAttribute("listQuotes", quoteDAO.listAllQuotes(id));
	    		request.getRequestDispatcher("clientQuoteView.jsp").forward(request, response);
	    	}
	    	
	    }
	    
	    
	    private void seeBills(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
	    	System.out.println("Client Bills View");
	    	currentUser = (String) session.getAttribute("username");
	    	session = request.getSession();
			session.setAttribute("username", currentUser);
	    	int id = userDAO.getUserID(currentUser);
	    	System.out.println(id);
	    	if (currentUser.equals("davidSmith@gmail.com")) {
	    		request.setAttribute("listBills", billDAO.listAllBills());
	    		request.getRequestDispatcher("davidBillView.jsp").forward(request, response);
	    	} else {
	    		request.setAttribute("listBills", billDAO.listAllBills(id));
	    		request.getRequestDispatcher("clientBillView.jsp").forward(request, response);
	    	}
		
	    	
	    }
	    
	    
	    private void quoteAgree(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
	    	int qID = Integer.parseInt(request.getParameter("id"));
	    	int uID = quoteDAO.getUserID(qID);
	    	double price = quoteDAO.getPrice(qID);
	    	
	    	Date issueDate = new Date();
	        
	        Date dueDate = new Date();
	        dueDate.setMonth(dueDate.getMonth() + 1);
	    	
	    	bill bill = new bill(qID, uID, price, issueDate, dueDate);
	    	billDAO.generate(bill);
	    	
	    	request.getRequestDispatcher("clientView.jsp").forward(request, response);
	    }
	    
	    private void quoteQuit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
	    	int qID = Integer.parseInt(request.getParameter("id"));
	    	
	    	quoteDAO.delete(qID);
	    	requestDAO.deleteQuote(qID);
	    	
	    	request.getRequestDispatcher("clientView.jsp").forward(request, response);
	    }
	    
	    
	    
	    private void billAgree(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
	    	int bID = Integer.parseInt(request.getParameter("id"));
	    	session.setAttribute("bID", bID);
	    	
	    	double price = billDAO.getPrice(bID);
	    	
	    	request.setAttribute("bID", bID);
	    	request.setAttribute("price", price);
	    	
	    	request.getRequestDispatcher("payBillView.jsp").forward(request, response);
	    }
	    
	    
	    private void payBill(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
	    	System.out.println("PAY BILL RUNNING IN CONTROL SERVLET");
	    	int bID = (Integer) (session.getAttribute("bID"));
	    	int cardNum = Integer.parseInt(request.getParameter("cardNum"));
	    	
	    	currentUser = (String) session.getAttribute("username");
	    	session.setAttribute("username", currentUser);
	    	
	    	int uID = userDAO.getUserID(currentUser);
	    	
	    	System.out.println("CURRENT USER: " + currentUser);
	    	System.out.println("USERID: " + uID);
	    	
	    	if(userDAO.cardValid(cardNum, uID)) 
			{
			 	 
	    		billDAO.updatePayStatus(bID);
	    		request.getRequestDispatcher("clientView.jsp").forward(request, response);
			 			 			 			 
			 }
			 else {
				 double price = billDAO.getPrice(bID);
				 
				 request.setAttribute("cardStr","Card Failed: Please check your credentials.");
				 request.setAttribute("bID", bID);
				 request.setAttribute("price", price);
				 request.getRequestDispatcher("payBillView.jsp").forward(request, response);
			 }
	    	
	    	System.out.println("PAY BILL TERMINATED IN CONTROL SERVLET");
	    	
	    }
	    
	    
	    private void billPrint(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
	    	System.out.println("BILL PRINT RUNNING IN CONTROL SERVLET");
	    	int bID = Integer.parseInt(request.getParameter("id"));
	    	System.out.println("BILL ID: " + bID);
	    	
	    	currentUser = (String) session.getAttribute("username");
	    	session.setAttribute("username", currentUser);
	    	
	    	System.out.println("CURRENT USER: " + currentUser);
	    	
	    	bill bill = billDAO.getBill(bID);
	    	user user = userDAO.getUser(currentUser);
	    	
	    	
	    	request.setAttribute("bill", bill);
	    	request.setAttribute("user", user);
	    	request.setAttribute("listResponses", replyDAO.listAllBillReplies(bID));
	    	
	    	
			request.getRequestDispatcher("printBillView.jsp").forward(request, response);
	    	
	    	System.out.println("BILL PRINT TERMINATED IN CONTROL SERVLET");
	    	
	    }
	    
	    
	    private void cutTree(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
	    	System.out.println("CUT TREE RUNNING IN CONTROL SERVLET");
	    	int tID = Integer.parseInt(request.getParameter("id"));
			 	 
	    	treeDAO.updateCutStatus(tID);
	    	request.getRequestDispatcher("davidSmithView.jsp").forward(request, response);
			 			 			 			 
	    	System.out.println("CUT TREE TERMINATED IN CONTROL SERVLET");
	    	
	    }
	    
	    
	    private void addTreeView(HttpServletRequest request, HttpServletResponse response)
	            throws SQLException, ServletException, IOException {
	        System.out.println("Add Tree Form started: 000000000000000000000000000");
	        
	        int rID = Integer.parseInt(request.getParameter("id"));
	        
	        session.setAttribute("rID", rID);
	        
	        RequestDispatcher dispatcher = request.getRequestDispatcher("treeForm.jsp");
	        dispatcher.forward(request, response); // The forward() method works at server side, and It sends the same request and response objects to another servlet.
	        System.out.println("Now you see the  Tree Form page in your browser.");
	        System.out.println("Add Tree Form finished: 1111111111111111111111111111");
	    }
	    
	    
	    private void submitTree(HttpServletRequest request, HttpServletResponse response)
	            throws SQLException, ServletException, IOException {
	        System.out.println("submitTree started: 000000000000000000000000000");
	        
	        String image1 = (request.getParameter("image1"));
	        String image2 = (request.getParameter("image2"));
	        String image3 = (request.getParameter("image3"));
	        String address = (request.getParameter("address"));
	        double distance = Double.parseDouble(request.getParameter("distance"));
	        double width = Double.parseDouble(request.getParameter("width"));
	        double height = Double.parseDouble(request.getParameter("height"));
	        
	        int rID = (Integer) session.getAttribute("rID");
	        
	        System.out.println(rID);
	        
	        tree tree = new tree(image1, image2, image3, address, distance, width, height, rID, false);
	        treeDAO.insert(tree);
	       
	        
	        clientPage(request,response, "");
	        System.out.println("Now you see the  Client page in your browser.");
	        System.out.println("submitTree finished: 1111111111111111111111111111");
	    }
	    
	    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	    	 String email = request.getParameter("email");
	    	 String password = request.getParameter("password");
	    	 
	    	 if (email.equals("root") && password.equals("pass1234")) {
				 System.out.println("Login Successful! Redirecting to root");
				 session = request.getSession();
				 session.setAttribute("username", email);
				 rootPage(request, response, "");
	    	 }
	    	 else if(email.equals("davidSmith@gmail.com") && password.equals("ds1234")) {
	    		 System.out.println("Login Successful! Redirecting to Smith Page");
				 session = request.getSession();
				 session.setAttribute("username", email);
				 smithPage(request, response, "");
	    	 }
	    	 else if(userDAO.isValid(email, password)) 
	    	 {
			 	 
			 	 currentUser = email;
			 	session = request.getSession();
				session.setAttribute("username", email);
				clientPage(request, response, "");
			 			 			 			 
	    	 }
	    	 else {
	    		 request.setAttribute("loginStr","Login Failed: Please check your credentials.");
	    		 request.getRequestDispatcher("login.jsp").forward(request, response);
	    	 }
	    }
	           
	    private void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	    	String email = request.getParameter("email");
	   	 	String firstName = request.getParameter("firstName");
	   	 	String lastName = request.getParameter("lastName");
	   	 	String password = request.getParameter("password");
	   	 	String creditCard = request.getParameter("creditCard");
	   	 	String phoneNumber = request.getParameter("phoneNumber"); 
	   	 	String confirm = request.getParameter("confirmation");
	   	 	
	   	 	if (password.equals(confirm)) {
	   	 		if (!userDAO.checkEmail(email)) {
		   	 		System.out.println("Registration Successful! Added to database");
		            user users = new user(email,firstName, lastName, password, creditCard, phoneNumber);
		   	 		userDAO.insert(users);
		   	 		response.sendRedirect("login.jsp");
	   	 		}
		   	 	else {
		   	 		System.out.println("Username taken, please enter new username");
		    		 request.setAttribute("errorOne","Registration failed: Username taken, please enter a new username.");
		    		 request.getRequestDispatcher("register.jsp").forward(request, response);
		   	 	}
	   	 	}
	   	 	else {
	   	 		System.out.println("Password and Password Confirmation do not match");
	   		 request.setAttribute("errorTwo","Registration failed: Password and Password Confirmation do not match.");
	   		 request.getRequestDispatcher("register.jsp").forward(request, response);
	   	 	}
	    }    
	    private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
	    	currentUser = "";
        		response.sendRedirect("login.jsp");
        	}
	    
	    
	    
	    
	    
}
	        
	        
	    
	        
	        
	        
	    


