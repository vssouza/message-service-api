package com.example.message;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@EnableConfigurationProperties
@EnableJpaAuditing
public class MessageApiApplication {

//    @Bean
//    public WebServerFactoryCustomizer<TomcatServletWebServerFactory> sessionManagerCustomizer() {
//        //TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
//        return server -> {
//            // example of changing error page request mapping
//            server.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/404"));
//            // example of changing session timeout
//            server.addContextCustomizers(context -> context.setSessionTimeout(24 * 60));
//        };
//    }

    public static void main(String[] args) {
        SpringApplication.run(MessageApiApplication.class, args);
    }
}
