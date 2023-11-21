package io.bootify.my_app.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;


@Getter
@Setter
public class ProductDTO {

    private Long id;

    @Size(max = 255)
    private String name;

    private Long categoryId;

}
