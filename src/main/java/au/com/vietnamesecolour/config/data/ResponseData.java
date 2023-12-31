package au.com.vietnamesecolour.config.data;

import au.com.vietnamesecolour.constant.DateConstant;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
public class ResponseData<T> implements Serializable {

    private static final long serialVersionUID = 1L;
    @JsonView({ViewMode.Public.class})
    private final String timestamp;
    @JsonView({ViewMode.Public.class})
    private int code;
    @JsonView({ViewMode.Public.class})
    private String message;
    @JsonView({ViewMode.Public.class})
    private T data;

    public ResponseData() {
        this.code = ResponseStatusCode.OK.getCode();
        this.timestamp =
                LocalDateTime.now()
                        .format(DateTimeFormatter.ofPattern(DateConstant.STR_PLAN_DD_MM_YYYY_HH_MM_SS));
        this.message = ResponseStatusCode.OK.getDescription();
    }

    public ResponseData(int code, String message) {
        this.code = code;
        this.timestamp =
                LocalDateTime.now()
                        .format(DateTimeFormatter.ofPattern(DateConstant.STR_PLAN_DD_MM_YYYY_HH_MM_SS));
        this.message = message;
    }

    ResponseData<T> success(T data) {
        this.data = data;
        return this;
    }

    ResponseData<T> error(int code, String message) {
        this.code = code;
        this.message = message;
        return this;
    }

    ResponseData<T> error(int code, String message, T data) {
        this.data = data;
        this.code = code;
        this.message = message;
        return this;
    }

    public void setData(T data) {
        this.data = data;
    }

}
