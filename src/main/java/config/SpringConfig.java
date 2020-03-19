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
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ComponentScan(basePackages = {"dao", "services", "controller", "ui", "model", "mapper", "dto", "exceptions", "utils"})
@PropertySource("classpath:database.properties")
public class SpringConfig implements WebMvcConfigurer, WebApplicationInitializer {
	
	@Autowired
	private Environment environment;
	
//	@Bean
//	NamedParameterJdbcTemplate namedParameterJdbcTemplate(DataSource dataSource) {
//		
//		NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
//		
//		return namedParameterJdbcTemplate;
//	}
	
	public DataSource sqlDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(environment.getRequiredProperty("dataSource.jdbc.driver"));
		dataSource.setUrl(environment.getRequiredProperty("dataSource.url"));
		dataSource.setUsername(environment.getRequiredProperty("dataSource.name"));
		dataSource.setPassword(environment.getRequiredProperty("dataSource.password"));
		return dataSource;
	}

	@Override
	public void onStartup(ServletContext servletContext) {
		AnnotationConfigWebApplicationContext webContext = new AnnotationConfigWebApplicationContext();
		webContext.register(SpringConfig.class, MvcConfig.class);
		webContext.setServletContext(servletContext);
		ServletRegistration.Dynamic servlet = servletContext.addServlet("dynamicServler", new DispatcherServlet(webContext));
		servlet.setLoadOnStartup(1);
		servlet.addMapping("/");
	}
}
