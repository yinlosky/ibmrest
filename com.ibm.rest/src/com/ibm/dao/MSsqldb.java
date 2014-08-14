/**
 * 
 */
package com.ibm.dao;

/**
 * @author ibm
 *
 */
import javax.naming.*;
import javax.sql.*;
/**
 * @author ibm
 *
 */
public class MSsqldb {
	/**
	 * 
	 */
	private static DataSource MSsqldb = null;
	private static Context context =null;
	
	public static DataSource Mysqldbconn() throws Exception {
		if(MSsqldb!=null){
			return MSsqldb;
		}
		try{
			if(context == null){
				context = new InitialContext();
			}
			MSsqldb = (DataSource) context.lookup("mssqldb");
		}catch(Exception e){
			e.printStackTrace();
		}
		return MSsqldb;
		
	}

}
