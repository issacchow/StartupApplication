package com.isc.application.startup.config;

import jodd.io.StreamUtil;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.*;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.util.*;

/**
 * @author 周伟锋
 */
@Configuration
public class RestTemplateConfig {


    /**
     * 连接池的最大连接数默认为0
     */
    @Value("${remote.maxTotalConnect:10}")
    private int maxTotalConnect;

    /**
     * 单个主机的最大连接数
     */
    @Value("${remote.maxConnectPerRoute:200}")
    private int maxConnectPerRoute;

    /**
     * 连接超时默认2s
     */
    @Value("${remote.connectTimeout:4000}")
    private int connectTimeout;

    /**
     * 读取超时默认30s
     */
    @Value("${remote.readTimeout:30000}")
    private int readTimeout;

    /**
     * 创建HTTP客户端工厂
     *
     * @return
     */
    @Bean
    public ClientHttpRequestFactory createFactory() {
        if (this.maxTotalConnect <= 0) {
            SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
            factory.setConnectTimeout(this.connectTimeout);
            factory.setReadTimeout(this.readTimeout);
            return factory;
        }


        CloseableHttpClient client = HttpClients.createDefault();
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(client);
        factory.setConnectTimeout(this.connectTimeout);
        factory.setReadTimeout(this.readTimeout);
        return factory;
    }

    @Primary
    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setRequestFactory(createFactory());
        restTemplate.setInterceptors(Collections.<ClientHttpRequestInterceptor>singletonList(new LoggingClientHttpRequestInterceptor()));
        return restTemplate;
    }


    /**
     * ruby api专用 RestTemplate
     *
     * @return
     */
    @Bean("rubyRestTemplate")
    public RestTemplate rubyRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setRequestFactory(createFactory());


        //配置默认协议头
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        headers.add("secret", "xxxxxxxxxxxx");

        restTemplate.setInterceptors(Arrays.asList(
                new HeaderInterceptor(headers),
                new LoggingClientHttpRequestInterceptor()
        ));
        return restTemplate;
    }


    /**
     * Secret拦截器
     * 会在Header中增加一个Secret值
     */
    public static class HeaderInterceptor implements ClientHttpRequestInterceptor {

        HttpHeaders headers;

        public HeaderInterceptor(HttpHeaders headers) {
            this.headers = headers;
        }

        @Override
        public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {

            Set<String> keys = headers.keySet();
            for (String key : keys) {
//                request.getHeaders().addAll(key, headers.get(key));
            }
            ClientHttpResponse response = execution.execute(request, body);
            return response;
        }
    }

    /**
     * 请求及响应日志打印拦截器
     */
    public static class LoggingClientHttpRequestInterceptor implements ClientHttpRequestInterceptor {

        private final static Logger LOGGER = LoggerFactory.getLogger(LoggingClientHttpRequestInterceptor.class);

        @Override
        public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {

            String traceId = UUID.randomUUID().toString();
            request.getHeaders().add("Trace-Id",traceId);
            //打印请求参数
            traceRequest(request, body, traceId);

            Date start = new Date();
            ClientHttpResponse response = execution.execute(request, body);
            Date end = new Date();
            long elapsedTime = end.getTime() - start.getTime();

            //打印响应结果
            ClientHttpResponseWrapper wrapper = new ClientHttpResponseWrapper(response);
            traceResponse(wrapper, request, elapsedTime, traceId);
            return wrapper;
        }

        private void traceRequest(HttpRequest request, byte[] body, String traceId) throws IOException {
            LOGGER.info("");
            LOGGER.info("========================== HTTP请求参数 START =============================");
            LOGGER.info("Trace id    : {}", traceId);
            LOGGER.info("URI         : {}", request.getURI());
            LOGGER.info("Method      : {}", request.getMethod());
            LOGGER.info("Headers     : {}", request.getHeaders());
            LOGGER.info("Request body: {}", new String(body, "UTF-8"));
            LOGGER.info("================================ END ====================================");
            LOGGER.info("");
        }

        private void traceResponse(ClientHttpResponseWrapper response, HttpRequest request, long elapsedTime, String traceId) throws IOException {


            LOGGER.info("");
            LOGGER.info("========================== HTTP请求响应结果 START ==========================");
            LOGGER.info("Trace id     : {}", traceId);
            LOGGER.info("URI : {} - 耗时: {}ms", request.getURI(), elapsedTime);
            LOGGER.info("Status code  : {}", response.getStatusCode());
            LOGGER.info("Status text  : {}", response.getStatusText());
            LOGGER.info("Headers      : {}", response.getHeaders());
            //WARNING: comment out in production to improve performance
            if(LOGGER.isDebugEnabled()) {
                String body = response.readBody();
                LOGGER.info("Response body: {}", body);
            }else if(LOGGER.isInfoEnabled()){
                LOGGER.info("Response body: 略（如有需要请打开debug级别访问）");
            }
            LOGGER.info("================================= END ====================================");
            LOGGER.info("");


        }

        /**
         * 包装器，解决inputstream 不可重复读的问题
         */
        static class ClientHttpResponseWrapper implements ClientHttpResponse {

            ClientHttpResponse rawResponse;
            byte[] buffer = null;

            public ClientHttpResponseWrapper(ClientHttpResponse response) throws IOException {
                this.rawResponse = response;
                buffer = StreamUtil.readBytes(response.getBody());

            }

            @Override
            public HttpStatus getStatusCode() throws IOException {
                return rawResponse.getStatusCode();
            }

            @Override
            public int getRawStatusCode() throws IOException {
                return rawResponse.getRawStatusCode();
            }

            @Override
            public String getStatusText() throws IOException {
                return rawResponse.getStatusText();
            }

            @Override
            public void close() {
                rawResponse.close();
            }

            @Override
            public InputStream getBody() throws IOException {
                //每次都返回一个新的InputStream
                return new ByteArrayInputStream(buffer);
            }

            @Override
            public HttpHeaders getHeaders() {
                return rawResponse.getHeaders();
            }

            public String readBody() throws IOException {

                StringBuilder inputStringBuilder = new StringBuilder();
                try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(getBody()))) {
                    String line = bufferedReader.readLine();
                    while (line != null) {
                        inputStringBuilder.append(line);
                        inputStringBuilder.append('\n');
                        line = bufferedReader.readLine();
                    }
                }
                return inputStringBuilder.toString();
            }
        }

    }

}
