package springboot;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springboot.advice.AdviceMarker;
import springboot.config.SpringConfigMarker;
import springboot.controller.ControllerMarker;
import springboot.service.ServiceMarker;

import static springboot.util.LogUtil.log;

/**
 * 一般应用程序(单数据源)
 */
@SpringBootApplication(
        scanBasePackageClasses = {
                SpringConfigMarker.class,
                ControllerMarker.class,
                ServiceMarker.class,
                AdviceMarker.class
        }
)
//@PropertySource("application.properties")
public class GeneralApplication {


    public static  void main(String...args){
        int a = 2;
        int b = a<<15;
        log("%s",b);

       SpringApplication.run(GeneralApplication.class,args);
    }

}
