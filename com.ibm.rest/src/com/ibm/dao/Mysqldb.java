/** This class is used to connect to mysql db
 *  mysql user: ibm
 *  mysql database: xe
 *  mysql password: 123456
 * 
 */
package com.ibm.dao;

import javax.naming.*;
import javax.sql.*;
/**
 * @author ibm
 *
 */
public class Mysqldb {
	/**
	 * 
	 */
	private static DataSource Mysqldb = null;
	private static Context context =null;
	
	public static DataSource Mysqldbconn() throws Exception {
		if(Mysqldb!=null){
			return Mysqldb;
		}
		try{
			if(context == null){
				context = new InitialContext();
			}
			Mysqldb = (DataSource) context.lookup("mysqldb");
		}catch(Exception e){
			e.printStackTrace();
		}
		return Mysqldb;
		
	}

}
