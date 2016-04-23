package com.edgardo.localiza.config;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

@Configuration
public class AppInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		// TODO Auto-generated method stub

		// Load application context
		AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
		rootContext.register(AppContext.class);
		rootContext.setDisplayName("App Java Config");
		
		//Filter force encodig all aplication map 
        FilterRegistration.Dynamic characterEncodingFilter = servletContext.addFilter("characterEncodingFilter", 
        new CharacterEncodingFilter()); 
        characterEncodingFilter.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*"); 
        characterEncodingFilter.setInitParameter("encoding", "UTF-8"); 
        characterEncodingFilter.setInitParameter("forceEncoding", "true");
		
		// Context loader listener
		servletContext.addListener(new ContextLoaderListener(rootContext));

		// Dispatcher servlet
		ServletRegistration.Dynamic dispatcher = servletContext.addServlet(
				"dispatcher_App", new DispatcherServlet(rootContext));
		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping("/");

	}

}