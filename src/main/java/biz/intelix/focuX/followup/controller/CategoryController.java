package biz.intelix.focuX.followup.controller;

import biz.intelix.focuX.followup.exception.ResourceNotFoundException;
import biz.intelix.focuX.followup.model.Category;
import biz.intelix.focuX.followup.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/followup/categories")
@CrossOrigin("*")
public class CategoryController {

    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/all")
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> findCategoryById(@PathVariable(value = "id") Integer id)
            throws ResourceNotFoundException {
        return categoryService.findCategoryById(id);
    }

    @PostMapping("/save")
    public Category createCategory(@RequestBody Category category)
            throws ParseException {
        return categoryService.saveCategory(category);
    }

    @PutMapping("/category/{id}")
    public Category updateCategory(@RequestBody Category newCategory, @PathVariable Integer id)
            throws ParseException {
        return categoryService.updateCategory(newCategory, id);
    }

    @PutMapping("/status/{id}")
    public Category updateCategoryStatus(@RequestBody Category category, @PathVariable Integer id)
            throws ParseException {
        return categoryService.updateCategoryStatus(id, category);
    }

}
