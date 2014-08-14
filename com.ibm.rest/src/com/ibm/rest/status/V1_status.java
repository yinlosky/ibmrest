package com.ibm.rest.status;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.sql.*;
import com.ibm.dao.*;

/** This is the root path for our restful api service
 *  In the web.xml file, we specified that /api/* need to be in the url to 
 *  get this class.
 *  
 *  We are versioning the class in the URL path. This is the first version V1.
 *  Example how to get to the root of this api resource:
 *  http://localhost:7001/com.ibm.rest/api/v1/status
 *  
 * @author ibm
 *
 */
@Path("/v1/status")   // used to route to java class
public class V1_status {
	private static final String api_version = "00.02.00"; 
	//only one get method is allowed in one api
	
	static public final String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	static public final String database= "worlddata";
	static public final String userName ="sa";
	static public final String password = "123456";
	static public final String ip="9.24.170.38";
	
	/**
	 * This method sits at the root of the api. It will return the name of this api.
	 * @return String - Title of the api
	 */
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String returnTitle(){
		return "<p>Java Web service</p>";
	}
	
	/** 
	 * This method returns the version number of this api
	 * Note: This is nested one down from the root. You will need add version 
	 * into the URL path.
	 * 
	 * Example: http://localhost:7001/com.ibm.rest/api/v1/status/version
	 * 
	 * @return String - version number of the api   
	 * 
	 */
	@Path("/version")     // used to route to specific method
	@GET
	@Produces(MediaType.TEXT_HTML)
 
	public String returnVersion(){ 
		return "<p>Version:</p>"+api_version;
	}
	
	/** This class uses the mysqldb conn method defined in com.ibm.dao package 
	 * @return
	 * @throws Exception
	 */
	@Path("/database")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String returnDatabaseStatus() throws Exception{
		String connUrl="jdbc:sqlserver://"+ip+";database="+database+";";
		Connection conn = null;
		String returnString = null;
		String myString = null;
		try{
			Class.forName(driver);
			conn = DriverManager.getConnection(connUrl,userName,password);
			Statement st = conn.createStatement();
			System.out.println("Try connecting");
			String sql = "select * from monthly";
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()){
				System.out.println(rs.getString("country"));
				myString=rs.getString("country");
			}
			conn.close();
			returnString = "<p>Database status</p>"+
			"<p>Database time/date return: " + myString +"</p>";
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			if(conn!=null)conn.close();
		}
		return returnString;
	}
}
