package config;

import javax.sql.DataSource;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.context.AbstractContextLoaderInitializer;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

@Configuration
@ComponentScan(basePackages = {"dao", "services", "servlet", "ui", "model", "mapper"})
@PropertySource("classpath:database.properties")
public class SpringContext extends AbstractContextLoaderInitializer {
	
	@Override
	protected WebApplicationContext createRootApplicationContext() {
		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		context.register(SpringContext.class);
		return context;
	}
	
	public DataSource sqlDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		return dataSource;
	}

}
