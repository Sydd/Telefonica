package utn.telefonica.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import utn.telefonica.app.session.Session;
import utn.telefonica.app.session.SessionFilter;

@org.springframework.context.annotation.Configuration
@EnableScheduling
public class Configuration {

    @Autowired
    SessionFilter sessionFilter;
    @Bean
    public FilterRegistrationBean myFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(sessionFilter);
            registration.addUrlPatterns("/api/*");
        return registration;
    }
}
