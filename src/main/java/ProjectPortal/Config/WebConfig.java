package ProjectPortal.Config;

import ProjectPortal.Config.SessionInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * basic configurationn class for web settings
 * Implements WebMvcConfigurer to customize Spring MVC
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * Registers interceptors for the application
     * Adds SessionInterceptor to handle session management
     *
     * @param registry The InterceptorRegistry to register interceptors
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SessionInterceptor());
    }
}