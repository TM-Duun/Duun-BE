package dunn.dunnshop.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;


@Getter
@ConfigurationProperties(prefix = "mail")
@RequiredArgsConstructor
public class MailPropertiesConfig {

    private final String host;
    private final String username;
    private final String password;
    private final int port;

}