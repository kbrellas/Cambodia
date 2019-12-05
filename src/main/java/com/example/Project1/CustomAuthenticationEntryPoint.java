package com.example.Project1;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;


public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest req, HttpServletResponse res, AuthenticationException authException) throws IOException, ServletException {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        res.setContentType("application/json;charset=UTF-8");
        res.setStatus(403);
        res.getWriter().write("{   \"timestamp\":" +timestamp+",\n"+
                        " \"status\":403," +
                        "\"error\": \"Not Found\",\n" +
                        "\"message\": \"Wrong username and/or password.Access Denied\",   }"

                );
    }
}