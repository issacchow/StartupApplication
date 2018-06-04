package com.isc.application.startup.controller.app.hello;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.isc.application.startup.beans.MyBean;
import com.isc.application.startup.util.BeanInitLogger;

@RestController
@RequestMapping("/app/hello")
public class HelloController extends BeanInitLogger {


    @RequestMapping(value = { "/","index"})
    public Object index(){
       return new MyBean();
    }


}
