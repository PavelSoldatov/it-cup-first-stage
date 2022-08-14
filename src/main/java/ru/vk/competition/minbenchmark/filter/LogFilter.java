package ru.vk.competition.minbenchmark.filter;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.stream.Collectors;

@Component
@Slf4j
public class LogFilter implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        log.info(request.getContextPath());
//        if ("POST".equalsIgnoreCase(request.getMethod())) {
//            try {
//                log.info(request.getReader().lines().collect(Collectors.joining(System.lineSeparator())));
//            } catch (Exception ignored) {
//
//            }
//        }
//        log.info("request is " + request + "response is" + response + "object is" + handler);
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//        if (request.getContextPath().startsWith("/api/report")){
            log.info("status is " + response.getStatus());
//        }
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        if (request.getRequestURI().startsWith("/api/report") || request.getRequestURI().startsWith("/api/table-query")){
            log.info("status is " + response.getStatus());
        }
        log.info("status is " + response.getStatus());
        try {
            if(ex != null) {
                log.info("exception is" + ex.getMessage());
                ex.printStackTrace();
            }
        } catch (Exception ignored) {

        }
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
