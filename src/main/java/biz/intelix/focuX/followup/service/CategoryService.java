package biz.intelix.focuX.followup.service;


import biz.intelix.focuX.followup.model.Category;
import biz.intelix.focuX.followup.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public ResponseEntity<Category> findCategoryById(Integer id) {
        Category category = categoryRepository.findCategoryById(id);
        return ResponseEntity.ok().body(category);
    }

    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    public Category updateCategory(Category newCategory, Integer id) {

        return categoryRepository.findById(id)
                .map(category -> {
                    category.setId(newCategory.getId());
                    category.setName(newCategory.getName());
                    category.setDescription(newCategory.getDescription());
                    category.setUpdated(newCategory.getUpdated());
                    category.setUpdatedBy(newCategory.getUpdatedBy());
                    return categoryRepository.save(category);
                })
                .orElseGet(() -> {
                    newCategory.setId(id);
                    return categoryRepository.save(newCategory);
                });
    }

    public Category updateCategoryStatus(Integer id, Category category) {

        return categoryRepository.findById(id)
                .map(categoryWithNewStatus -> {
                    categoryWithNewStatus.setIsActive(category.getIsActive());
                    categoryWithNewStatus.setUpdated(category.getUpdated());
                    categoryWithNewStatus.setUpdatedBy(category.getUpdatedBy());
                    return categoryRepository.save(categoryWithNewStatus);
                }).get();

    }

}
