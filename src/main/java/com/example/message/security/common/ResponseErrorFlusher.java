package com.example.message.security.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ResponseErrorFlusher {

    public static void flush(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                           Exception ex, HttpStatus status) throws IOException {
        httpServletResponse.setStatus(status.value());
        httpServletResponse.setContentType("application/json");
        Map<String, Object> data = new HashMap<>();
        data.put("timestamp", toJSONDate(new Date()));
        data.put("status", status.value());
        data.put("error", status.getReasonPhrase());
        data.put("message", ex.getMessage());
        data.put("path", httpServletRequest.getServletPath());

        OutputStream out = httpServletResponse.getOutputStream();
        com.fasterxml.jackson.databind.ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(out, data);
        out.flush();

    }

    public static String toJSONDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd'T'HH:mm:ss.SSS'Z'");
        return dateFormat.format(date);
    }
}
