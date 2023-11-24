package au.com.vietnamesecolour.config.exception;

import au.com.vietnamesecolour.config.data.ResponseStatusCode;
import au.com.vietnamesecolour.config.data.ResponseUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomAccessDeniedHandler.class);

    @Value("${spring.application.name}")
    private String serviceName;


    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            LOGGER.warn(
                    String.format(
                            "User: %s attempted to access the protected URL: %s",
                            auth.getName(), request.getRequestURI()));
        }
        ResponseStatusCode errorCode = ResponseStatusCode.FORBIDDEN;
        response.setStatus(errorCode.getCode());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        final ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(
                response.getOutputStream(),
                ResponseUtils.status(errorCode.getCode(), errorCode.getDescription(), HttpStatus.FORBIDDEN)
        );
//        response
//                .getWriter()
//                .write(
//                        JsonUtils.toJson(
//                                ResponseUtils.getResponseDataError(
//                                        errorCode.getCode(), errorCode.getDescription(), null)));
    }
}
