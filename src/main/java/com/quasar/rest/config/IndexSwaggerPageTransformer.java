package com.quasar.rest.config;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.resource.ResourceTransformer;
import org.springframework.web.servlet.resource.ResourceTransformerChain;
import org.springframework.web.servlet.resource.TransformedResource;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;

@Component
public class IndexSwaggerPageTransformer implements ResourceTransformer {

    @Value("#{'${server.servlet.context-path:}'.concat('${springdoc.api-docs.path:/v3/api-docs}')}")
    private String apiDocPath;

    private static final String DEFAULT_SWAGGER_URL = "https://petstore.swagger.io/v2/swagger.json";

    @Override
    public Resource transform(HttpServletRequest request, Resource resource,
                              ResourceTransformerChain transformerChain) throws IOException {
        if (resource.getURL().toString().endsWith("/index.html")) {
            String html = getHtmlContent(resource);
            html = overwritePetStore(html);
            return new TransformedResource(resource, html.getBytes());
        } else {
            return resource;
        }
    }

    private String overwritePetStore(String html) {
        return html.replace(DEFAULT_SWAGGER_URL,
                apiDocPath);
    }

    private String getHtmlContent(Resource resource) throws IOException {
        InputStream inputStreamResource = null;

        try {
            inputStreamResource = resource.getInputStream();
            String html = IOUtils.toString(inputStreamResource);
            return html;
        } finally {
            if(inputStreamResource != null) {
                inputStreamResource.close();
            }
        }
    }
}
