package com.isc.application.startup.advice;

import com.isc.application.startup.controller.app.AppControllerMarker;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import com.isc.application.startup.data.web.ResponseData;

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
