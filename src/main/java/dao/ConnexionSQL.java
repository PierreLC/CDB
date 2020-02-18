package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.lang.AutoCloseable;
public final class ConnexionSQL {

	private static volatile ConnexionSQL instance;
	private static Connection conn;
	
	public final static ConnexionSQL getInstance() {
        if (ConnexionSQL.instance == null) {
           synchronized(ConnexionSQL.class) {
             if (ConnexionSQL.instance == null) {
            	 ConnexionSQL.instance = new ConnexionSQL();
             }
           }
        }
        return ConnexionSQL.instance;
	}
	
	public Connection connect() {
		String url = "jdbc:mysql://localhost:3306/computer-database-db";
	    String user = "admincdb";
	    String passwd = "azerty123";
	    
        try{
        	Class.forName("com.mysql.cj.jdbc.Driver");
        	conn = DriverManager.getConnection(url, user, passwd);

        }catch(SQLException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
        return conn;
        
	}
}
