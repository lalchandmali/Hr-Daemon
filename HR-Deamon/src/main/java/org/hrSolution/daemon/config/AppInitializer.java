package org.hrSolution.daemon.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class works as web.xml, which is used for scanning base package
 * registering dispatcher servlet
 * load on startup
 * url mapping 
 *  
 * */
/**
 * @author Suprith S
 *
 */
public class AppInitializer implements WebApplicationInitializer {
	
	private static final Logger logger = LoggerFactory
			.getLogger(AppInitializer.class);
	
	/**
	 * This is the startup point of the application
	 * */
	public void onStartup(ServletContext container) throws ServletException {
	
		AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
		ctx.register(AppConfig.class);
		ctx.setConfigLocation("org.hrSolution.daemon");
		ctx.setServletContext(container);

		ServletRegistration.Dynamic servlet = container.addServlet("dispatcher", new DispatcherServlet(ctx));

		servlet.setLoadOnStartup(1);
		servlet.addMapping("/");
		
		logger.debug("***Configuration loaded successfully.***");
		
	}
	
	

}
