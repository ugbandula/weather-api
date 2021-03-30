package com.weather.demo.security;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.weather.demo.api.exception.AccessDeniedException;
import com.weather.demo.util.Constants;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ApiKeyAuthenticationFilter implements Filter {

    static final private String AUTH_METHOD = "apiKey";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException, AccessDeniedException {
        if (request instanceof HttpServletRequest && response instanceof HttpServletResponse) {
            String apiKey = getApiKey((HttpServletRequest) request);
            System.out.println("API Key: " + apiKey);
            if (apiKey != null) {
                if (isValidApiKey(apiKey)) {
                    System.out.println("Auth successful");
                    ApiKeyAuthenticationToken apiToken = new ApiKeyAuthenticationToken(apiKey, AuthorityUtils.NO_AUTHORITIES);
                    SecurityContextHolder.getContext().setAuthentication(apiToken);
                } else {
                    HttpServletResponse httpResponse = (HttpServletResponse) response;
                    httpResponse.setStatus(401);
                    httpResponse.getWriter().write("Invalid API Key");
                    return;
                }
            }
        }

        chain.doFilter(request, response);
    }

    private String getApiKey(HttpServletRequest httpRequest) {
        String apiKey = null;

        /**
         * First checks whether the ApiKey can be found within the header
         */
        String authHeader = httpRequest.getHeader("Authorization");
        if (authHeader != null) {
            System.out.println("API Key: " + authHeader);
            authHeader = authHeader.trim();
            if (authHeader.toLowerCase().startsWith(AUTH_METHOD + " ")) {
                apiKey = authHeader.substring(AUTH_METHOD.length()).trim();
            }
        }

        /**
         * If not checks whether the ApiKey can be found within the query string
         */
        if (apiKey == null) {
            String queryString = httpRequest.getQueryString();

            if (queryString != null && queryString.indexOf(AUTH_METHOD) >= 0) {
                String[] parameters = queryString.split("&");
                Map<String, String> queryParameters = new HashMap<>();
                for (String parameter : parameters) {
                    String[] keyValuePair = parameter.split("=");
                    queryParameters.put(keyValuePair[0], keyValuePair[1]);
                }
                apiKey = queryParameters.get(AUTH_METHOD);
            }
        }
        return apiKey;
    }

    private boolean isValidApiKey(String apiKey) {
        for (String key: Constants.SAMPLE_API_KEYs) {
            return key.equalsIgnoreCase(apiKey);
        }

        return false;
    }
}
