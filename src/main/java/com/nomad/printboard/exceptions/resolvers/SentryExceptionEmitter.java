package com.nomad.printboard.exceptions.resolvers;

import io.sentry.Sentry;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class SentryExceptionEmitter implements HandlerExceptionResolver, Ordered {
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        Sentry.init("https://287470a469ab4defbcf2a8d905a6259b:b294f1b3392c44c0b5243821019eaa6e@sentry.io/305615");
        Sentry.capture(e);

        return null;
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }
}
