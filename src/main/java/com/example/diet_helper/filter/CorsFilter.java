package com.example.diet_helper.filter;

import com.example.diet_helper.utils.Const;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Cross-domain Resource Sharing (CORS) filter class that adds CORS headers to HTTP responses.
 * Allow controls from different domains to access resources.
 */
@Component
@Order(Const.ORDER_CORS)
public class CorsFilter extends HttpFilter {

    /**
     * Filter and enhance HTTP responses by adding CORS headers before passing the request to the next filter in the filter chain.
     *
     * @param request  Incoming HTTP Servlet request
     * @param response Outgoing HTTP Servlet response
     * @param chain    A chain of filters for handling requests and responses
     * @throws IOException       I/O error occurs during filtering processing
     * @throws ServletException servlet-related error occurs during filtering processing
     */
    @Override
    protected void doFilter(HttpServletRequest request,
                            HttpServletResponse response,
                            FilterChain chain) throws IOException, ServletException {
        // add CORS header to respond
        this.addCorsHeader(request, response);

        // handle request by filter chain
        chain.doFilter(request, response);
    }

    /**
     * Adds a CORS header to the HTTP response based on the source of the request and the allowed HTTP method.
     *
     * @param request  HTTP Servlet request
     * @param response HTTP Servlet respond
     */
    private void addCorsHeader(HttpServletRequest request, HttpServletResponse response) {
        // set the allowed source to the Origin header value in the request
        response.addHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));

        // define allowable HTTP methods for CORS requests
        response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");

        // specifies the allowed header of the CORS request
        response.addHeader("Access-Control-Allow-Headers", "Authorization, Content-Type");
    }
}

