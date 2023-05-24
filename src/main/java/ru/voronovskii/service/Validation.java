package ru.voronovskii.service;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import ru.voronovskii.exception.ErrorMessages;
import ru.voronovskii.exception.UserServiceException;

import java.util.ArrayList;
import java.util.List;

@Service
public class Validation {

    public void checkConstraints(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> fieldErrorResources = new ArrayList<>();
            for (FieldError constraintViolation : bindingResult.getFieldErrors())
                fieldErrorResources.add(constraintViolation.getDefaultMessage());
            throw new UserServiceException(String.format(ErrorMessages.VALIDATION_ERROR.getErrorCause(), String.join(", ", fieldErrorResources)), ErrorMessages.VALIDATION_ERROR.getErrorCode());
        }
    }

}