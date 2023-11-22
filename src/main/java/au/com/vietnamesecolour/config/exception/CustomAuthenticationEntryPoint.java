package au.com.vietnamesecolour.config.exception;

import java.io.IOException;

import au.com.vietnamesecolour.config.data.ResponseStatusCode;
import au.com.vietnamesecolour.config.data.ResponseUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import static au.com.vietnamesecolour.config.data.ResponseUtils.getResponseDataError;
import static au.com.vietnamesecolour.utils.JsonUtils.toJson;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationEntryPoint.class);

    @Value("${spring.application.name}")
    private String serviceName;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        LOGGER.warn("You need to login first in order to perform this action.");
        ResponseStatusCode errorCode = ResponseStatusCode.UNAUTHORIZED;
        response.setStatus(errorCode.getCode());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        final ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(
                response.getOutputStream(),
                ResponseUtils.error(errorCode.getCode(), errorCode.getDescription(), HttpStatus.UNAUTHORIZED)
        );
//        response.getWriter()
//                .print(
//                        toJson(
//                                getResponseDataError(errorCode.getCode(), errorCode.getDescription(), null)
//                        )
//                );
    }
}
