package dao;

import java.sql.Connection;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public final class ConnexionSQL {

	private static volatile ConnexionSQL instance;
	private static HikariConfig connectProperties;
	private static HikariDataSource dataSource;
	private static final String CONFIGURATION_LOCATION = "database.properties";

	public ConnexionSQL() {
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

		connectProperties = new HikariConfig(CONFIGURATION_LOCATION);
		dataSource = new HikariDataSource(connectProperties);

		try {
			return dataSource.getConnection();

		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(0);
			return null;
		}
	}
}
