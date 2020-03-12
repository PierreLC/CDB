package dao;

import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Component
public final class ConnexionSQL {

	private static HikariConfig configProperties;
	private static HikariDataSource dataSource;
	private static final String CONFIGURATION_LOCATION = "/database.properties";

	public ConnexionSQL() {
		super();	
	}

	@Bean
	public Connection connect() {

		configProperties = new HikariConfig(CONFIGURATION_LOCATION);
		dataSource = new HikariDataSource(configProperties);

		try {
			return dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
