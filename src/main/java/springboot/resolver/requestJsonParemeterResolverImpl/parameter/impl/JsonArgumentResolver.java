package springboot.resolver.requestJsonParemeterResolverImpl.parameter.impl;


import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.core.MethodParameter;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.NativeWebRequest;
import springboot.resolver.requestJsonParemeterResolverImpl.parameter.IArgumentResolver;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * @desc:
 * @author: suanjin
 * @create: 18/12/2017
 **/
public class JsonArgumentResolver implements IArgumentResolver {

    @Override
    public Object resolveArgument(MethodParameter methodParameter, NativeWebRequest nativeWebRequest) throws IOException {

        return null;
//        HttpServletRequest nativeRequest = nativeWebRequest.getNativeRequest(HttpServletRequest.class);
//        String body = new String(toByteArray(nativeRequest.getInputStream()), "utf-8");
//        if (StringUtils.isEmpty(body)) {
//            return null;
//        }
//
//        Class<?> parameterType = methodParameter.getParameterType();
//        if (List.class.isAssignableFrom(parameterType)) {
//            ParameterizedType genericParameterType = (ParameterizedType)methodParameter.getGenericParameterType();
//            Type[] actualTypeArguments = genericParameterType.getActualTypeArguments();
//            for(Type type : actualTypeArguments) {
//                Class classz = (Class)type;
//                return JsonUti.toBean(body, parameterType, classz);
//            }
//        }
//
//        Object obj = JacksonUtils.toBean(body, parameterType);
//        if (obj != null) {
//            return obj;
//        } else {
//            ObjectMapper objectMapper = JacksonUtils.createObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
//            return toBean(objectMapper, body, parameterType);
//        }
    }

    /**
     * json-->java bean
     */
//    private <T> T toBean(ObjectMapper objectMapper, String json, Class<T> clazz) {
//        try {
//            return objectMapper.readValue(json, clazz);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public static byte[] toByteArray(InputStream input) throws IOException {
//        ByteArrayOutputStream output = new ByteArrayOutputStream();
//        copy(input, output, new byte[1024 * 4]);
//        return output.toByteArray();
//    }
//
//    public static int copy(InputStream input, OutputStream output, byte[] buffer) throws IOException {
//        int count = 0;
//        int n;
//        while (-1 != (n = input.read(buffer))) {
//            output.write(buffer, 0, n);
//            count += n;
//        }
//        return count;
//    }

}
