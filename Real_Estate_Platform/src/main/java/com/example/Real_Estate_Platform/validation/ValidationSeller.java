package com.example.Real_Estate_Platform.validation;

import com.example.Real_Estate_Platform.Model.SellerModel;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class ValidationSeller implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return SellerModel.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        SellerModel sellerModel = (SellerModel) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");
        if (sellerModel.getUsername() == null || sellerModel.getUsername().isBlank()){
            ValidationUtils.rejectIfEmpty(errors,"username", "username");
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "Password");
        if (sellerModel.getPassword() == null || sellerModel.getUsername().isEmpty() || sellerModel.getPassword().length()<6) {
            errors.rejectValue("password", "Password");
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "Email");
        if (sellerModel.getEmail().isBlank() || !sellerModel.getEmail().endsWith("@gmail.com")) {
            errors.rejectValue("email", "Email");
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "mobile", "Mobile");
        String mobile=sellerModel.getMobile()+"";
        if(mobile.length()!=10){
            errors.rejectValue("mobile","Mobile");
        }
    }
}
