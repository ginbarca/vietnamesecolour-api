package io.bootify.my_app.service;

import io.bootify.my_app.dto.CategoryDTO;
import io.bootify.my_app.entity.Category;
import io.bootify.my_app.entity.Product;
import io.bootify.my_app.repos.CategoryRepository;
import io.bootify.my_app.repos.ProductRepository;
import io.bootify.my_app.util.NotFoundException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@Transactional
@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public CategoryService(final CategoryRepository categoryRepository,
            final ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    public List<CategoryDTO> findAll() {
        final List<Category> categorys = categoryRepository.findAll(Sort.by("id"));
        return categorys.stream()
                .map((category) -> mapToDTO(category, new CategoryDTO()))
                .collect(Collectors.toList());
    }

    public CategoryDTO get(final Long id) {
        return categoryRepository.findById(id)
                .map(category -> mapToDTO(category, new CategoryDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final CategoryDTO categoryDTO) {
        final Category category = new Category();
        mapToEntity(categoryDTO, category);
        return categoryRepository.save(category).getId();
    }

    public void update(final Long id, final CategoryDTO categoryDTO) {
        final Category category = categoryRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(categoryDTO, category);
        categoryRepository.save(category);
    }

    public void delete(final Long id) {
        categoryRepository.deleteById(id);
    }

    private CategoryDTO mapToDTO(final Category category, final CategoryDTO categoryDTO) {
        categoryDTO.setId(category.getId());
        categoryDTO.setName(category.getName());
        categoryDTO.setCategoryId(category.getCategoryId() == null ? null : category.getCategoryId().stream()
                .map(product -> product.getId())
                .collect(Collectors.toList()));
        return categoryDTO;
    }

    private Category mapToEntity(final CategoryDTO categoryDTO, final Category category) {
        category.setName(categoryDTO.getName());
        final List<Product> categoryId = productRepository.findAllById(
                categoryDTO.getCategoryId() == null ? Collections.emptyList() : categoryDTO.getCategoryId());
        if (categoryId.size() != (categoryDTO.getCategoryId() == null ? 0 : categoryDTO.getCategoryId().size())) {
            throw new NotFoundException("one of categoryId not found");
        }
        category.setCategoryId(categoryId.stream().collect(Collectors.toSet()));
        return category;
    }

}
