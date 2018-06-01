package springboot.util;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static springboot.util.LogUtil.log;

public abstract class BeanInitLogger {

    private static Integer totalBeans = 0;
    private int beanIndex = -1;

    private String name = this.getClass().getName();

    public BeanInitLogger() {


        synchronized (totalBeans) {
            this.beanIndex = ++totalBeans;
        }
        log("%s  -- init , bean index:%s", name,this.beanIndex);
    }



    @PostConstruct
    final public void init(){
        log("%s -- onInit",name);
        onInit();
    }

    protected void onInit(){}

    @PreDestroy
    final public void destroy(){
        log("%s -- preDestroy",name);
        preDestroy();
    }


     protected void preDestroy(){}
}
