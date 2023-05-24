package nguyenthilinhchi.buoi456.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import nguyenthilinhchi.buoi456.entity.Category;
import nguyenthilinhchi.buoi456.validator.annotation.ValidCategoryId;

public class ValidCategoryIdValidator implements ConstraintValidator<ValidCategoryId, Category> {
    @Override
    public boolean isValid(Category category, ConstraintValidatorContext context){
        return category!=null && category.getId()!=null;
    }

}
