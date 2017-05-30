package com.dannyns.cms.backend.interceptors;

import com.dannyns.cms.backend.annotations.Layout;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

public class ThymeleafLayoutInterceptor extends HandlerInterceptorAdapter {

    private static final String DEFAULT_LAYOUTS_DIRECTORY = "layouts";
    private static final String DEFAULT_LAYOUT = String.join("/", DEFAULT_LAYOUTS_DIRECTORY, "main");
    private static final String DEFAULT_VIEWS_DIRECTORY = "views/";
    private static final String DEFAULT_VIEW_ATTRIBUTE_NAME = "view";

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);

        Optional.ofNullable(modelAndView)
                .map(ModelAndView::getViewName)
                .filter(viewName -> !viewName.startsWith("redirect:"))
                .filter(viewName -> !viewName.startsWith("forward:"))
                .ifPresent(viewName -> {
                    String layoutName = getLayoutName(handler);
                    modelAndView.setViewName(layoutName);
                    modelAndView.addObject(DEFAULT_VIEW_ATTRIBUTE_NAME, String.join("", DEFAULT_VIEWS_DIRECTORY, viewName));
                });
    }

    private String getLayoutName(Object handler) {
        HandlerMethod handlerMethod = (HandlerMethod) handler;

        return Arrays.asList(
                handlerMethod.getMethodAnnotation(Layout.class),
                handlerMethod.getBeanType().getAnnotation(Layout.class)
        )
                .stream()
                .filter(Objects::nonNull)
                .map(layout -> String.join("/", DEFAULT_LAYOUTS_DIRECTORY, layout.value()))
                .findFirst()
                .orElse(DEFAULT_LAYOUT);
    }
}
