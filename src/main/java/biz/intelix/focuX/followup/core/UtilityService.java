package biz.intelix.focuX.followup.core;

import org.springframework.stereotype.Service;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Service
public class UtilityService {
    private final String constantsFilePath = "/home/focux/constants.properties";
    
    private static Logger log = LogManager.getLogger(UtilityService.class);

    public Properties readConstants() {
        Properties props = new Properties();
        try (FileReader file = new FileReader(constantsFilePath)) {
           props.load(file);
        } catch (IOException e) {
            log.error("Error al abrir archivo constants.properties");
            // Properties extends a hashmap, so create an empty map 
            // and cast it to a Properties object
            return (Properties) Collections.emptyMap();
        }
        return props;
    }
}
