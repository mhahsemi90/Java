package calculation.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Component
@ConfigurationPropertiesBinding
@ConfigurationProperties(prefix = "pcn.exception")
public class PcnExceptionConfiguration {
    /**
     * Puts an error report in the response of the sent request.
     **/
    private Boolean showInResponse = false;
    /**
     * Log an error report in the console.
     **/
    private Boolean logInConsole = false;
}
