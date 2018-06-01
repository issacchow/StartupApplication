package springboot.controller.app.hello;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springboot.beans.MyBean;
import springboot.util.BeanInitLogger;

@RestController
@RequestMapping("/app/hello")
public class HelloController extends BeanInitLogger {


    @RequestMapping(value = { "/","index"})
    public Object index(){
       return new MyBean();
    }


}
