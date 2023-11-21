//package io.bootify.my_app.config.exception;
//
//import io.bootify.my_app.utils.JsonUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.MediaType;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.nio.file.AccessDeniedException;
//
//@Component
//public class CustomAccessDeniedHandler implements AccessDeniedHandler {
//
//    private static final Logger LOGGER = LoggerFactory.getLogger(CustomAccessDeniedHandler.class);
//
//    private final LoggingProperties loggingProperties;
//
//    @Value("${spring.application.name}")
//    private String serviceName;
//
//    @Autowired
//    public CustomAccessDeniedHandler(LoggingProperties loggingProperties) {
//        this.loggingProperties = loggingProperties;
//    }
//
//    @Override
//    public void handle(
//            HttpServletRequest request, HttpServletResponse response, AccessDeniedException exc)
//            throws IOException {
//        logRequest(request, serviceName, loggingProperties);
//        var auth = SecurityContextHolder.getContext().getAuthentication();
//        if (auth != null) {
//            LOGGER.warn(
//                    String.format(
//                            "User: %s attempted to access the protected URL: %s",
//                            auth.getName(), request.getRequestURI()));
//        }
//        var errorCode = CommonErrorCode.FORBIDDEN;
//        response.setStatus(errorCode.getHttpStatus().value());
//        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
//        response
//                .getWriter()
//                .write(
//                        JsonUtils.toJson(
//                                ResponseUtils.getResponseDataError(
//                                        errorCode.getCode(), errorCode.getMessage(), null, false)));
//        logResponse(request, response, loggingProperties);
//    }
//}
