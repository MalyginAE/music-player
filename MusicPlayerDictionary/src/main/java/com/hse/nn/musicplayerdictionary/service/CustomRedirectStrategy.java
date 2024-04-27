package com.hse.nn.musicplayerdictionary.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.springframework.security.web.DefaultRedirectStrategy;

import java.io.IOException;
import java.net.URI;

public class CustomRedirectStrategy extends DefaultRedirectStrategy {

    @Override
    public void sendRedirect(HttpServletRequest request, HttpServletResponse response, String url) throws IOException {
        if (!"WearOs".equals(request.getHeader("User"))) {
            super.sendRedirect(request, response, url);
        }else {
//            StringUtils.
            response.setStatus(200);
            response.setHeader("state" , getStateParameterFromUrl(url));
            response.getWriter().flush();
        }

    }

        @SneakyThrows
        public static String getStateParameterFromUrl(String url)  {
            URI uri = new URI(url);
            String query = uri.getQuery();
            String[] parameters = query.split("&");
            for (String parameter : parameters) {
                String[] keyValuePair = parameter.split("=");
                if (keyValuePair[0].equals("state")) {
                    return keyValuePair[1];
                }
            }
            return null;
        }


}
