package nba.com.security;

import javax.servlet.ServletContext;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.web.filter.HiddenHttpMethodFilter;

public class RegisterSecurityFilter extends
		AbstractSecurityWebApplicationInitializer {
	@Override
	protected void beforeSpringSecurityFilterChain(ServletContext servletContext) {
		insertFilters(servletContext, new HiddenHttpMethodFilter());
	}
}
