package io.bootify.my_app.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;
import java.util.List;


@Getter
@Setter
public class CategoryDTO {

    private Long id;

    @Size(max = 255)
    private String name;

    private List<Long> categoryId;

}
