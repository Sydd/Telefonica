package utn.telefonica.app.config;

import io.swagger.models.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
    import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import utn.telefonica.app.session.BackOfficeSessionFilter;
import utn.telefonica.app.session.CallFilter;
import utn.telefonica.app.session.SessionFilter;

@org.springframework.context.annotation.Configuration
@EnableScheduling
@EnableSwagger2
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

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("utn.telefonica.app"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(metaData())
                .useDefaultResponseMessages(false);
    }

    private ApiInfo metaData() {


        ApiInfo apiInfo = new ApiInfo(
                "PhoneServices Api doc",
                "Phoneservices api for UTN university.",
                "0.5",
                "Terms of service","SantiMati",
                "Apache License Version 2.0",
                "https://www.apache.org/licenses/LICENSE-2.0");
        return apiInfo;
    }
}
