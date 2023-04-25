package com.example.VWorks.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import java.util.HashMap;
import java.util.Map;

@Component
public class EndpointInfoService {

    @Autowired
    private ApplicationContext applicationContext;

    public Map<String, Map<String, String>> getAllEndpoints() {
        Map<String, Map<String, String>> endpoints = new HashMap<>();
        RequestMappingHandlerMapping handlerMapping = applicationContext.getBean(RequestMappingHandlerMapping.class);
        handlerMapping.getHandlerMethods().forEach((requestMappingInfo, handlerMethod) -> {
            RestController restController = handlerMethod.getBeanType().getAnnotation(RestController.class);
            if (restController != null) {
                //String methodName = handlerMethod.getMethod().getName();
                String requestMethod = requestMappingInfo.getMethodsCondition().getMethods().iterator().next().name();
                String path = requestMappingInfo.getPatternsCondition().getPatterns().iterator().next();
                //restController.value()
                endpoints.computeIfAbsent("Available Endpoints", k -> new HashMap<>())
                        .put(path, requestMethod );
            }
        });

        return endpoints;
    }
}

