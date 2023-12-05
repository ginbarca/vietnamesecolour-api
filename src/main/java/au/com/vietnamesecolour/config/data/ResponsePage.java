package au.com.vietnamesecolour.config.data;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
public class ResponsePage<T> {

    @JsonView({ViewMode.Public.class})
    private final int pageNo;

    @JsonView({ViewMode.Public.class})
    private final int pageSize;

    @JsonView({ViewMode.Public.class})
    private final long totalCount;

    @JsonView({ViewMode.Public.class})
    private final int totalPage;

    @JsonView({ViewMode.Public.class})
    private final List<T> content;

    public ResponsePage(int pageNo, int pageSize, long totalCount, int totalPage, List<T> content) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.totalCount = totalCount;
        this.totalPage = totalPage;
        this.content = content;
    }

    public ResponsePage(Page<T> page) {
        this(page, page.getContent());
    }

    @SuppressWarnings("rawtypes")
    public ResponsePage(Page page, List<T> content) {
        this(
                page.getNumber() + 1,
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages(),
                content);
    }
}
