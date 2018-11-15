package com.example.message.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
public class DefaultErrorController implements ErrorController {

    private static final String PATH = "/error";
    private ErrorAttributes errorAttributes;

    @Autowired
    public DefaultErrorController(final ErrorAttributes errorAttributes) {
        this.errorAttributes = errorAttributes;
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }

    @RequestMapping(path = "/404")
    public Map<String, Object> error404(HttpServletRequest request, WebRequest webRequest) {
        Map<String, Object> body = getErrorAttributes(webRequest, getTraceParameter(request));
        generateTraceInformation(body);
        body.put("message", "You reached a dark place!");
        return body;
    }

    @RequestMapping(path = PATH)
    public Map<String, Object> error(HttpServletRequest request, WebRequest webRequest){
        Map<String, Object> body = getErrorAttributes(webRequest, getTraceParameter(request));
        generateTraceInformation(body);
        return body;
    }

    private void generateTraceInformation(Map<String, Object> body) {
        String trace = (String) body.get("trace");
        if(trace != null){
            String[] lines = trace.split("\n\t");
            body.put("trace", lines);
        }
    }

    private boolean getTraceParameter(HttpServletRequest request) {
        String parameter = request.getParameter("trace");
        if (parameter == null) {
            return false;
        }
        return !"false".equals(parameter.toLowerCase());
    }

    private Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
        return errorAttributes.getErrorAttributes(webRequest, includeStackTrace);
    }
}
