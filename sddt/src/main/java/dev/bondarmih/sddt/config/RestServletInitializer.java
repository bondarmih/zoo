package dev.bondarmih.sddt.config;

import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;

/**
 * Created by bondarm on 09.08.16.
 */

public class RestServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] {PersistenceConfig.class, SecurityConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] {RepositoryRestMvcConfiguration.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/api/*"};
    }

    @Override
    protected Filter[] getServletFilters() {
        return new Filter[]{
                new DelegatingFilterProxy("springSecurityFilterChain"),
                new OpenEntityManagerInViewFilter()};
    }

    @Override
    protected String getServletName() {
        return "restServlet";
    }

}
