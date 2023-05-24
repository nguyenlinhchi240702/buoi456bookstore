package nguyenthilinhchi.buoi456.controller;

import jakarta.validation.Valid;
import nguyenthilinhchi.buoi456.entity.Book;
import nguyenthilinhchi.buoi456.entity.Category;
import nguyenthilinhchi.buoi456.services.BookService;
import nguyenthilinhchi.buoi456.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;
    @GetMapping
    public String showAllCategories(Model model){
        List<Category> categories=categoryService.getAllCategories();
        model.addAttribute("categories",categories);
        return "category/list";
    }
    @GetMapping("/add")
    public String addCategoryForm(Model model){
        model.addAttribute("category",new Category());
       // model.addAttribute("categories",categoryService.getAllCategories());
        return "category/add";
    }
    @PostMapping("/add")
    public String addCategory(@Valid @ModelAttribute("category") Category category, BindingResult result, Model model){
        if(result.hasErrors()){
           // model.addAttribute("categories",categoryService.getAllCategories());
            return "category/add";
        }
        categoryService.addCategory(category);
        return "redirect:/categories";
    }
    @GetMapping("/edit/{id}")
    public String editBookForm(@PathVariable("id") long id, Model model){
        Category editCategory=categoryService.getCategoryById(id);
        if(editCategory != null){
            model.addAttribute("category", editCategory);
            //model.addAttribute("categories", categoryService.getAllCategories());
            return "category/edit";
        }else {
            return "not-found";
        }
    }
    @PostMapping("/edit")
    public String editCategory(@Valid @ModelAttribute("category")Category updateCategory, BindingResult bindingResult, Model model ){
        if (bindingResult.hasErrors()){
            model.addAttribute("categories", categoryService.getAllCategories());
            return "category/edit";
        }
        categoryService.getAllCategories().stream()
                        .filter(category -> category.getId()==updateCategory.getId())
                                .findFirst()
                                        .ifPresent(category -> {categoryService.updateCategory(updateCategory);});
        return "redirect:/books";
    }
    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable("id") long id){
        categoryService.deleteCategory(id);
        return "redirect:/categories";
    }
}
