package com.barrett.zuulgateway.config;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: smartsite-master
 * @description:
 * @author: Barrett
 * @create: 2020-07-02 18:02
 **/
@Component
@Primary
public class MultiDocConfig implements SwaggerResourcesProvider {

    @Override
    public List<SwaggerResource> get() {

        List resources = new ArrayList<>();

        /**
         * AI-Alert-Service ---> 这个就是个模块的名字，可以随便起
         * /zb-api/face-detect-service/v2/api-docs ---> /zb-api/face-detect-service就是application.properties配置的路由路径，
         * 后面的/v2/api-docs固定写法
         */
        resources.add(swaggerResource("AI-Alert-Service", "/zb-api/ai-alert-service/v2/api-docs", "2.0"));
        resources.add(swaggerResource("Face-Detect-Service", "/zb-api/face-detect-service/v2/api-docs", "2.0"));
        resources.add(swaggerResource("smartsite-service", "/zb-api/smartsite-service/v2/api-docs", "2.0"));
        resources.add(swaggerResource("patrolmodule-service", "/zb-api/patrolmodule-service/v2/api-docs", "2.0"));
        return resources;
    }

    private Object swaggerResource(String name, String location, String version) {

        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(location);
        swaggerResource.setSwaggerVersion(version);
        return swaggerResource;
    }
}
