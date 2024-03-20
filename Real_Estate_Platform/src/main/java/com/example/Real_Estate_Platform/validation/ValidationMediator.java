package com.example.Real_Estate_Platform.validation;

import com.example.Real_Estate_Platform.Model.MediatorModel;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class ValidationMediator implements Validator {


    @Override
    public boolean supports(Class<?> clazz) {
        return MediatorModel.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        MediatorModel mediatorModel = (MediatorModel) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");
        if (mediatorModel.getUsername() == null || mediatorModel.getUsername().isEmpty()) {
            ValidationUtils.rejectIfEmpty(errors,"username", "username");
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "Password");
        if (mediatorModel.getPassword() == null || mediatorModel.getPassword().length()<6) {
            errors.rejectValue(
                    "password", "Password");
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "Email");
        if (!mediatorModel.getEmail().endsWith("@gmail.com") || mediatorModel.getEmail().isBlank()) {
            errors.rejectValue("email", "Email");
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "mobile", "Mobile");
        if(mediatorModel.getMobile().toString().length()!=10){
            errors.rejectValue("mobile","Mobile");
        }
    }

}
