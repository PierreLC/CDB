package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public final class ConnexionSQL {

	private static volatile ConnexionSQL instance;
	private static Properties connectProperties;
	private static String url;
	private static String user;
	private static String password;
	private static String driver;
	private static final String CONFIGURATION_LOCATION = "database.properties";
	
	public ConnexionSQL () {
		super();
	}

	public final static ConnexionSQL getInstance() {
		if (ConnexionSQL.instance == null) {
			synchronized (ConnexionSQL.class) {
				if (ConnexionSQL.instance == null) {
					ConnexionSQL.instance = new ConnexionSQL();
				}
			}
		}
		return ConnexionSQL.instance;
	}

	public Connection connect() {

		connectProperties = new Properties();

		try {
			connectProperties
					.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(CONFIGURATION_LOCATION));
			url = connectProperties.getProperty("url");
			user = connectProperties.getProperty("user");
			password = connectProperties.getProperty("password");
			driver = connectProperties.getProperty("driver");
			
			Class.forName(driver);
			return DriverManager.getConnection(url, user, password);

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.exit(0);
		return null;
	}
}
