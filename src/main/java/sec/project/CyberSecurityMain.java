package sec.project;

import org.apache.catalina.Context;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatContextCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;

@SpringBootApplication
public class CyberSecurityMain implements EmbeddedServletContainerCustomizer {

    public static void main(String[] args) throws Throwable {
        SpringApplication.run(CyberSecurityMain.class);
    }

    @Override
    public void customize(ConfigurableEmbeddedServletContainer cesc) {
        TomcatEmbeddedServletContainerFactory tomcatCesc = (TomcatEmbeddedServletContainerFactory) cesc;
        ((TomcatEmbeddedServletContainerFactory) tomcatCesc).addContextCustomizers((TomcatContextCustomizer) (Context cntxt) -> {
            cntxt.setUseHttpOnly(false);
            cntxt.setSessionCookieName("SESSIONID");
        });
    }
}
