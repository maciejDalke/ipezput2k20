package pl.meklad.ipezput2k20.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.annotation.Nonnull;

/**
 * Create by dev on 21.10.2020
 */
@Configuration
public class WebConfig extends AbstractAnnotationConfigDispatcherServletInitializer {


    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[0];
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] {WebConfig.class};
    }

    @Nonnull
    @Override
    protected String[] getServletMappings() {
        return new String[] {"/api/user/hello"};
    }
}
