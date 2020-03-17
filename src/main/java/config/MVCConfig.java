package config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
@Configuration
public class MVCConfig implements WebMvcConfigurer {
	
//	@Override
//	public void addViewControllers(ViewsControllerRegistry registry) {
//		registry.addViewController("/").setViewName("dashboard");
//	}
//	
//	@Bean
//	public ViewResolver getViewLocation() {
//		
//		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
//		
//		viewResolver.setViewClass(JstlView.class);
//		viewResolver.setPrefix("/WEB-INF/views/");
//		viewResolver.setSuffix(".jsp");
//		
//		return viewResolver;
//	}

}
