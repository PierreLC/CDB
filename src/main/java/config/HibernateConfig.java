//package config;
//
//import javax.sql.DataSource;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.core.env.Environment;
//import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;
//import org.springframework.orm.hibernate5.HibernateTransactionManager;
//import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//@Configuration
//@EnableTransactionManagement
//@PropertySource(value = "classpath:application.properties")
//public class HibernateConfig {
//
//	@Autowired
//	private Environment environment;
//
//	@Bean
//	public LocalSessionFactoryBean sessionFactory() {
//
//		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
//		sessionFactory.setDataSource(dataSource());
//		sessionFactory.setPackagesToScan("dao", "services", "controller", "ui", "model", "mapper", "dto", "exceptions");
//
//		return sessionFactory;
//	}
//
//	@Bean
//	public DataSource dataSource() {
//
//		DriverManagerDataSource dataSource = new DriverManagerDataSource();
//		dataSource.setUrl(environment.getProperty("datasource.jdbcUrl"));
//		dataSource.setUsername(environment.getProperty("datasource.username"));
//		dataSource.setPassword(environment.getProperty("datasource.password"));
//		dataSource.setDriverClassName(environment.getProperty("datasource.driverClassName"));
//
//		return dataSource;
//	}
//
//	@Bean
//	public NamedParameterJdbcTemplate namedParameterJdbcTemplate(DataSource dataSource) {
//
//		NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
//
//		return namedParameterJdbcTemplate;
//	}
//
//	@Bean
//	public PlatformTransactionManager hibernateTransactionManager() {
//
//		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
//		transactionManager.setSessionFactory(sessionFactory().getObject());
//
//		return transactionManager;
//	}
//
//	@Bean
//	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
//		return new PersistenceExceptionTranslationPostProcessor();
//	}
//}
