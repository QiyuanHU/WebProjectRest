package rpc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import db.DBConnection;

/**
 * Servlet implementation class GetRestaurantsNearby
 * 改名字为RestaurantsNearby就restful
 * 也可用Get传值
 */
@WebServlet("/GetRestaurantsNearby")
public class GetRestaurantsNearby extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetRestaurantsNearby() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//写Html
		//定义contentType
		//可以改称Html
		//response.setContentType("text/html");
		//PrintWriter out = response.getWriter();
	  	//out.println("<html><body>");
	  	//out.println("<h1>This is a HTML page</h1>");
	  	//out.println("</html></body>");
	  	//out.flush();
	  	//out.close();

		//写一句话
		//response.setContentType("application/json"); //what if I change it to txt
		//谁都可以access
		//response.addHeader("Access-Control-Allow-Origin", "*");
		//String username = "";
		//PrintWriter out = response.getWriter();
		//if (request.getParameter("username") != null) {
			 //username = request.getParameter("username");
		  		 //out.print("Hello " + username);
		  	 //}
			//写到对面去，从缓存推出到response去。
		  	 //out.flush();
		  	 //out.close();
		
		//写Json Obj
		response.setContentType("application/json");
	  	 response.addHeader("Access-Control-Allow-Origin", "*");
	  	 String username = "";
	  	 if (request.getParameter("username") != null) {
	  		 username = request.getParameter("username");
	  	 }
	  	 JSONObject obj = new JSONObject();
	  	 try {
	  		 obj.append("username", username);
	  		 obj.append("Date of Birth", "Aug 13");
	  		 obj.append("Home", "Beijing");
	  		 obj.append("Age", (double)24.222);
	  		 obj.append("Age", (double)24.222);

	  	 } catch (JSONException e) {
	  		 // TODO Auto-generated catch block
	  		 e.printStackTrace();
	  	 }
	  	 PrintWriter out = response.getWriter();
	  	 out.print(obj);
	  	 out.flush();

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	private static final DBConnection connection = new DBConnection();

	protected void doPost(HttpServletRequest request,
	 		 HttpServletResponse response) throws ServletException, IOException {
	 	 StringBuffer jb = new StringBuffer();
	 	 String line = null;
	 	 try {
	 		 BufferedReader reader = request.getReader();
	 		 while ((line = reader.readLine()) != null) {
	 			 jb.append(line);
	 		 }
	 		 reader.close();
	 	 } catch (Exception e) { /* report an error */
	 	 }

	 	 try {
	 		 JSONObject input = new JSONObject(jb.toString());
	 		 JSONArray array = null;
	 		 if (input.has("lat") && input.has("lon")) {
	 			 double lat = (Double) input.get("lat");
	 			 double lon = (Double) input.get("lon");
	 			 array = connection.GetRestaurantsNearLoation(lat, lon);
	 		 }
	 		 response.setContentType("application/json");
	 		 response.addHeader("Access-Control-Allow-Origin", "*");
	 		 PrintWriter out = response.getWriter();
	 		 out.print(array);
	 		 out.flush();
	 		 out.close();
	 	 } catch (JSONException e) {
	 		 e.printStackTrace();
	 	 }

	}

}
