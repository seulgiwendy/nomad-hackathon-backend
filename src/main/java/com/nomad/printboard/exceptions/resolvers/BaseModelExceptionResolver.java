package com.nomad.printboard.exceptions.resolvers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nomad.printboard.exceptions.ExceptionResponseResource;
import com.nomad.printboard.exceptions.model.BaseModelException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class BaseModelExceptionResolver implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest req, HttpServletResponse res, Object o, Exception e) {

        if(e instanceof BaseModelException) {
            BaseModelException exception = (BaseModelException)e;
            ExceptionResponseResource responseResource = new ExceptionResponseResource(exception);
            sendResponse(res, responseResource);
            return new ModelAndView();
        }
        return null;
    }

    private void sendResponse(HttpServletResponse res, ExceptionResponseResource resource) {
        res.setStatus(HttpStatus.NOT_FOUND.value());
        res.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        try {
            res.getWriter()
                    .write(new ObjectMapper().writeValueAsString(resource));
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
