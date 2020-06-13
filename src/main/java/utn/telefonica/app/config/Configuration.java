package utn.telefonica.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import utn.telefonica.app.session.BackOfficeSessionFilter;
import utn.telefonica.app.session.CallFilter;
import utn.telefonica.app.session.SessionFilter;

@org.springframework.context.annotation.Configuration
@EnableScheduling
public class Configuration {

    @Autowired
    SessionFilter sessionFilter;
    @Autowired
    CallFilter callFilter;

    @Autowired
    BackOfficeSessionFilter backOfficeSessionFilter;

    @Bean
    public FilterRegistrationBean userFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(sessionFilter);
        registration.addUrlPatterns("/api/*");
        return registration;
    }

    @Bean
    public FilterRegistrationBean antennaFilter() {
        FilterRegistrationBean antena = new FilterRegistrationBean();
        antena.setFilter(callFilter);
        antena.addUrlPatterns("/antenna/*");
        return antena;
    }

    @Bean
    public FilterRegistrationBean backofficeFilter() {
        FilterRegistrationBean backoffice = new FilterRegistrationBean();
        backoffice.setFilter(backOfficeSessionFilter);
        backoffice.addUrlPatterns("/backoffice/*");
        return backoffice;
    }

}
