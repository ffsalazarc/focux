package biz.intelix.focuX;

import org.jsondoc.spring.boot.starter.EnableJSONDoc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@EnableJSONDoc
public class FocuXApplication {

    public static void main(String[] args) {
        SpringApplication.run(FocuXApplication.class, args);
    }

}
