package edu.ncsu.csc.itrust2.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Log4j2
@Component
public class MvcLogger implements HandlerInterceptor {
    @Override
    public boolean preHandle(
            HttpServletRequest request,
            @NotNull HttpServletResponse response,
            @NotNull Object handler) {
        log.info("url : {}", request.getRequestURI());
        return true;
    }

    @Override
    public void postHandle(
            @NotNull HttpServletRequest request,
            HttpServletResponse response,
            @NotNull Object handler,
            ModelAndView modelAndView) {
        if (response.getStatus() >= 400)
            log.error("response: {}\nerror: {}", modelAndView, response);
        else log.info("response status: {}", response.getStatus());
    }

    @Override
    public void afterCompletion(
            @NotNull HttpServletRequest request,
            @NotNull HttpServletResponse response,
            @NotNull Object handler,
            Exception ex) {}
}
