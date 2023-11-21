package au.com.vietnamesecolour.config.exception;

import au.com.vietnamesecolour.config.data.ResponseData;
import au.com.vietnamesecolour.config.data.ResponseUtils;
import jakarta.persistence.EntityNotFoundException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.List;

@RestController
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(RestExceptionHandler.class);

    /**
     * Customize the response for MissingServletRequestParameterException. Triggered when a 'required'
     * request parameter is missing.
     *
     * @param ex      the exception
     * @param headers the headers to be written to the response
     * @param status  the selected response status
     * @param request the current request
     * @return a {@code ResponseEntity} instance
     */
    @SuppressWarnings("unchecked")
    @Override
    public ResponseEntity<Object> handleMissingServletRequestParameter(
            MissingServletRequestParameterException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {
        LOGGER.error(ex.getMessage());
        return this.handleError(CommonErrorCode.MISSING_REQUEST_PARAMETER);
    }

    /**
     * Customize the response for HttpMediaTypeNotSupportedException. This one triggers when JSON is
     * invalid as well.
     *
     * @param ex      the exception
     * @param headers the headers to be written to the response
     * @param status  the selected response status
     * @param request the current request
     * @return a {@code ResponseEntity} instance
     */
    @SuppressWarnings("unchecked")
    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(
            HttpMediaTypeNotSupportedException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {
        LOGGER.error(ex.getMessage());
        return this.handleError(CommonErrorCode.UNSUPPORTED_MEDIA_TYPE);
    }

    /**
     * Customize the response for HttpRequestMethodNotSupportedException.
     *
     * <p>This method logs a warning, sets the "Allow" header, and delegates to
     *
     * @param ex      the exception
     * @param headers the headers to be written to the response
     * @param status  the selected response status
     * @param request the current request
     * @return a {@code ResponseEntity} instance
     */
    @SuppressWarnings("unchecked")
    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
            HttpRequestMethodNotSupportedException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {
        LOGGER.error(ex.getMessage());
        return this.handleError(CommonErrorCode.METHOD_NOT_ALLOWED);
    }

    /**
     * Handle MethodArgumentNotValidException. Triggered when an object fails @Valid validation.
     *
     * @param ex      the exception
     * @param headers the headers to be written to the response
     * @param status  the selected response status
     * @param request the current request
     * @return a {@code ResponseEntity} instance
     */
    @SuppressWarnings("unchecked")
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {
        LOGGER.error(ex.getMessage());
        return this.handleError(CommonErrorCode.ARGUMENT_NOT_VALID, null, this.getSubErrors(ex));
    }

    /**
     * Customize the response for HttpMessageNotReadableException. Happens when request JSON is
     * malformed.
     *
     * @param ex      the exception
     * @param headers the headers to be written to the response
     * @param status  the selected response status
     * @param request the current request
     * @return a {@code ResponseEntity} instance
     */
    @SuppressWarnings("unchecked")
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {
        LOGGER.error(ex.getMessage());
        return this.handleError(CommonErrorCode.BAD_REQUEST);
    }

    /**
     * Customize the response for HttpMessageNotWritableException.
     *
     * @param ex      the exception
     * @param headers the headers to be written to the response
     * @param status  the selected response status
     * @param request the current request
     * @return a {@code ResponseEntity} instance
     */
    @SuppressWarnings("unchecked")
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotWritable(
            HttpMessageNotWritableException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {
        LOGGER.error(ex.getMessage());
        return this.handleError(CommonErrorCode.BAD_REQUEST);
    }

    /**
     * Customize the response for NoHandlerFoundException.
     *
     * @param ex      the exception
     * @param headers the headers to be written to the response
     * @param status  the selected response status
     * @param request the current request
     * @return a {@code ResponseEntity} instance
     */
    @SuppressWarnings("unchecked")
    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(
            NoHandlerFoundException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        LOGGER.error(ex.getMessage());
        return this.handleError(CommonErrorCode.NOT_FOUND, new Object[]{ex.getHttpMethod(), ex.getRequestURL()});
    }

    /**
     * Customize the response for HttpMediaTypeNotAcceptableException.
     *
     * @param ex      the exception
     * @param headers the headers to be written to the response
     * @param status  the selected response status
     * @param request the current request
     * @return a {@code ResponseEntity} instance
     */
    @SuppressWarnings("unchecked")
    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(
            HttpMediaTypeNotAcceptableException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {
        LOGGER.error(ex.getMessage());
        return this.handleError(CommonErrorCode.MEDIA_TYPE_NOT_ACCEPTABLE);
    }

    /**
     * Handle javax.persistence.EntityNotFoundException
     *
     * @param ex the exception
     * @return a {@code ResponseEntity} instance
     */
    @SuppressWarnings("unchecked")
    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<Object> handleEntityNotFound(
            EntityNotFoundException ex) {
        LOGGER.error(ex.getMessage());
        return this.handleError(CommonErrorCode.ENTITY_NOT_FOUND);
    }

    /**
     * Handle DataIntegrityViolationException, inspects the cause for different DB causes.
     *
     * @param ex the exception
     * @return a {@code ResponseEntity} instance
     */
    @SuppressWarnings("unchecked")
    @ExceptionHandler(DataIntegrityViolationException.class)
    protected ResponseEntity<Object> handleDataIntegrityViolation(
            DataIntegrityViolationException ex) {
        LOGGER.error(ex.getMessage(), ex);
        return this.handleError(CommonErrorCode.DATA_INTEGRITY_VIOLATION);
    }

    /**
     * Handle AccessDeniedException.
     *
     * @param ex the exception
     * @return a {@code ResponseEntity} instance
     */
    @SuppressWarnings("unchecked")
    @ExceptionHandler(AccessDeniedException.class)
    protected ResponseEntity<Object> handleAccessDeniedException(AccessDeniedException ex) {
        LOGGER.error(ex.getMessage());
        return this.handleError(CommonErrorCode.FORBIDDEN);
    }

    /**
     * Handle Exception, handle generic Exception.class
     *
     * @param ex the exception
     * @return a {@code ResponseEntity} instance
     */
    @SuppressWarnings("unchecked")
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<Object> handleMethodArgumentTypeMismatch(
            MethodArgumentTypeMismatchException ex) {
        LOGGER.error(ex.getMessage());
        return this.handleError(
                CommonErrorCode.ARGUMENT_TYPE_MISMATCH, new Object[]{ex.getRequiredType(), ex.getName()});
    }

    /**
     * Handle BaseException
     *
     * @param ex the exception
     * @return a {@code ResponseEntity} instance
     */
    @ExceptionHandler(BaseException.class)
    protected ResponseEntity<ResponseData<Object>> handleBaseException(BaseException ex) {
        List<SubError> subErrors = null;
        LOGGER.error(ex.getLocalizedMessage(), ex);
        int code = ex.getCode();
        HttpStatus httpStatus = ex.getHttpStatus();
        return ResponseUtils.error(code, ex.getMessage(), subErrors, httpStatus, false);
    }

    /**
     * Handles HttpClientErrorException
     *
     * @param ex the exception
     * @return a {@code ResponseEntity} instance
     */
    @SuppressWarnings("unchecked")
    @ExceptionHandler(HttpClientErrorException.class)
    protected ResponseEntity<Object> handleHttpClientErrorException(HttpClientErrorException ex) {
        LOGGER.error(ex.getMessage(), ex);
        return this.handleError(CommonErrorCode.EXECUTE_THIRTY_SERVICE_ERROR);
    }

    /**
     * Handles Exception
     *
     * @param ex the exception
     * @return a {@code ResponseEntity} instance
     */
    @SuppressWarnings("unchecked")
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleAllException(Exception ex) {
        LOGGER.error(ex.getMessage(), ex);
        return this.handleError(CommonErrorCode.INTERNAL_SERVER_ERROR);
    }

    @SuppressWarnings("rawtypes")
    private ResponseEntity handleError(CommonErrorCode commonError) {
        return this.handleError(commonError, null, null);
    }

    @SuppressWarnings("rawtypes")
    private ResponseEntity handleError(CommonErrorCode commonError, Object[] messageArg) {
        return this.handleError(commonError, messageArg, null);
    }

    @SuppressWarnings("rawtypes")
    private ResponseEntity handleError(
            CommonErrorCode commonError, Object[] messageArg, List<SubError> subErrors) {
        String message = commonError.getMessage();
        int code = commonError.getCode();
        if (messageArg != null) {
            message = String.format(message, messageArg);
        }
        HttpStatus httpStatus = commonError.getHttpStatus();
        return ResponseUtils.error(code, message, subErrors, httpStatus, false);
    }

    private List<SubError> getSubErrors(MethodArgumentNotValidException e) {
        List<SubError> subErrors = new ArrayList<>();
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        for (FieldError fieldError : fieldErrors) {
            SubError subError =
                    new SubError(
                            fieldError.getField(), fieldError.getRejectedValue(), fieldError.getDefaultMessage());
            subErrors.add(subError);
        }
        return subErrors;
    }

    /**
     * @author haidv
     * @version 1.0
     */
    @Getter
    @RequiredArgsConstructor
    public static class SubError {

        private final String fieldName;

        private final Object fieldValue;

        private final String message;
    }
}
