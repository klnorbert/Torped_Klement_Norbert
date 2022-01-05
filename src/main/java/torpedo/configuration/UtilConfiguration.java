package torpedo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import torpedo.service.util.CollectionUtil;
import torpedo.service.util.MapUtil;

/**
 * Spring Java configuration class for utility Spring Beans.
 */
@Configuration
public class UtilConfiguration {

    @Bean
    MapUtil mapUtil() {
        return new MapUtil();
    }

    @Bean
    CollectionUtil collectionUtil() {
        return new CollectionUtil();
    }

}
