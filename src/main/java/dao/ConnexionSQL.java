package dao;

import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Repository
public final class ConnexionSQL {

	private static volatile ConnexionSQL instance;
	private static HikariConfig configProperties;
	private static HikariDataSource dataSource;
	private static final String CONFIGURATION_LOCATION = "/database.properties";

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

		configProperties = new HikariConfig(CONFIGURATION_LOCATION);
		dataSource = new HikariDataSource(configProperties);

		try {
			return dataSource.getConnection();

		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(0);
			return null;
		}
	}
}
