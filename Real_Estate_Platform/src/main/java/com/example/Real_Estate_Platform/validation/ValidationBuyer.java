package com.example.Real_Estate_Platform.validation;

import com.example.Real_Estate_Platform.Model.BuyerModel;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.stereotype.Component;

@Component
public class ValidationBuyer implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return BuyerModel.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        BuyerModel buyerModel = (BuyerModel) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");
        if (buyerModel.getUsername() == null || buyerModel.getUsername().isBlank()){
            ValidationUtils.rejectIfEmpty(errors,"username", "username");
        }
        if (buyerModel.getPassword() == null || buyerModel.getUsername().isEmpty() || buyerModel.getPassword().length()<6) {
            errors.rejectValue("password", "Password");
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "Email");
        if (buyerModel.getEmail().isBlank() || !buyerModel.getEmail().endsWith("@gmail.com")) {
            errors.rejectValue("email", "Email");
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "mobile", "Mobile");
        if(buyerModel.getMobile().toString().length()!=10){
            errors.rejectValue("mobile","Mobile");
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address", "NotEmpty.address");
        if(buyerModel.getAddress().isBlank()){
            errors.rejectValue("address","Address");
        }
    }
}
