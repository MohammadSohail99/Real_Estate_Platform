package com.example.Real_Estate_Platform.validation;

import com.example.Real_Estate_Platform.Model.MediatorModel;
import com.example.Real_Estate_Platform.Model.PropertyModel;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class ValidationProperty implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return PropertyModel.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        PropertyModel propertyModel = (PropertyModel) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "title.empty");
        if (propertyModel.getTitle() == null || propertyModel.getTitle().isEmpty()) {
            ValidationUtils.rejectIfEmpty(errors,"title", "title");
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address", "address.empty");
        if (propertyModel.getAddress() == null || propertyModel.getAddress().isEmpty()) {
            errors.rejectValue(
                    "address", "address");
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "price", "price.empty");
        if (propertyModel.getPrice()<=0) {
            errors.rejectValue("price", "price");
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "area", "area.empty");
        if (propertyModel.getArea()<=0) {
            errors.rejectValue("area", "area");
        }
    }
}
