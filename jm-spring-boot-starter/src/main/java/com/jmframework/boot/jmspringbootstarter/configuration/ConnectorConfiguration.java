package com.jmframework.boot.jmspringbootstarter.configuration;

import lombok.RequiredArgsConstructor;
import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;

/**
 * Description: ConnectorConfiguration
 * <p>Setting up HTTPS for Spring Boot requires two steps:</p>
 * <ol>
 * <li>Getting an SSL certificate;</li>
 * <li>Configuring SSL in Spring Boot.</li>
 * </ol>
 * <a href="https://www.thomasvitale.com/https-spring-boot-ssl-certificate/">How to enable HTTPS in a Spring Boot Java
 * application</a>
 *
 * @author Johnny Miller (鍾俊), email: johnnysviva@outlook.com
 * @date 2019-05-03 14:39
 **/
//@Configuration
@RequiredArgsConstructor
public class ConnectorConfiguration {
    private final ServerConfiguration serverConfiguration;

    /**
     * Redirect all traffic to HTTPS.
     * <p>
     * Spring allows defining just one network connector in the application.properties (or application.yaml) file. Since
     * we have used it for HTTPS, we have to set the HTTP connector programmatically.
     *
     * @return HTTP connector
     */
//    @Bean
    public Connector connector() {
        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
        connector.setScheme("http");
        connector.setPort(80);
        connector.setSecure(false);
        connector.setRedirectPort(serverConfiguration.getServerPort());
        // FIXME: Following setProperty() method may solve the problem:
        //  error parsing HTTP request header. Note: further occurrences of HTTP request parsing errors will be
        //  logged at DEBUG level.
        //  Invalid character found in the request target. The valid characters are defined in RFC 7230 and RFC 3986
        connector.setProperty("relaxedQueryChars", "|{}[]");
        return connector;
    }

//    @Bean
    public TomcatServletWebServerFactory tomcatServletWebServerFactory(Connector connector) {
        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory() {
            @Override
            protected void postProcessContext(Context context) {
                SecurityConstraint securityConstraint = new SecurityConstraint();
                securityConstraint.setUserConstraint("CONFIDENTIAL");
                SecurityCollection collection = new SecurityCollection();
                collection.addPattern("/*");
                securityConstraint.addCollection(collection);
                context.addConstraint(securityConstraint);
            }
        };
        tomcat.addAdditionalTomcatConnectors(connector);
        return tomcat;
    }
}
