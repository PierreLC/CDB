package config;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

@Configuration
@ComponentScan(basePackages = {"dao", "services", "controller", "ui", "model", "mapper", "dto", "exceptions", "config"})
@PropertySource(value = "classpath:application.properties")
@EnableJpaRepositories("Repository")
@EnableTransactionManagement
public class SpringConfig implements WebApplicationInitializer {

	@Autowired
	private Environment environment;
	
	@Override
	public void onStartup(ServletContext servletContext) {
		
		AnnotationConfigWebApplicationContext webContext = new AnnotationConfigWebApplicationContext();
		webContext.register(SpringConfig.class, MvcConfig.class);
		webContext.setServletContext(servletContext);
		ServletRegistration.Dynamic servlet = servletContext.addServlet("dynamicServlet", new DispatcherServlet(webContext));
		servlet.setLoadOnStartup(1);
		servlet.addMapping("/");
	}
	
	@Bean
	public LocalSessionFactoryBean sessionFactory() {

		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setPackagesToScan("model");

		return sessionFactory;
	}

	@Bean
	public DataSource dataSource() {

		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUrl(environment.getProperty("datasource.jdbcUrl"));
		dataSource.setUsername(environment.getProperty("datasource.username"));
		dataSource.setPassword(environment.getProperty("datasource.password"));
		dataSource.setDriverClassName(environment.getProperty("datasource.driverClassName"));

		return dataSource;
	}

//	@Bean
//	public NamedParameterJdbcTemplate namedParameterJdbcTemplate(DataSource dataSource) {
//
//		NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
//
//		return namedParameterJdbcTemplate;
//	}

	@Bean
	public PlatformTransactionManager hibernateTransactionManager() {

		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(sessionFactory().getObject());

		return transactionManager;
	}
}
