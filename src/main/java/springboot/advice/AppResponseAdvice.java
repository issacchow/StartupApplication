package springboot.advice;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import springboot.controller.app.AppControllerMarker;
import springboot.data.web.ResponseData;
import springboot.data.web.ResponseDataGeneric;

@ControllerAdvice(basePackageClasses = {AppControllerMarker.class })
public class AppResponseAdvice implements ResponseBodyAdvice {


    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        //returnType.getMethod().getAnnotation()
        //可根据注解进行拦截
        return  true;
    }

    @Override
    public Object beforeBodyWrite(Object controllerReturnValue, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        ResponseData responseData = new ResponseData();
        responseData.setData(controllerReturnValue);
        return responseData;
    }


}
