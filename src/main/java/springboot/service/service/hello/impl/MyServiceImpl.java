package springboot.service.service.hello.impl;

import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;
import springboot.service.service.hello.MyService;
import springboot.service.service.hello.MyService222;
import springboot.util.BeanInitLogger;

import javax.annotation.Resource;

import static springboot.util.LogUtil.log;


@Service
@DependsOn("myBean")
public class MyServiceImpl  extends BeanInitLogger implements MyService  {


    @Resource
    private MyService222 myService222;

    public void invoke() {
        log("myService222 invoked!");
    }

    @Override
    protected void onInit() {
        myService222.invoke();
    }
}
